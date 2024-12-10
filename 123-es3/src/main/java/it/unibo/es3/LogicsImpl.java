package it.unibo.es3;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics{
    
    Set<Pair<Integer, Integer>> marked;
    private int size;

    LogicsImpl(final int size) {
        marked = new HashSet<>();
        this.size = size;
        var rdm = new Random();
        do {
            Pair<Integer, Integer> newMarked = new Pair<>(rdm.nextInt(size), rdm.nextInt(size));
            marked.add(newMarked);
        } while (marked.size() < 3);
    }

    public void nextStep() {
        Set<Pair<Integer, Integer>> newMarked = new HashSet<>();
        for (int i = 0; i < this.size; i++) {
            for(int j = 0; j < this.size; j++) {
                var newPair = new Pair<Integer,Integer>(j, i);
                if (marked.stream().anyMatch(x -> areNear(newPair, x))) {
                    newMarked.add(newPair);
                }
                /*for (Pair<Integer,Integer> markedPair : this.marked) {
                    if (areNear(newPair, markedPair)) {
                        newMarked.add(newPair);
                        break;
                    }
                }*/
            }
        }
        this.marked = newMarked;
    }

    public boolean toQuit() {
        return marked.size() == Math.pow(size, 2);
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public Set<Pair<Integer, Integer>> getMarked() {
        return Set.copyOf(marked);
    }

    private Boolean areNear(Pair<Integer,Integer> pair1, Pair<Integer,Integer> pair2) {
        return Math.pow(pair1.getX() - pair2.getX(), 2) <= 1 && Math.pow(pair1.getY() - pair2.getY(), 2) <= 1 ;
    }

}