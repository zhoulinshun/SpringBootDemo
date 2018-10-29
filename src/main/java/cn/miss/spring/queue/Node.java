package cn.miss.spring.queue;

/**
 * @Author zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/1.
 */
public class Node<T> {
    private T value;

    private Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getNext() {
        return next;
    }
}
