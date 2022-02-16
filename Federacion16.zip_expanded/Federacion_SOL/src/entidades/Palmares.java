package entidades;

public class Palmares<T extends Metal, S extends Participante> {
	private long id;
	private T medalla;
	private S participante;
	private Prueba prueba;
	private String observaciones;

	public Palmares() {

	}

	public Palmares(long id, T medalla, S participante, Prueba prueba, String observaciones) {
		this.id = id;
		this.medalla = medalla;
		this.participante = participante;
		this.prueba = prueba;
		this.observaciones = observaciones;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public T getMedalla() {
		return medalla;
	}

	public void setMedalla(T medalla) {
		this.medalla = medalla;
	}

	public S getParticipante() {
		return participante;
	}

	public void setParticipante(S participante) {
		this.participante = participante;
	}

	public Prueba getPrueba() {
		return prueba;
	}

	public void setPrueba(Prueba prueba) {
		this.prueba = prueba;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	/**
	 * Muestra 
	 * 
	 * @return Cadena de caracteres con los datos pedidos del Palmares
	 */
	public void mostrarString() {
		String ret = "Palmares [id=" + id + ", medalla=" + medalla.toString() + ", prueba=" + prueba.getNombre()
				+ prueba.getFecha() + prueba.getLugar() + ", participante=" + participante.getDorsal()
				+ participante.getCalle();
		
		if(this.getParticipante().getClass()==Equipo.class) {
			for(Atleta a: ((Equipo)participante).getAtletas()) {
				ret += a.toString();
			}
		}else {
			ret += this.getParticipante().toString();
		}
		System.out.println(ret);
	}

}
