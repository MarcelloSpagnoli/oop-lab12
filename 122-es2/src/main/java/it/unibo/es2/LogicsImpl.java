package it.unibo.es2;

import java.util.*;
import java.util.stream.IntStream;

public class LogicsImpl implements Logics{

    List<List<Boolean>> map;

    public LogicsImpl(final Integer size) {
        // this.map = new ArrayList<>(Collections.nCopies(size, new ArrayList<>(Collections.nCopies(size, " "))));
        /* Non funziona perché non vengono create copie,
         * ma tutti gli oggetti puntano alla stessa lista
         * tanto è vero che modificando un elemento si modificava tutta
         * la colonna 
        */ 
        this.map = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            this.map.add(new ArrayList<>(Collections.nCopies(size, false)));
        }
    }

    @Override
    public Boolean hit(Pair<Integer, Integer> coord) {
        setCell(coord);
        return getCell(coord);
    }

    @Override
    public Boolean toQuit() {
        boolean lines = map.stream()
            .anyMatch(x -> x.stream().allMatch(y -> y));

        boolean columns = IntStream.iterate(0, x -> x+1)
            .limit(map.size())
            .anyMatch(x -> map.stream().allMatch(y -> y.get(x)));
        
        return lines || columns;
    }

    private Boolean getCell(Pair<Integer, Integer> coord) {
        return map.get(coord.getX()).get(coord.getY());
    }

    private void setCell(Pair<Integer, Integer> coord) {
        final Boolean ch = getCell(coord);
        map.get(coord.getX()).set(coord.getY(), !ch);
    }
    
}
