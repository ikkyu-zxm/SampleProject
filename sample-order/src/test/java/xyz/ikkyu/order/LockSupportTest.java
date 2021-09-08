package xyz.ikkyu.order;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author by xinming
 * @Classname LockSupportTest
 * @Description
 * @Date 2021/8/31 16:37
 */
public class LockSupportTest {

    @Test
    public void test() {

        Object o = new Object();

        Thread thread = new Thread(() -> {
            try {
                System.out.println("子线程-------start");
                Thread.sleep(3000);
                System.out.println("子线程尝试获取锁");
                synchronized (o) {
                    System.out.println("子线程获取到锁");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        synchronized (o) {
            System.out.println("主线程-----start");
            LockSupport.park();
            System.out.println("主线程-----end");
        }
    }


    private static AtomicStampedReference<Integer> atomicStampedRef =
            new AtomicStampedReference<>(1, 0);

    public static void main(String[] args) {
        Thread main = new Thread(() -> {
            System.out.println("操作线程" + Thread.currentThread() + ",初始值 a = " + atomicStampedRef.getReference());
            int stamp = atomicStampedRef.getStamp(); //获取当前标识别
            try {
                Thread.sleep(1000); //等待1秒 ，以便让干扰线程执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //此时expectedReference未发生改变，但是stamp已经被修改了,所以CAS失败
            boolean isCASSuccess = atomicStampedRef.compareAndSet(1, 2, stamp, stamp + 1);
            System.out.println("操作线程" + Thread.currentThread() + ",CAS操作结果: " + isCASSuccess);
        }, "主操作线程");

        Thread other = new Thread(() -> {
            Thread.yield(); // 确保thread-main 优先执行
//            System.out.println("操作线程" + Thread.currentThread() + "开始执行");
            System.out.println("操作线程" + Thread.currentThread() + ",【increment】 ,值 = " + atomicStampedRef.getReference());
            atomicStampedRef.compareAndSet(1, 2, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
            System.out.println("操作线程" + Thread.currentThread() + ",【increment】 ,值 = " + atomicStampedRef.getReference());
            atomicStampedRef.compareAndSet(2, 1, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
            System.out.println("操作线程" + Thread.currentThread() + ",【decrement】 ,值 = " + atomicStampedRef.getReference());
        }, "干扰线程");

        main.start();
        other.start();
    }


    class MyThread extends Thread {
        private Lock lock;

        public MyThread(String name, Lock lock) {
            super(name);
            this.lock = lock;
        }

        public void run() {
            lock.lock();
            System.out.println(Thread.currentThread() + "获取到锁");
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread() + " running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


    @Test
    public void reentrantLockTest() throws InterruptedException {
        Lock lock = new ReentrantLock();
        MyThread t1 = new MyThread("t1", lock);
        MyThread t2 = new MyThread("t2", lock);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }


    @Test
    public void conditionTest() {
        Depot depot = new Depot(500);
        new Producer(depot).produce(500);
        new Producer(depot).produce(200);
        new Consumer(depot).consume(500);
        new Consumer(depot).consume(200);
    }
}




class Depot {
    private int size;
    private int capacity;
    private Lock lock;
    private Condition fullCondition;
    private Condition emptyCondition;

    public Depot(int capacity) {
        this.capacity = capacity;
        lock = new ReentrantLock();
        fullCondition = lock.newCondition();
        emptyCondition = lock.newCondition();
    }

    public void produce(int no) {
        lock.lock();
        int left = no;
        try {
            while (left > 0) {
                while (size >= capacity)  {
                    System.out.println(Thread.currentThread() + " before await");
                    fullCondition.await();
                    System.out.println(Thread.currentThread() + " after await");
                }
                int inc = (left + size) > capacity ? (capacity - size) : left;
                left -= inc;
                size += inc;
                System.out.println("produce = " + inc + ", size = " + size);
                emptyCondition.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consume(int no) {
        lock.lock();
        int left = no;
        try {
            while (left > 0) {
                while (size <= 0) {
                    System.out.println(Thread.currentThread() + " before await");
                    emptyCondition.await();
                    System.out.println(Thread.currentThread() + " after await");
                }
                int dec = (size - left) > 0 ? left : size;
                left -= dec;
                size -= dec;
                System.out.println("consume = " + dec + ", size = " + size);
                fullCondition.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }



}

class Consumer {
    private Depot depot;
    public Consumer(Depot depot) {
        this.depot = depot;
    }

    public void consume(int no) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                depot.consume(no);
            }
        }, no + " consume thread").start();
    }
}

class Producer {
    private Depot depot;
    public Producer(Depot depot) {
        this.depot = depot;
    }

    public void produce(int no) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                depot.produce(no);
            }
        }, no + " produce thread").start();
    }
}

