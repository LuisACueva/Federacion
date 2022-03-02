package principal;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import entidades.*;
import utils.*;
import validaciones.Validaciones;

public class Principal4 {

	public static void main(String[] args) {
		Datos.cerrarResultados();
		System.out.println("INICIO DEL PROGRAMA");
		ComparadorMedallas.printMedallasInOrder();
		System.out.println("INICIO DEL PROGRAMApppppppppppppppppppppppppppppppppppppppppppppppppp");
		Scanner in;
		int elecc = -1;
		Rol rol; // Examen 4 Ejercicio 3A
		boolean correcto = false;
		while (true) {
			System.out.println("Bienvenido al programa de gestión de la FEDERACIÓN DEPORTIVA:");
			do {
				System.out.println("Seleccione el id del tipo de usuario o pulse 0 para SALIR:");
				int i = 1;
				for (Rol r : Rol.values()) {
					System.out.println(i + " " + r.getNombre());
					i++;
				}
				in = new Scanner(System.in);
				elecc = in.nextInt();
				if (elecc == 0) {
					System.out.println("¿Está seguro de que desea SALIR?");
					if (Utilidades.leerBoolean()) {
						System.out.println("¡ADIOS!");
						return;
					}
				}
				if (elecc >= 1 && elecc <= Rol.values().length)
					correcto = true;
				else
					System.out.println("¡Valor invalido para el ROL seleccionado!");
			} while (!correcto);
			rol = Rol.values()[elecc - 1];

			Credenciales cred; // Examen 4 Ejericicio 3B
			boolean login = false;
			switch (rol.ordinal()) {
			case 0: // Rol.DIRECTIVA;
			case 1: // Rol.MANAGER
			case 2: // Rol.ATLETA;
			case 3: // Rol.COLEGIADO;
			case 4: // Rol.ADMIN;
				do {
					System.out.println("Introduzca sus credenciales de acceso.");
					System.out.println("Introduzca su nombre de usuario:");
					String usuario, password;
					usuario = in.next();
					System.out.println("Introduzca su contraseña:");
					password = in.next();
					cred = new Credenciales(usuario, password);
					// login = login(cred);
					if (!login)
						System.out.println("ERROR: Usuario o password incorrectos.");
					else
						System.out.println("Login correcto con rol " + rol.getNombre());
				} while (!login);
				break;
			case 5: // Rol.INVITADO;
				System.out.println("Ha ingresado con el rol " + rol.getNombre());
			}

			mostrarMenu(rol);
		}

	} // Final del main

