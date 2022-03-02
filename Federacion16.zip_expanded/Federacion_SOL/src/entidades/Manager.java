package entidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;
import utils.Datos;
import validaciones.Validaciones;

public class Manager {
	private long id;
	private String telefono;
	private String direccion;

	private DatosPersona persona;

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

	// Examen 5 Ejercicio 4
	/***
	 * Función que pregunta al usuario por cada uno de los campos para un nuevo
	 * Manager, los valida y si son correctos devuelve un objeto Manager completo
	 * 
	 * @return un objeto Manager completo válido o null si hubo algún error
	 */
	public static Manager nuevoManager() {
		Manager ret = null;
		long id = -1;
		String telefono = "";
		String direccion = "";
		DatosPersona dp = null;
		Scanner in;
		boolean valido = false;
		do {
			System.out.println("Introduzca el id del nuevo mánager:");
			in = new Scanner(System.in);
			id = in.nextInt();
			if (id > 0)
				valido = true;
			else
				System.out.println("Valor incorrecto para el identificador.");
		} while (!valido);

		valido = false;
		do {
			in = new Scanner(System.in);
			System.out.println("Introduzca el telefono de empresa del nuevo mánager");
			telefono = in.nextLine();
			valido = Validaciones.validarTelefono(telefono);
			if (!valido)
				System.out.println("ERROR: El valor introducido para el teéfono no es válido.");
		} while (!valido);

		valido = false;
		do {
			in = new Scanner(System.in);
			System.out.println("Introduzca la dirección del nuevo mánager:");
			direccion = in.next();
			valido = Validaciones.validarDireccion(direccion);
			if (!valido)
				System.out.println("ERROR: El valor introducido para la dirección no es válido.");
		} while (!valido);

		System.out.println("Introduzca ahora los datos personales:");
		in = new Scanner(System.in);
		dp = DatosPersona.nuevaPersona();

		ret = new Manager(id, telefono, direccion, dp);
		return ret;
	}

	/***
	 * Función que devuelve una cadena de caracteres con los datos del mánager en el
	 * siguiente formato: <idManager> <nombre> ” (” <documentacion> ”) del año ”
	 * <fechaNac.año> ” Tfno1: <Manager.telefono>” ,Tfno2: ” <DatosPersona.telefono>
	 */
	@Override
	public String toString() {
		return "" + id + ". " + persona.getNombre() + " (" + persona.getNifnie().mostrar() + ") del año "
				+ persona.getFechaNac().getYear() + " Tfno1: " + telefono + " , Tfno2:" + persona.getTelefono() + "]";
	}

	public String data() {

		return this.getPersona().getId() + "|" + this.getPersona().getNombre() + "|" + this.getPersona().getNifnie()
				+ "|" + this.getPersona().getFechaNac() + "|" + this.getPersona().getTelefono() + "|" + this.id + "|"
				+ this.telefono + "|" + this.direccion;
	}

	/**
	 * Este método almacena los datos producidos por la funcion data() en un String
	 * que luego se escribran en el archivo managers.txt
	 * 
	 * @param listManager
	 * @throws IOException
	 */
	public void exportData(Manager[] listManager) throws IOException {
		String datos = null;

		for (Manager i : listManager) {
			datos += i.data() + "\n";
		}

		File file = new File("managers.txt");
		file.createNewFile();
		FileWriter FW = new FileWriter(file, true);
		BufferedWriter BW = new BufferedWriter(FW);
		PrintWriter PW = new PrintWriter(BW);

		PW.print(datos);

	}

	public static void mapear() {
		HashMap<Documentacion, Equipo> mapManager = new HashMap<Documentacion, Equipo>();
		
		File file = new File("managers.txt");
		String line;
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String[] campos = new String[8];
			if(file.exists()) {
				 while ((line = br.readLine()) != null) {     
					 campos = br.readLine().split("|");
					 Manager mng = null;
					 Equipo eqp = null;
					 for(Manager a: Datos.MANAGERS) {
						 if(a.getPersona().getNifnie().toString() == campos[2]) {
							 mng = a;
							 break;
						 }
					 }
					 
					 for(Equipo a: Datos.EQUIPOS) {
						 if(a.getManager() == mng) {
							 eqp = a;
							 break;
						 }						 
					 }
					 mapManager.put(mng.getPersona().getNifnie(), eqp);
			        }
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void importData() throws Exception {
		File fl = new File("managers.txt");
		FileReader fr = new FileReader(fl);
		int c;
		String lineaDatos = null;

		while ((c = fr.read()) != -1) {
			lineaDatos += (char) c;
			if (c == 10) {
				String id = null;
				for (int i = 0; i < lineaDatos.length(); i++) {
					if (lineaDatos.charAt(i) != '|') {
						id += lineaDatos.charAt(i);
					}else {
						for(Manager a: Datos.MANAGERS) {
							if(a.getId()==Long.parseLong(id)) {
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM uuuu");
								System.out.println("D./Dña. "+a.getPersona().getNombre()
										+" con NIF:NIE "+a.getPersona().getNifnie()
										+" nacido el "+a.getPersona().getFechaNac()
										);
								break;
							}
						}
					}
				}
			}
		}
	}
}
