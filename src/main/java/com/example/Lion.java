package com.example;

import java.util.List;

public class Lion {

    boolean hasMane;
    private final FelinePredator felinePredator;


    public Lion(String sex, FelinePredator felinePredator) throws Exception {
        this.felinePredator = felinePredator;
        if ("Самец".equals(sex)) {
            hasMane = true;
        } else if ("Самка".equals(sex)) {
            hasMane = false;
        } else {
            throw new Exception("Используйте допустимые значения пола животного - самей или самка");
        }
    }


    public int getKittens() {
        return felinePredator.getKittens();
    }

    public boolean doesHaveMane() {
        return hasMane;
    }

    public List<String> getFood() throws Exception {
        return felinePredator.eatMeat();
    }
}
