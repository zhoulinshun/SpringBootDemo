package cn.miss.spring;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/9/19.
 */
public class Normal extends MM {

    private NN message = new NN();

    public Normal() {
        System.out.println("Normal.Normal");
    }

    public static void main(String[] args) {
        new Normal();
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