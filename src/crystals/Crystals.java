package crystals;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.random.RandomGenerator;

public class Crystals {

    private BlockingQueue<TypeOfCrystals> listOfCrystals;

    public Crystals(){
        listOfCrystals = new LinkedBlockingDeque<>();
        setListOfCrystals();
    }
    private  void setListOfCrystals(){
        var countOfAllCrystals = new Random().nextInt(4) + 2;
        for(int i = 0; i < countOfAllCrystals; i++){
            var elem = new Random().nextInt(TypeOfCrystals.values().length) +1;
            TypeOfCrystals crystal = TypeOfCrystals.values()[elem-1];
            listOfCrystals.add(crystal);
        }
    }

    public BlockingQueue<TypeOfCrystals> getListOfCrystals(){
        return listOfCrystals;
    }


}
