package cn.miss.spring.queue;

/**
 * @Author zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/1.
 */
public class Consumer implements Runnable {

    private SynBlockQueue<Integer> queue;

    public Consumer(SynBlockQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(Thread.currentThread().getName() + ":尝试消费一个货物");
                Integer take = queue.take();
                System.out.println(Thread.currentThread().getName() + ":消费成功,剩余数量：" + queue.size());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
