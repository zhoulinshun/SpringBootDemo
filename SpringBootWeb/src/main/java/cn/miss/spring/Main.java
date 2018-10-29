package cn.miss.spring;

import cn.miss.spring.util.loader.FileResourceLoader;
import org.springframework.boot.ImageBanner;
import org.springframework.core.env.StandardEnvironment;
import sun.security.util.BitArray;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/13.
 */
public class Main {
//    public static void main(String[] args) throws NoSuchMethodException {
//        final Method method = Demo.class.getMethod("method", Demo.class);
//
//        final Demo testDemo = new Demo();
//        testDemo.age = "age:10";
//
//
//        final Demo demo = new Demo();
//        SpelExpressionParser parser = new SpelExpressionParser();
//        MethodBasedEvaluationContext methodBasedEvaluationContext = new MethodBasedEvaluationContext(demo, method, new Object[]{testDemo}, new LocalVariableTableParameterNameDiscoverer());
//
//        final SpelExpression spelExpression = parser.parseRaw("#demo.age");
//        final Object value = spelExpression.getValue(methodBasedEvaluationContext);
//        System.out.println(value);
//
//    }

    public static void main(String[] args) {
//        System.out.println(Integer.MAX_VALUE);
//        BitArray bitArray = new BitArray(1000000000);
//        BitMap bitMap = new BitMap(Integer.MAX_VALUE);

//        bitArray.set();


        final StandardEnvironment standardEnvironment = new StandardEnvironment();
        ImageBanner imageBanner = new ImageBanner(FileResourceLoader.load("timg.jpeg"));
        imageBanner.printBanner(standardEnvironment, Main.class, System.out);
    }

    public static class Demo {

        private String age;

        public void method(Demo demo) {

        }

        public String getAge() {
            return age;
        }
    }
}
