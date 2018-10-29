package cn.miss.spring.queue;

/**
 * @Author zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/1.
 */
public class Producer implements Runnable {
    private SynBlockQueue<Integer> queue;

    public Producer(SynBlockQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(Thread.currentThread().getName() + ":尝试生产一个货物");
                queue.put(1);
                System.out.println(Thread.currentThread().getName() + ":生产成功,剩余数量：" + queue.size());
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
