package cn.miss.spring.util.share;

import sun.misc.Contended;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * @Author: zhoulinshun
 * @Description: 伪共享
 * 运行参数  -XX:-RestrictContended
 * @Date: Created in 2018/9/20.
 */
public class FalseSharing {
    final static int ITEM = 1024*1024;
    final static TestLong[] testLongs = new TestLong[4];
    final static int threadNum = 4;

    static {
        for (int i = 0; i < testLongs.length; i++) {
            testLongs[i] = new TestLong();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        Semaphore semaphore = new Semaphore(threadNum);
        semaphore.acquire(threadNum);
        final long l = System.nanoTime();
        for (int i = 0; i < threadNum; i++) {
            int temp = i;
            new Thread(() -> {
                try {
                    int item = temp + ITEM;
                    semaphore.acquire();
                    System.out.println(temp + "begin");
                    while (--item > 0) {
                        testLongs[temp].value = item;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }
        semaphore.release(threadNum);
        countDownLatch.await();
        //75439273  1024  on
        //84782566  1024  off
        //143577873 1024*1024 off
        //128201865 1024*1024 on
        System.out.println(System.nanoTime() - l);
    }


    @Contended
    public static class TestLong {
        /**
         * mark word 8byte
         * class reference 4byte
         * value 8 byte
         * 20byte
         */
        public volatile long value;
    }

    @Contended
    public static class TestLong2 {
        /**
         * mark word 8byte
         * class reference 4byte
         * value 8 byte
         * 20byte
         */
        public volatile long value;
    }
}
