import crystals.Crystals;
import crystals.TypeOfCrystals;
import mages.Magicians;
import rockets.Rocket;

import java.io.Serial;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
       var pool = Executors.newFixedThreadPool(4);
        CyclicBarrier barrier = new CyclicBarrier(2);

        CountDownLatch airLatch = new CountDownLatch(500);
        CountDownLatch fireLatch = new CountDownLatch(500);


        Magicians blueMag = new Magicians(airLatch, fireLatch, "BLUE");
        Magicians redMag = new Magicians(fireLatch, airLatch, "RED");

        pool.submit(blueMag);
        pool.submit(redMag);

        AtomicInteger upperBound = new AtomicInteger(1000);

        while(upperBound.get() != 0){
            var crystals = new Crystals();

            pool.submit(new Rocket(airLatch, crystals, TypeOfCrystals.BLUE,  barrier));
            pool.submit(new Rocket(fireLatch, crystals, TypeOfCrystals.RED,  barrier));


            upperBound.decrementAndGet();

        }

        pool.shutdown();
        pool.awaitTermination(2L, TimeUnit.MINUTES);
        System.out.println("End!");

    }
}