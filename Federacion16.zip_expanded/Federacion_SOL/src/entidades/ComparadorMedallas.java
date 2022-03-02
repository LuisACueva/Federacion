package entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import utils.Datos;

public class ComparadorMedallas implements Comparator<Metal> {

	@Override
	public int compare(Metal o1, Metal o2) {
		return Math.round(o1.getPureza()-o2.getPureza());
	}
	
	/**
	 * Imprime por pantalla todas las medallas por orde de su puereza
	 */
	public static void printMedallasInOrder() {
		LinkedList arl = new LinkedList();
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
		
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
	
	}

}

