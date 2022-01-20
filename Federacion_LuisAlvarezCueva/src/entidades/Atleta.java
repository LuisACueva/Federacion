package entidades;

import java.util.Scanner;

import utils.Datos;
import utils.Utilidades;

public class Atleta extends Participante {
	private long idAtleta;
	private float altura;
	private float peso;

	private DatosPersona persona;

	public Atleta(long id, int dorsal, char calle, long idAtleta, float altura, float peso) {
		super(id, dorsal, calle);
		this.idAtleta = idAtleta;
		this.altura = altura;
		this.peso = peso;
		this.persona = Datos.buscarPersonaPorId(id);
	}

	public Atleta(long id, int dorsal, char calle, long idAtleta, float altura, float peso, DatosPersona dp) {
		super(idAtleta, dorsal, calle);
		this.idAtleta = idAtleta;
		this.altura = altura;
		this.peso = peso;
		this.persona = dp;
	}

	public Atleta(long idAtleta, float altura, float peso, DatosPersona dp) {
		super();
		this.idAtleta = idAtleta;
		this.altura = altura;
		this.peso = peso;
		this.persona = dp;
	}

	public Atleta(long idParticipante, Atleta a, int dorsal, char calle) {
		super(idParticipante, dorsal, calle);
		this.idAtleta = a.idAtleta;
		this.altura = a.altura;
		this.peso = a.peso;
		this.persona = Datos.buscarPersonaPorId(a.idAtleta);
	}

	@Override
	public long getId() {
		return idAtleta;
	}

	@Override
	public void setId(long id) {
		this.idAtleta = id;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public DatosPersona getPersona() {
		return this.persona;
	}

	/**
	 * Este método permite transformar la información del Atleta como una cadena de texto.
	 * 
	 * @return una cadena de caracteres (String)
	 */
	@Override
	public String toString() {
		return this.getPersona().getNombre() + " (" + this.getPersona().getNifnie().mostrar() + ") "
				+ "del año " + this.getPersona().getFechaNac().getYear() + "\t" + this.getPeso() + " Kgs. "
				+ this.getAltura() + " m.";
	}

	/**
	 * Este método permite crear un nuevo objeto de tipo Atleta, gracias a datos metidos
	 * por el usuario mediante el teclado. Este método valida los datos aportados.
	 * 
	 * @return un objeto de la clase Atleta
	 */
	public static Atleta nuevoAtleta() {
		Scanner sc = new Scanner(System.in);
		long nid;
		float naltura;
		float npeso;
		boolean valido = false;
		DatosPersona dp;

		do {
			System.out.println("Introduzca el id del atleta:");
			nid = sc.nextLong();
			if (nid > 0)
				valido = true;
		} while (!valido);
		valido = false;

		System.out.println("Introduzca la altura del atleta:");
		naltura = Utilidades.leerFloat();
		System.out.println("Introduzca el peso del atleta:");
		npeso = Utilidades.leerFloat();

		dp = DatosPersona.nuevaPersona();
		
		return new Atleta(nid, naltura, npeso, dp);
	}

}
