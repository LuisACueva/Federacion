package entidades;

import java.util.Comparator;

public class ComparadorMedallas implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		if (o1.equals(o2)) {
			return 1;
		} else
			return 0;
	}

}
