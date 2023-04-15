package rockets;

import crystals.Crystals;
import crystals.TypeOfCrystals;

import java.util.Deque;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Rocket extends Thread {
    private CountDownLatch latch;
    private BlockingQueue<TypeOfCrystals> list;
    private TypeOfCrystals type;

    private static AtomicInteger rocketCounter = new AtomicInteger(0);

    private static AtomicBoolean flagIn = new AtomicBoolean(false);

    private CyclicBarrier barrier;

    public Rocket(CountDownLatch latch, Crystals crystals, TypeOfCrystals type, CyclicBarrier barrier) {
        this.latch = latch;
        this.list = crystals.getListOfCrystals();
        this.type = type;
        this.barrier = barrier;
    }

    public void run(){

        try {
            list.stream().filter(elem -> elem == type ).forEach(elem -> {
                latch.countDown();
            });

            if(!flagIn.get()){
                flagIn.set(true);
                rocketCounter.incrementAndGet();
            }

            barrier.await();

            flagIn.set(false);
            if(latch.getCount() != 0){
                System.out.println("Rocket-" + rocketCounter + " take " + type +
                        " crystal, " + " crystals, now left " + latch.getCount());
            }

            barrier.reset();


        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
