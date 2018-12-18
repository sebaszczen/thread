package lowLeverSynchronization;

import java.util.LinkedList;
import java.util.Scanner;

public class ProducerConsumer {

    Object lock = new Object();
    int limit=10;
    LinkedList linkedList = new LinkedList();

    public void produce() {
        int value=0;
        while (true) {
            synchronized (lock) {
                while (linkedList.size()==limit){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                value++;
                linkedList.add(value);
                lock.notify();
            }
        }

    }

    public void consume() {
        while (true) {
            synchronized (lock) {
                System.out.println("rozmiar listy: "+linkedList.size());
                while (linkedList.size() == 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                linkedList.removeFirst();
                System.out.println("rozmiar listy: "+linkedList.size());
                lock.notify();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
