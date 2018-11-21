package cn.miss.spring.util.rxjava;



import io.reactivex.Observable;
import io.reactivex.internal.schedulers.ExecutorScheduler;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/21.
 */
public class SimulationSpider {

    public static void main(String[] args) {
//        Observable.create()
//        spider();
        streamSpider();
    }

    private static void spider() {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        final ExecutorService executorService1 = Executors.newFixedThreadPool(5);
        Observable.fromArray("abc", "def")
                .subscribeOn(new ExecutorScheduler(executorService))
                //模拟下载  使用缓存线程池
                .map(SimulationSpider::simulationDown)
                .observeOn(new ExecutorScheduler(executorService1))
                //模拟解析 使用固定线程池
                .flatMap(html -> Observable.fromArray(SimulationSpider.simulationParse(html)))
                .subscribe(System.out::println, Throwable::printStackTrace, () -> {
                    executorService.shutdown();
                    executorService1.shutdown();
                });
    }

    private static void streamSpider(){
        final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        final ForkJoinPool forkJoinPool1 = new ForkJoinPool(10);
        final ExecutorService executorService = Executors.newCachedThreadPool();
        Arrays.asList("abc","def").parallelStream().
                map(SimulationSpider::simulationDown).
                map(SimulationSpider::simulationParse).
                flatMap(Stream::of).
                forEach(System.out::println);
    }


    public static String simulationDown(String url) {
        try {
            System.out.println("url = " + url);
            Thread.sleep(1000);
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String[] simulationParse(String html) {
        System.out.println("html = " + html);
        try {
            Thread.sleep(1000);
            return html.split("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String[0];
    }

}
