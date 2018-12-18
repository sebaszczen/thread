package asdf;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Proccessor extends Thread {

    private CountDownLatch latch;
    int x;

    public Proccessor(CountDownLatch latch, int value) {
        this.latch = latch;
        this.x = value;
    }

    @Override
    public void run() {
        System.out.println("started "+x);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(22);
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 100; i++) {
            executorService.submit(new Proccessor(countDownLatch,i));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("completed");
    }
}
