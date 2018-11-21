package cn.miss.spring.util.rxjava;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/24.
 */
public class RxJava2 {
    public static void main(String[] args) {

        Observable.fromArray().subscribeOn(Schedulers.io()).sorted( ).subscribe().dispose();

    }
}
