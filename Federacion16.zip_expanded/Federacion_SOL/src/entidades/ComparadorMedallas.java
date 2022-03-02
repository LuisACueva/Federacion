package entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import utils.Datos;

public class ComparadorMedallas implements Comparator<Metal> {

	@Override
	public int compare(Metal o1, Metal o2) {
		if (o1.getPureza() > o2.getPureza()) {
			return 1;
		} else if (o1.getPureza() < o2.getPureza()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	/**
	 * Imprime por pantalla todas las medallas por orde de su puereza
	 */
	public static void printMedallasInOrder() {
		ArrayList arl = new ArrayList();
		Iterator <Metal> itr = arl.iterator();
		
		for(Metal a: Datos.OROS) {
			arl.add(a);
		}
		
		for(Metal a: Datos.PLATAS) {
			arl.add(a);
		}
		
		for(Metal a: Datos.BRONCES) {
			arl.add(a);
		}
		
		Collections.sort(arl);
		
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

}
