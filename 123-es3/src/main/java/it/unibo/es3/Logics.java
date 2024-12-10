package it.unibo.es3;

import java.util.Set;

public interface Logics {
    
    void nextStep();

    Set<Pair<Integer, Integer>> getMarked();

    boolean toQuit();

    int getSize();
} 
