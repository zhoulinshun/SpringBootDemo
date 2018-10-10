package cn.miss.spring.util.rxjava;

import rx.Observable;
import rx.internal.schedulers.ExecutorScheduler;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/21.
 */
public class SimulationSpider {

    public static void main(String[] args) {
//        Observable.create()
    }

    private static void spider() {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        final ExecutorService executorService1 = Executors.newFixedThreadPool(5);
        Observable.from(Arrays.asList("abc", "def"))
                .subscribeOn(new ExecutorScheduler(executorService))
                //模拟下载  使用缓存线程池
                .map(SimulationSpider::simulationDown)
                .observeOn(new ExecutorScheduler(executorService1))
                //模拟解析 使用固定线程池
                .flatMap(html -> Observable.from(SimulationSpider.simulationParse(html)))
                .subscribe(System.out::println, Throwable::printStackTrace, () -> {
                    executorService.shutdown();
                    executorService1.shutdown();
                });
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
