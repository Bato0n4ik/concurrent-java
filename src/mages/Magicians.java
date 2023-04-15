package mages;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

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

            if(another.getCount() == 0 || Thread.activeCount() == 1){
                System.out.println(getName() + " magicians is LOSE!");
            }
            else{
                System.out.println(getName() + " magicians is WIN!");
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