	// Examen 3 Ejercicio 2 - Examen 4 Ejercicio 3C
	private static void mostrarMenu(Rol rol) {
		int elecc = -1;
		Scanner in = new Scanner(System.in);
		boolean valido = false;

		switch (rol.ordinal()) {
		case 0: // Rol.DIRECTIVA;
			do {
				mostrarMenuDirectiva();
				System.out.println("Seleccione una de las opciones anteriores.");
				in = new Scanner(System.in);
				elecc = in.nextInt();
				if (elecc >= 0 && elecc <= 2) {
					valido = true;
					mostrarSubmenuDirectiva(elecc);
				} else
					System.out.println("¡Valor seleccionado no válido!\n");
			} while (!valido || elecc != 0);
			System.out.println("Ha elegido VOLVER.");
			break;
		case 1: // Rol.MANAGER;
			do {
				mostrarMenuManager();
				System.out.println("Seleccione una de las opciones anteriores.");
				in = new Scanner(System.in);
				elecc = in.nextInt();
				if (elecc >= 0 && elecc <= 2) {
					valido = true;
					mostrarSubmenuManager(elecc);
				} else
					System.out.println("¡Valor seleccionado no válido!\n");
			} while (!valido || elecc != 0);
			System.out.println("Ha elegido VOLVER");
			break;
		case 2: // Rol.ATLETA
			do {
				mostrarMenuAtleta();
				System.out.println("Seleccione una de las opciones anteriores.");
				in = new Scanner(System.in);
				elecc = in.nextInt();
				if (elecc >= 0 && elecc <= 2) {
					valido = true;
					try {
						mostrarSubmenuAtleta(elecc);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else
					System.out.println("¡Valor seleccionado no válido!\n");
			} while (!valido || elecc != 0);
			System.out.println("Ha elegido VOLVER.");
			break;
		case 3: // Rol.COLEGIADO;
			do {
				mostrarMenuColegiado();
				System.out.println("Seleccione una de las opciones anteriores.");
				in = new Scanner(System.in);
				elecc = in.nextInt();
				if (elecc >= 0 && elecc <= 2) {
					valido = true;
					mostrarSubmenuColegiado(elecc);
				} else
					System.out.println("¡Valor seleccionado no válido!\n");
			} while (!valido || elecc != 0);
			System.out.println("Ha elegido VOLVER.");
			break;
		case 4: // Rol.ADMIN;
			do {
				mostrarMenuAdmin();
				System.out.println("Seleccione una de las opciones anteriores.");
				in = new Scanner(System.in);
				elecc = in.nextInt();
				if (elecc >= 0 && elecc <= 4) {
					valido = true;
					mostrarSubmenuAdmin(elecc);
				} else
					System.out.println("¡Valor seleccionado no válido!\n");
			} while (!valido || elecc != 0);
			System.out.println("Ha elegido VOLVER.");
			break;
		case 5: // Rol.INVITADO;
			do {
				mostrarMenuInvitado();
				System.out.println("Seleccione una de las opciones anteriores.");
				in = new Scanner(System.in);
				elecc = in.nextInt();
				if (elecc >= 0 && elecc <= 2) {
					valido = true;
					mostrarSubmenuInvitado(elecc);
				} else
					System.out.println("¡Valor seleccionado no válido!\n");
			} while (!valido || elecc != 0);
			System.out.println("Ha elegido VOLVER.");
			break;
		default:
		}

	}

	private static void mostrarSubmenuDirectiva(int elecc) {
		Scanner in = new Scanner(System.in);
		int subelecc = -1;
		boolean valido = false;
		System.out.println("SUBMenús de la DIRECTIVA.");
		switch (elecc) {
		case 1:
			System.out.println("Ha seleccionado 1.1 GESTIÓN de MEDALLAS.");
			do {
				mostrarMenuGestionMedallas();
				System.out.println("Seleccione una de las opciones anteriores.");
				in = new Scanner(System.in);
				subelecc = in.nextInt();
				if (subelecc >= 0 && subelecc <= 2) {
					valido = true;
					mostrarSubmenuGestionMedallas(subelecc);
				} else
					System.out.println("¡Valor seleccionado no válido!\n");
			} while (!valido || subelecc != 0);
			System.out.println("Ha elegido VOLVER.");
			break;
		case 2:
			System.out.println("Ha seleccionado 1.2 GESTIÓN de COMPETICIONES y de PRUEBAS.");
			break;
		default:
		}

		System.out.println("Volviendo al menú de la DIRECTIVA...");
	}

	private static void mostrarMenuGestionMedallas() {
		System.out.println("Menú de GESTIÓN de MEDALLAS.");
		System.out.println("Seleccione una de las siguientes opciones:");
		System.out.println("1. Nueva Medalla\n" + "2. Ver Medallas\n" + "0. Volver");
	}

	private static void mostrarSubmenuGestionMedallas(int elecc) {
		Scanner in = new Scanner(System.in);
		int subelecc = -1;
		boolean valido = false;
		System.out.println("\nGESTIÓN de MEDALLAS.");
		switch (elecc) {
		case 1: // opción 1.1.1
			System.out.println("Ha seleccionado 1.1.1 Nueva MEDALLA.");
			do {
				System.out.println("Seleccione 1 para ORO, 2 para PLATA o 3 para BRONCE.");
				subelecc = in.nextInt();
				if (subelecc != 1 && subelecc != 2 && subelecc != 3)
					System.out.println("¡Valor seleccionado no válido!\n");
				else
					valido = true;
			} while (!valido);
			Metal nuevo;
			if (subelecc == 1) {
				System.out.println("Ha seleccionado Nuevo ORO");
				nuevo = Oro.nuevoOro();
			} else if (subelecc == 2) {
				System.out.println("Ha seleccionado Nueva PLATA");
				nuevo = (Plata) Plata.nuevoPlata();
			} else {
				System.out.println("Ha seleccionado Nuevo BRONCE");
				nuevo = (Bronce) Bronce.nuevoBronce();
			}
			System.out.println("Se ha introducido una nueva medalla correctamente.");
			System.out.println(nuevo);
			break;
		// Examen 5 Ejercicio 6
		case 2: // opción 1.1.2
			System.out.println("Ha seleccionado 1.1.2 Ver Medallas");
			boolean validar = false;
			boolean orosSi = false;
			boolean platasSi = false;
			boolean broncesSi = false;
			// Se pregunta al usuario por sus criterios de búsqueda
			do {
				System.out.println("Seleccione qué tipo de medallas filtrar:");
				System.out.println("¿Desea buscar Oros?");
				in = new Scanner(System.in);
				orosSi = Utilidades.leerBoolean();
				System.out.println("¿Desea buscar Platas?");
				in = new Scanner(System.in);
				platasSi = Utilidades.leerBoolean();
				System.out.println("¿Desea buscar Bronces?");
				in = new Scanner(System.in);
				broncesSi = Utilidades.leerBoolean();
				validar = orosSi || platasSi || broncesSi;
				if (!validar) {
					System.out.println("ERROR: Debe seleccionar al menos algún tipo de medallas.");
					continue;
				}
				System.out.print("Ha seleccionado buscar sobre " + (orosSi ? "oros " : " ")
						+ (platasSi ? "platas " : " ") + (broncesSi ? "bronces" : " "));
				System.out.println("\n¿Es correcto?");
				in = new Scanner(System.in);
				validar = Utilidades.leerBoolean();
			} while (!validar);

			System.out.println("¿Desea buscar por un rango de pureza?");
			boolean purezaSi = Utilidades.leerBoolean();
			float purezaMin = 0.0F;
			float purezaMax = 0.0F;
			if (purezaSi) {
				boolean purezaVal = false;
				do {
					System.out.println("Introduzca el valor para la pureza mínima:");
					purezaMin = Utilidades.leerFloat();
					purezaVal = Validaciones.validarPureza(purezaMin);
					if (!purezaVal)
						System.out.println("ERROR: Valor introducido para la pureza mínima no válido.");
				} while (!purezaVal);
				purezaVal = false;
				do {
					System.out.println("Introduzca el valor para la pureza máxima:");
					purezaMax = Utilidades.leerFloat();
					purezaVal = Validaciones.validarPureza(purezaMax);
					if (!purezaVal)
						System.out.println("ERROR: Valor introducido para la pureza máxima no válido.");
					else if (purezaMax < purezaMin) {
						System.out.println("ERROR: La pureza máxima debe ser mayor que la mínima.");
						purezaVal = false;
					}
				} while (!purezaVal);
			}

			System.out.println("¿Desea buscar por un rango de fechas?");
			boolean fechasSi = Utilidades.leerBoolean();
			LocalDate fechaMin = null;
			LocalDate fechaMax = null;
			if (fechasSi) {
				boolean fechaVal = false;
				do {
					System.out.println("Introduzca el valor para la fecha mínima:");
					fechaMin = Utilidades.leerFecha();
					fechaVal = Validaciones.validarFecha(fechaMin);
					if (!fechaVal)
						System.out.println("ERROR: Valor introducido para la fecha mínima no válido.");
				} while (!fechaVal);
				fechaVal = false;
				do {
					System.out.println("Introduzca el valor para la fecha máxima:");
					fechaMax = Utilidades.leerFecha();
					fechaVal = Validaciones.validarFecha(fechaMax);
					if (!fechaVal)
						System.out.println("ERROR: Valor introducido para la fecha máxima no válido.");
					else if (fechaMax.isBefore(fechaMin)) {
						System.out.println("ERROR: La fecha máxima debe ser posterior a la mínima.");
						fechaVal = false;
					}
				} while (!fechaVal);
			}

			System.out.println("¿Desea buscar solo entre medallas ya asignadas?");
			boolean asignadasOnly = Utilidades.leerBoolean();

			// Se prepara un array medallasFiltradas que se inicializa con todas las
			// medallas del sistema (primero Oros, luego Platas y por último Bronces
			int totalMedallas = Datos.OROS.length + Datos.PLATAS.length + Datos.BRONCES.length;
			Metal[] medallasFiltradas = new Metal[totalMedallas];
			for (int i = 0; i < totalMedallas;) {
				for (int j = 0; j < Datos.OROS.length; j++, i++)
					medallasFiltradas[i] = Datos.OROS[j];
				for (int j = 0; j < Datos.PLATAS.length; j++, i++)
					medallasFiltradas[i] = Datos.PLATAS[j];
				for (int j = 0; j < Datos.BRONCES.length; j++, i++)
					medallasFiltradas[i] = Datos.BRONCES[j];
			}
			// se filtra cada elemento de medallasFiltradas en función de los criterios de
			// búsqueda seleccionados
			int index = 0;
			if (orosSi) {
				for (int i = 0; i < Datos.OROS.length; i++, index++) {
					if (purezaSi) {
						float purezaElem = ((Oro) medallasFiltradas[index]).getPureza();
						if (purezaElem < purezaMin || purezaElem > purezaMax) {
							medallasFiltradas[index] = null;
							continue;
						}
					}
					if (fechasSi) {
						LocalDate fechaElem = medallasFiltradas[index].getFecha();
						if (fechaElem != null)
							if (fechaElem.isBefore(fechaMin) || fechaElem.isAfter(fechaMax)) {
								medallasFiltradas[index] = null;
								continue;
							}
					}
					if (asignadasOnly) {
						if (!medallasFiltradas[index].isAsignada()) {
							medallasFiltradas[index] = null;
							continue;
						}
					}
				}
			} else {
				for (int i = index; i < Datos.OROS.length; i++, index++) {
					medallasFiltradas[index] = null;
				}
			}
			if (platasSi) {
				for (int i = 0; i < Datos.PLATAS.length; i++, index++) {
					if (purezaSi) {
						float purezaElem = ((Plata) medallasFiltradas[index]).getPureza();
						if (purezaElem < purezaMin || purezaElem > purezaMax) {
							medallasFiltradas[index] = null;
							continue;
						}
					}
					if (fechasSi) {
						LocalDate fechaElem = medallasFiltradas[index].getFecha();
						if (fechaElem != null)
							if (fechaElem.isBefore(fechaMin) || fechaElem.isAfter(fechaMax)) {
								medallasFiltradas[index] = null;
								continue;
							}
					}
					if (asignadasOnly) {
						if (!medallasFiltradas[index].isAsignada()) {
							medallasFiltradas[index] = null;
							continue;
						}
					}
				}
			} else {
				for (int i = 0; i < Datos.PLATAS.length; i++, index++) {
					medallasFiltradas[index] = null;
				}
			}

			if (broncesSi) {
				for (int i = 0; i < Datos.BRONCES.length; i++, index++) {
					if (purezaSi) {
						float purezaElem = ((Bronce) medallasFiltradas[index]).getPureza();
						if (purezaElem < purezaMin || purezaElem > purezaMax) {
							medallasFiltradas[index] = null;
							continue;
						}
					}
					if (fechasSi) {
						LocalDate fechaElem = medallasFiltradas[index].getFecha();
						if (fechaElem != null)
							if (fechaElem.isBefore(fechaMin) || fechaElem.isAfter(fechaMax)) {
								medallasFiltradas[index] = null;
								continue;
							}
					}
					if (asignadasOnly) {
						if (!medallasFiltradas[index].isAsignada()) {
							medallasFiltradas[index] = null;
							continue;
						}
					}
				}
			} else {
				for (int i = 0; i < Datos.BRONCES.length; i++, index++) {
					medallasFiltradas[index] = null;
				}
			}

			// mostrar las medallas que cumplen los criterios de búsqueda
			int numMedallasFiltradas = 0;
			for (int i = 0; i < medallasFiltradas.length; i++)
				if (medallasFiltradas[i] != null)
					numMedallasFiltradas++;
			System.out.println("Hay " + numMedallasFiltradas + " medallas que cumplen sun criterios de búsqueda.");
			if (numMedallasFiltradas > 0) {
				System.out.println("Las siguientes medallas cumplen sus criterios de búsqueda:");
				for (int i = 0; i < medallasFiltradas.length; i++)
					if (medallasFiltradas[i] != null)
						System.out.println("medalla: " + medallasFiltradas[i].toString());
			} else
				System.out.println("No hay medallas que cumplan sus criterios de búsqueda.");

			break;
		default:
		}
		System.out.println("Volviendo al menú principal de gestión de medallas...");

	}

	private static void mostrarSubmenuManager(int elecc) {
		Scanner in = new Scanner(System.in);
		int subelecc = -1;
		boolean valido = false;
		switch (elecc) {
		case 1: // opción 2.1
			System.out.println("Ha seleccionado 2.1 CONFORMAR EQUIPO.");
			break;
		case 2: // opción 2.2
			System.out.println("Ha seleccionado 2.2 INSCRIPCIÓN de EQUIPO en PRUEBA.");
			Equipo eqp = Equipo.nuevoEquipo();
			System.out.println("¿Son correctos los siguientes datos?");
			System.out.println(eqp.toString());

			File file = new File("pruebas.txt");
			String line;
			FileReader fr;
			try {
				fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String[] campos = new String[5];
				if (file.exists()) {
					while ((line = br.readLine()) != null) {
						campos = br.readLine().split("|");
						
						if(campos[4]=="false") {
							System.out.println(line);
						}
					}

					System.out.println("Introduzca el id de la prueba en la que quiera inscribirse");
					String idprueba = in.next();
					
					while ((line = br.readLine()) != null) {
						campos = br.readLine().split("|");
						
						if(campos[0]==idprueba) {
							System.out.println("¿Confirma la inscripción?");
							boolean con = Utilidades.leerBoolean();
							
							if(con) {
								File inscripcion = new File("inscrip_"+idprueba+"_"+eqp.getManager().getId()+".dat");
								FileOutputStream fos = new FileOutputStream(inscripcion);
								ObjectOutputStream escribir = new ObjectOutputStream(fos);
								escribir.writeObject(eqp);
								escribir.write('\n');
								escribir.writeLong(Long.parseLong(idprueba));
								escribir.write('\n');
								escribir.writeObject(LocalDateTime.now());
								
								FileInputStream  fis = new FileInputStream(inscripcion);
								DataInputStream dis = new DataInputStream(fis);
								
								if(dis.available()>0) {
									LocalDate localDate = LocalDate.now();
									DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YY hh:mm:ss");
									String fechahora = localDate.format(formatter);
									
									System.out.println("Se ha creado el fichero "+inscripcion.getName()+" a "
											+ fechahora+" en el que el equipo "+eqp.getId() + " representado por "
											+ eqp.getManager().getPersona().getNombre() + "("+eqp.getManager().getPersona().getNifnie()+")"
											);
								}
							}
						}
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			break;
		default:
		}
		System.out.println("Volviendo al menú de MÁNAGERS...\n\n");
	}

	private static void mostrarSubmenuAtleta(int elecc) throws IOException {
		Scanner in = new Scanner(System.in);
		int subelecc = -1;
		boolean valido = false;
		switch (elecc) {
		case 1: //// opción 3.1
			System.out.println("Ha seleccionado 3.1 FEDERARSE (Nuevo ATLETA).");
			Atleta nuevo = Atleta.nuevoAtleta();
			System.out.println("El nuevo atleta introducido es: " + nuevo);
			break;
		case 2: // opción 3.2
			System.out.println("Ha seleccionado 3.2 INSCRIPCIÓN de ATLETA en PRUEBA..");
			FileReader fl = new FileReader("pruebas.txt");
			boolean confi1 = false;
			Atleta atl;
			do {
				atl = Atleta.nuevoAtleta();
				System.out.println(atl.toString());
				System.out.println("¿Son correctos estos datos?");
				confi1 = Utilidades.leerBoolean();
			} while (!confi1);

			int c;
			while ((c = fl.read()) != -1) {
				System.out.print(c);
			}
			long idp;
			boolean confi2 = false;
			do {
				System.out.println("Seleccione el id de la prueba a la que desea inscribirse:");
				idp = in.nextLong();
				System.out.println("¿Confima su elección?:");
				confi2 = Utilidades.leerBoolean();
			} while (!confi2);

			FileWriter fw = new FileWriter("inscrip_" + idp + "_" + atl.getPersona().getNifnie());
			fw.write(atl.toString() + idp + LocalDateTime.now());
			fw.close();

			System.out.println("Se ha creado el fichero " + "inscrip_" + idp + "_" + atl.getPersona().getNifnie()
					+ " a " + LocalDateTime.now() + " en el que el atleta" + atl.getId() + " de nombre "
					+ atl.getPersona().getNombre() + " y NIF/NIE " + atl.getPersona().getNifnie()
					+ " queda inscrito en la prueba " + idp);

			break;
		default:
		}
		System.out.println("Volviendo al menú de ATLETAS...\n\n");
	}

	private static void mostrarSubmenuColegiado(int elecc) {
		Scanner in = new Scanner(System.in);
		int subelecc = -1;
		boolean valido = false;
		switch (elecc) {
		case 1: //// opción 4.1
			System.out.println("Ha seleccionado 4.1 Nuevo COLEGIADO.");
			Colegiado nuevo = Colegiado.nuevoColegiado();
			System.out.println("Se ha creado correctamente el nuevo colegiado:" + nuevo);
			break;
		case 2: //// opción 4.2
			System.out.println("Ha seleccionado 4.2 INTRODUCIR RESULTADOS de PRUEBA..");
			break;
		default:
		}
		System.out.println("Volviendo al menú de COLEGIADOS...\n\n");
	}

	private static void mostrarSubmenuAdmin(int elecc) {
		Scanner in = new Scanner(System.in);
		int subelecc = -1;
		boolean valido = false;
		switch (elecc) {
		case 1:
			System.out.println("Ha seleccionado Gestión de medallas, de competiciones y de pruebas.");
			break;
		case 2:
			System.out.println("Ha seleccionado Gestión de equipos.");
			break;
		case 3:
			System.out.println("Ha seleccionado Gestión de atletas.");
			break;
		case 4:
			System.out.println("Ha seleccionado Gestión de arbitrajes y resultados.");
			break;
		default:
		}
		System.out.println("Volviendo al menú de ADMINISTRADORES...\n\n");
	}

	private static void mostrarSubmenuInvitado(int elecc) {
		Scanner in = new Scanner(System.in);
		int subelecc = -1;
		boolean valido = false;
		System.out.println("SUBMenús para INVITADOS.");
		System.out.println("Volviendo al menú principal...\n\n");
	}

	private static void mostrarMenuDirectiva() {
		System.out.println("Menú de la DIRECTIVA.");
		System.out.println("Seleccione una de las siguientes opciones:");
		System.out.println("1.1 Gestión de medallas\n" + "1.2 Gestión de competiciones y pruebas.\n" + "0. Volver");
	}

	private static void mostrarMenuManager() {
		System.out.println("Menú para los MÁNAGERS.");
		System.out.println("Seleccione una de las siguientes opciones:");
		System.out.println("2.1 Conformar equipo\n" + "2.2 Inscripción de equipo en prueba.\n" + "0. Volver");
	}

	private static void mostrarMenuAtleta() {
		System.out.println("Menú para los ATLETAS.");
		System.out.println("Seleccione una de las siguientes opciones:");
		System.out.println("3.1 Federarse (nuevo Atleta)\n" + "3.2 Inscripción de atleta en prueba.\n" + "0. Volver");

	}

	private static void mostrarMenuColegiado() {
		System.out.println("Menú para los COLEGIADOS.");
		System.out.println("Seleccione una de las siguientes opciones:");
		System.out.println("4.1 Nuevo Colegiado\n" + "4.2 Introducir resultados de prueba.\n" + "0. Volver");
	}

	private static void mostrarMenuAdmin() {
		System.out.println("Menú para los ADMINISTRADORES.");
		System.out.println("Seleccione una de las siguientes opciones:");
		System.out.println("1. Gestión de medallas, de competiciones y de pruebas\n" + "2. Gestión de equipos.\n"
				+ "3. Gestión de atletas.\n" + "4. Gestión de arbitrajes y resultados.\n" + "0. Volver");
	}

	private static void mostrarMenuInvitado() {
		System.out.println("Menú para los INVITADOS.");
		System.out.println("Seleccione una de las siguientes opciones:");
		System.out.println("" + "0. Volver");
	}

	/**
	 * Comprueba en el documento credenciales.txt los datos del orbejo Credenciales
	 * que se hayan metido como paramtro
	 * 
	 * @param cred
	 * @return true si las credenciales coninciden con alguna fila del fichero,
	 *         false si nunca coincide.
	 * @throws IOException
	 */
	private static boolean login(Credenciales cred) throws IOException {
		// Por el momento siempre devolverá true

		File file = new File("credenciales.txt");
		FileInputStream FIS = new FileInputStream(file);

		int c;
		String usur = null;
		String pass = null;
		boolean espacio = false;
		boolean secEspacio = false;

		while ((c = FIS.read()) != -1) {
			if (!secEspacio) {
				if (c == 124) {
					espacio = true;
					continue;
				}

				if (c == 124 && espacio) {
					secEspacio = true;
					continue;
				}

				if (!espacio) {
					usur += (char) c;
				} else {
					pass += (char) c;
				}
			} else {
				if (cred.getUsuario() == usur && cred.getPassword() == pass) {
					return true;
				}
				if (c == 10) {
					usur = "";
					pass = "";
					espacio = false;
					secEspacio = false;
					continue;
				}
			}

		}

		return false;
	}

	/**
	 * Este método crea un fichero llamado juniors.dat donde se guardan los nombres
	 * de aquellos atletas nacidos despues del 1 de Enero del anno 2000
	 * 
	 * @throws IOException
	 */
	public static void exportAtletas() throws IOException {
		File juniors = new File("juniors.dat");
		juniors.createNewFile();
		FileOutputStream FIS = new FileOutputStream(juniors);
		ObjectOutputStream wr = new ObjectOutputStream(FIS);

		for (Atleta i : Datos.ATLETAS) {
			if (i.getPersona().getFechaNac().isAfter(LocalDate.of(2000, 1, 1))) {
				wr.writeBytes(i.getPersona().getNombre() + "\n");
			}
		}
	}

	/**
	 * Esta función declara un objeto file por cada tipo de colegiado Recorre la
	 * lista de colegiados y dependiendo de su categoria, escribe sus datos en su
	 * fichero correspondiente
	 * 
	 * @throws IOException
	 */
	public static void exportColegiados() throws IOException {
		File f1 = new File("colegiadosjunior.dat");
		if (!f1.exists()) {
			f1.createNewFile();
		}
		File f2 = new File("colegiadossenior.dat");
		if (!f2.exists()) {
			f2.createNewFile();
		}
		File f3 = new File("colegiadosespeciales.dat");
		if (!f3.exists()) {
			f3.createNewFile();
		}
		FileWriter fw1 = new FileWriter(f1);
		PrintWriter pw1 = new PrintWriter(fw1);
		FileWriter fw2 = new FileWriter(f2);
		PrintWriter pw2 = new PrintWriter(fw2);
		FileWriter fw3 = new FileWriter(f3);
		PrintWriter pw3 = new PrintWriter(fw3);

		for (Colegiado a : Datos.COLEGIADOS) {
			switch (a.getCategoria()) {
			case JUNIOR:
				pw1.println(a.toString());
				continue;
			case SENIOR:
				pw2.println(a.toString());
				continue;
			case ESPECIAL:
				pw3.println(a.toString());
				continue;
			}

		}
	}

}
