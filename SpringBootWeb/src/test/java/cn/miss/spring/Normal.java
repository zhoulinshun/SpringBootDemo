package cn.miss.spring;

import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/19.
 */
public class Normal extends MM {

    private NN message = new NN();

    public Normal() {
        final ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.te
        final Observable observable = new Observable();
        System.out.println("Normal.Normal");
    }

    public static void main(String[] args) {
//        new Normal();
        final String[] split = "abc".split("");
        System.out.println(split.length);
    }
}

class NN {
    public NN() {
        System.out.println("NN");
    }
}

class MM{
    public MM() {
        System.out.println("MM.MM");
    }
}