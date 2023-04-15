package mages;

import java.util.concurrent.CountDownLatch;

public class Magicians extends Thread{
    private CountDownLatch current;
    private CountDownLatch another;

    public Magicians(CountDownLatch current, CountDownLatch another, String name) {
        super(name);
        this.current = current;
        this.another = another;
    }

    public void run(){
        try {
            current.await();
            System.out.println("run magicians");

            if(another.getCount() == 0 || Thread.activeCount() == 1){
                System.out.println(getName() + " magicians is LOSE!");
            }
            else{
                System.out.println(getName() + " magicians is WIN!");
            }

            System.out.println("end magicians");

            Thread.currentThread().interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
