package entidades;

import java.util.Scanner;

import utils.Datos;
import validaciones.Validacion;

public class Manager {
	private long id;
	private String telefono;
	private String direccion;

	private DatosPersona persona;

	private Manager() {
		
	}
	
	public Manager(long id, String telefono, String direccion) {
		super();
		this.id = id;
		this.telefono = telefono;
		this.direccion = direccion;
		this.persona = Datos.buscarPersonaPorId(id);
	}

	public Manager(long id, String telefono, String direccion, DatosPersona dp) {
		super();
		this.id = id;
		this.telefono = telefono;
		this.direccion = direccion;
		this.persona = dp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public DatosPersona getPersona() {
		return this.persona;
	}
	
	public static Manager nuevoManager() {
		Manager ret;
		boolean valido = false;
		long nid;
		String tel;
		String dir;
		DatosPersona dp;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("Introduzca el id de la persona");
			nid = sc.nextLong();
			if(nid < 0)
				valido = true;
		}while(!valido);
		valido = false;
		do {
			System.out.println("Introduzca el telefono de la persona");
			tel = sc.next();
			if(Validacion.validarTelefono(tel))
				valido = true;
		}while(!valido);
		valido = false;
		System.out.println("Introduzca la direccion de la persona");
		dir = sc.next();
		dp = DatosPersona.nuevaPersona();
		
		ret = new Manager(nid, tel, dir, dp);
		sc.close();
		return ret;
	}

	@Override
	public String toString() {
		return id + " " + this.getPersona().getNombre() + " (" + this.getPersona().getNifnie().mostrar()
				+ ") del año" + this.getPersona().getFechaNac().getYear() + " Tfno1: " + this.getTelefono()
				+ ", Tfno2: " + this.getPersona().getTelefono();
	}

	
}
