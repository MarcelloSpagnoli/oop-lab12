package it.unibo.es1;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.List;

public class LogicsImpl implements Logics {

	// private Integer size; 
	private List<Integer> values;

	public LogicsImpl(final int size) {
		// this.size = size; --> inferable from values.size
		this.values = new ArrayList<>(Collections.nCopies(size, 0));
		// for (int i = 0; i < size; i++) {
		// 	values.add(0);
		// }
	}

	@Override
	public int size() {
		return this.values.size();
	}

	@Override
	public List<Integer> values() {
		return values;
	}

	@Override
	public List<Boolean> enablings() {
		return values().stream()
			.map(x -> x < this.values.size())
			.toList();
	}

	@Override
	public int hit(final int elem) {
		Integer i = values.get(elem);
		i++;
		values.set(elem, i);
		return i;
	}

	@Override
	public String result() {
		return values.stream()
			.map(x -> x.toString())
			.collect(Collectors.joining("|", "<<", ">>"));
	}

	@Override
	public boolean toQuit() {
		return values.stream()
			.allMatch(x -> x == values.getFirst());
	}
}
