package notifyAndWait;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ProducerConsumer {

    public void produce() {
        synchronized (this) {
            System.out.println("watek stworzony");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("watek dokoncziny");
        }

    }

    public void consume() {
        synchronized (this) {
            Scanner scanner = new Scanner(System.in);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("waiting for key");
            scanner.nextLine();
            System.out.println("klikniete");
            notify();

        }
    }
}
