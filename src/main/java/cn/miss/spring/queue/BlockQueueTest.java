package cn.miss.spring.queue;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/1.
 */
public class BlockQueueTest {

    public static void main(String[] args) throws InterruptedException {
        SynBlockQueue<Integer> queue = new SynBlockQueue<>(10);
        ExecutorService executorService = Executors.newFixedThreadPool(40);
        for (int i = 0; i < 20; i++) {
            executorService.submit(new Consumer(queue));

        }
        for (int i = 0; i < 20; i++) {
            executorService.submit(new Producer(queue));
        }

        Thread.sleep(5000);
        executorService.shutdownNow();
        System.out.println(executorService.isShutdown());
    }


    @Test
    public void test() throws Exception {

    }
}
