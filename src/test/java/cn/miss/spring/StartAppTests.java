package com.example.demo;

import com.example.demo.test.TestAutoConfig;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Test;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.MethodInfo;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = TestStartApp.class)
public class StartAppTests {

    @Autowired
    private TestAutoConfig testAutoConfig;

    @Test
    public void contextLoads() {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();

        test(new String[10]);
        LockSupport.park();

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
//        concurrentHashMap.put()
//        HashMap

        DoubleAdder adder = new DoubleAdder();
//        adder.add();
        final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
//        StampedLock
        AtomicInteger atomicInteger = new AtomicInteger(20);
        atomicInteger.incrementAndGet();
    }

    public void test(String[]... arg) {
        System.out.println("StartAppTests.test2");

    }

    public void test(String... arg) {
        System.out.println("StartAppTests.test1");
    }

    public static void main(String[] args) {
        SpelExpressionParser parser = new SpelExpressionParser();
        final Demo demo1 = new Demo();
        demo1.str = "abcd";

        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();

        MethodBasedEvaluationContext methodBasedEvaluationContext = new MethodBasedEvaluationContext(demo1,null,null,new LocalVariableTableParameterNameDiscoverer());
        final SpelExpression spelExpression = parser.parseRaw("str");
        final Object value = spelExpression.getValue(methodBasedEvaluationContext);
        System.out.println(value);
        String m = "hahahahahahahhahhhsssasas";
        final Demo dd = new Demo();
        final Demo demo = new Demo();
        System.out.println(demo.hashCode());
//        demo.ss = m;
//        demo.demo = dd;
//        System.out.println(MemoryUtil.deepMemoryUsageOf(m));
//        System.out.println(MemoryUtil.deepMemoryUsageOf(dd));
//        final long l = MemoryUtil.deepMemoryUsageOf(demo);
//        System.out.println(l);
//
//        final long l1 = MemoryUtil.memoryUsageOf(demo);
//        System.out.println(l1);
    }


    public static class Demo {

        static {
            try {
                final Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
                theUnsafe.setAccessible(true);
                final Unsafe unsafe = (Unsafe) theUnsafe.get(null);
                final long s_offset = unsafe.objectFieldOffset(Demo.class.getDeclaredField("str"));

//                final long s_offset = unsafe.objectFieldOffset(Demo.class.getDeclaredField("s"));
//                System.out.println("s_offset = " + s_offset);
//                final long b_offset = unsafe.objectFieldOffset(Demo.class.getDeclaredField("b"));
//                System.out.println("b_offset = " + b_offset);
//                final long i_offset = unsafe.objectFieldOffset(Demo.class.getDeclaredField("i"));
//                System.out.println("i_offset = " + i_offset);
//                final long ss_offset = unsafe.objectFieldOffset(Demo.class.getDeclaredField("ss"));
//                System.out.println("ss_offset = " + ss_offset);
//
//                final long ii_offset = unsafe.objectFieldOffset(Demo.class.getDeclaredField("ii"));
//                System.out.println("ii_offset = " + ii_offset);
//
//                final long bb_offset = unsafe.objectFieldOffset(Demo.class.getDeclaredField("bb"));
//                System.out.println("bb_offset = " + bb_offset);
//
//                final long d_offset = unsafe.objectFieldOffset(Demo.class.getDeclaredField("d"));
//                System.out.println("d_offset = " + d_offset);
//
//                final long dd_offset = unsafe.objectFieldOffset(Demo.class.getDeclaredField("dd"));
//                System.out.println("dd_offset = " + dd_offset);
//
//                final long l_offset = unsafe.objectFieldOffset(Demo.class.getDeclaredField("l"));
//                System.out.println("l_offset = " + l_offset);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

//        //16
//        private double d;
//
//        //24
//        private Integer s;
//
//        //20
//        private byte b;
//
//        //12
//        private int i;
//
//        //28
//        private String ss;
//
//        //16
//        private int ii;
//
//        private long l;
//
//        //21
//        private byte bb;
//
//        private double dd;


        //20
        private String str;

        //28
        private Demo demo;

        //16
        private int i;

        //count 36+4=40

        public String getStr() {
            return str;
        }
    }
}



