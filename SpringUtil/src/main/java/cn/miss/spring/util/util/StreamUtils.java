package cn.miss.spring.util.util;

import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/24.
 */
public class StreamUtils {

    public static <T, R> Function<T, Stream<R>> flatMapIte(Function<T, Iterable<R>> function) {
        return t -> StreamSupport.stream(function.apply(t).spliterator(), true);
    }
}
