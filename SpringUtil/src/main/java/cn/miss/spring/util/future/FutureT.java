package cn.miss.spring.util.future;

import org.springframework.util.concurrent.ListenableFutureTask;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/24.
 */
public class FutureT {
    public static void main(String[] args) {
        final ListenableFutureTask<String> futureTask = new ListenableFutureTask<>(() -> {}, "");
    }
}
