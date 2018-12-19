package reentrantLocks;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {

    ReentrantLock reentrantLock = new ReentrantLock();
    Condition condition=reentrantLock.newCondition();
    int count=0;

    public void count() {
        for (int i = 0; i < 1000; i++) {
            count++;
        }
    }

    public void produce() throws InterruptedException {
        reentrantLock.lock();
        System.out.println("sleeeping");
        condition.await();
        System.out.println("awaken");
        try {
            count();
        }
        finally {
            reentrantLock.unlock();
        }
    }

    public void consume() throws InterruptedException {
//        Thread.sleep(1000);
        reentrantLock.lock();

        System.out.println("press return key");
        new Scanner(System.in).nextLine();
        System.out.println("key pressed");
        condition.signal();
        try {
            count();
        }
        finally {
            reentrantLock.unlock();
        }
    }

    public void finish() {
        System.out.println("liczba koncowa: "+count);
    }
}
