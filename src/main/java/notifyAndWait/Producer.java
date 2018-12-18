package notifyAndWait;

public class Producer {

    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer();
        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                producerConsumer.produce();
            }
        });

        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                producerConsumer.consume();
            }
        });

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
