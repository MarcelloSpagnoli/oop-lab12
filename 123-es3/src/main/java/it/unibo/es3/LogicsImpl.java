package it.unibo.es3;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics{
    
    private Set<Pair<Integer, Integer>> marked;
    private final int size;

    LogicsImpl(final int size) {
        marked = new HashSet<>();
        this.size = size;
        final var rdm = new Random(0);
        do {
            marked.add(new Pair<>(rdm.nextInt(size), rdm.nextInt(size)));
        } while (marked.size() < 3);
    }

    public void nextStep() {
        final var linear = List.of(-1, 0, 1);
            final List<Pair<Integer, Integer>> nearby = linear.stream()
            .flatMap(x ->
                linear.stream().map(y -> new Pair<>(x, y))
                    // .filter(delta -> delta.getX() != 0 && delta.getY() != 0)
            )
            .toList();
        marked = marked.stream()
            .flatMap(marked ->
                nearby.stream().map(delta -> new Pair<>(marked.getX() + delta.getX(), marked.getY() + delta.getY()))
            )
            .filter(point -> point.getX() < size && point.getX() >= 0 && point.getY() < size && point.getY() >= 0)
            .collect(Collectors.toSet());

        // Set<Pair<Integer, Integer>> newMarked = new HashSet<>();
        // for (int i = 0; i < this.size; i++) {
        //     for(int j = 0; j < this.size; j++) {
        //         var newPair = new Pair<Integer,Integer>(j, i);
        //         if (marked.stream().anyMatch(x -> areNear(newPair, x))) {
        //             newMarked.add(newPair);
        //         }
        //         /*for (Pair<Integer,Integer> markedPair : this.marked) {
        //             if (areNear(newPair, markedPair)) {
        //                 newMarked.add(newPair);
        //                 break;
        //             }
        //         }*/
        //     }
        // }
        // this.marked.addAll(newMarked);
    }

    public boolean toQuit() {
        return marked.size() == this.size * this.size;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public Set<Pair<Integer, Integer>> getMarked() {
        return Collections.unmodifiableSet(marked);
    }
}