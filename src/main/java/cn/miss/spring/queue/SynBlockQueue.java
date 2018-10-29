package cn.miss.spring.queue;

import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/1.
 */
public class SynBlockQueue<E> extends AbstractQueue<E> implements BlockingQueue<E>, Serializable {
    private Node<E> head;

    private Node<E> last;

    private final int capacity;


    private final ReentrantLock takeLock = new ReentrantLock();


    private final Object putSyn = new Object();
    private final Object takeSyn = new Object();

    private volatile AtomicInteger size = new AtomicInteger(0);


    public SynBlockQueue() {
        this(Integer.MIN_VALUE);
    }

    public SynBlockQueue(int capacity) {
        this.capacity = capacity;
    }

    public static void main(String[] args) {
//        LinkedBlockingQueue
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return size.get();
    }

    @Override
    public void put(E e) throws InterruptedException {
        if (Objects.isNull(e)) {
            throw new NullPointerException();
        }
        Object putSynT = this.putSyn;
        Object takeSyncT = takeSyn;
        AtomicInteger sizeTemp = size;

        synchronized (putSynT) {
            while (sizeTemp.get() >= capacity) {
                putSynT.wait();
            }
            Node<E> headTemp = head;
            head = new Node<>(e, headTemp);
            sizeTemp.incrementAndGet();
        }
        synchronized (takeSyncT) {
            takeSyncT.notifyAll();
        }
    }

    @Override
    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        if (Objects.isNull(e)) {
            throw new NullPointerException();
        }
        Object putSynT = this.putSyn;
        Object takeSyncT = takeSyn;
        AtomicInteger sizeTemp = size;

        if (sizeTemp.get() >= capacity) {
            return false;
        }
        synchronized (putSynT) {
            if (sizeTemp.get() >= capacity) {
                return false;
            }
            Node<E> headTemp = head;
            head = new Node<>(e, headTemp);
            sizeTemp.incrementAndGet();
        }
        synchronized (takeSyncT) {
            takeSyncT.notifyAll();
        }
        return true;
    }

    @Override
    public E take() throws InterruptedException {
        Object putSync = putSyn;
        Object takeSyncT = takeSyn;
        AtomicInteger sizeTemp = size;
        E returnValue = null;
        synchronized (takeSyncT) {
            while (sizeTemp.get() <= 0) {
                takeSyncT.wait();
            }

            Node<E> headTemp = head;
            head = headTemp.getNext();
            sizeTemp.decrementAndGet();
            returnValue = headTemp.getValue();
        }
        synchronized (putSync) {
            putSync.notifyAll();
        }
        return returnValue;
    }

    @Override
    public E poll() {
        Object putSync = putSyn;
        Object takeSyncT = takeSyn;
        AtomicInteger sizeTemp = size;
        E returnValue;
        if (sizeTemp.get() <= 0) {
            return null;
        }
        synchronized (takeSyncT) {
            if (sizeTemp.get() <= 0) {
                return null;
            }
            Node<E> headTemp = head;
            head = headTemp.getNext();
            sizeTemp.decrementAndGet();
            returnValue = headTemp.getValue();
        }
        synchronized (putSync) {
            putSync.notifyAll();
        }
        return returnValue;
    }


    @Override
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public int remainingCapacity() {
        return 0;
    }

    @Override
    public int drainTo(Collection<? super E> c) {
        return 0;
    }

    @Override
    public int drainTo(Collection<? super E> c, int maxElements) {
        return 0;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }


    @Override
    public E peek() {
        return null;
    }
}
