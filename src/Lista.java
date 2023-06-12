import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Lista {

	private ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
	private String ruta;

	public Lista(ArrayList<Alumno> alumnos, String ruta) {
		super();
		this.alumnos = alumnos;
		this.ruta = ruta;
	}

	public ArrayList<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(ArrayList<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	@Override
	public String toString() {
		return "Lista [alumnos=" + alumnos + ", ruta=" + ruta + "]";
	}

	public void addAlumnos(Alumno item) {

		alumnos.add(item);

	}

	// 1- preguntarle al programa cuantos cumpleaños hay por mes
	public void cumpleMes(String mes) {
		int contador = 0;

		for (Alumno item : alumnos) {

			if (item.getFechaNac().getMonth().toString().equals(mes.toUpperCase())) {
				contador++;
			}

		}
		System.out.println("El mes " + mes + " tiene " + contador + " cumpleaño/s");
	}

	// 2- Que me diga el mayor y el menor de un grupo.
	public void mayorMenor(ArrayList<Alumno> alumnos, String grupo) {
		Alumno menor = null;
		Alumno mayor = null;

		// Encontramos el alumno más joven y mas viejo
		for (Alumno alumno : alumnos) {
			
			if (alumno.getCurso().equalsIgnoreCase(grupo)) {
				if (menor == null || alumno.getFechaNac().isAfter(menor.getFechaNac())) {
					menor = alumno;
				}
			}
			
			if (alumno.getCurso().equalsIgnoreCase(grupo)) {
				if (mayor == null || alumno.getFechaNac().isBefore(mayor.getFechaNac())) {
					mayor = alumno;
				}
			}
		}

		
	    if (menor != null) {
	        System.out.println("El alumno más joven del grupo " + grupo + " es: " + menor.getNombre());
	    } else {
	        System.out.println("No se encontraron alumnos en el grupo " + grupo);
	    }
		
		if (mayor != null) {
	        System.out.println("El alumno más viejo del grupo " + grupo + " es: " + mayor.getNombre());
	    } else {
	        System.out.println("No se encontraron alumnos en el grupo " + grupo);
	    }

	}
	
	// 3- que me diga todos los alumnos >18 de DAM
	public void mayorDeEdad(ArrayList<Alumno> alumnos, String grupo) {
		
		for(Alumno alumno: alumnos) {
			if(alumno.getCurso().equals(grupo)) {
				if(alumno.getFechaNac().isBefore(LocalDate.of(2005, 03, 01))) {
					System.out.println(alumno.getNombre() + " del grupo DAM, es mayor de edad");
				}
			}
		}
		
	}
	
	// 4- que me diga todos los alumnos que estan en un rango de fechas
	public void rangoEdad(ArrayList<Alumno> alumnos, LocalDate edad1, LocalDate edad2) {
		boolean falso = false;
		System.out.println("Entre estas fechas " + edad1 + " y " + edad2 + " se encuentran el/los alumno/s:");
		for(Alumno alumno: alumnos) {
			if(alumno.getFechaNac().isAfter(edad1) && alumno.getFechaNac().isBefore(edad2)) {
				System.out.println(alumno.getNombre());
				falso = true;
			}
		}
		
		if(!falso) {
			System.out.println("No hay ningun alumno entre esas fechas");
		}
	}
	
	// 5- lista ordenada por edad de mayor a menor o viceversa
	public void ordenarLista(ArrayList<Alumno> alumnos) {
		int opcion = 0;
		
		Scanner lector = new Scanner (System.in);
		
		System.out.println("Elige como quieres ordenar:");
		System.out.println("1 - Mayor a Menor");
		System.out.println("2 - Menor a Mayor");
		opcion = lector.nextInt();
		lector.nextLine();
		
		switch(opcion) {
		case 1:
			alumnos.stream()
			.sorted(Comparator.comparingInt(a -> ((Alumno) a).getFechaNac().until(LocalDate.now()).getYears()))
	        .forEach(alumno -> System.out.println(alumno));
			break;
		case 2:
			alumnos.stream()
			.sorted(Comparator.comparingInt(a -> ((Alumno) a).getFechaNac().until(LocalDate.now()).getYears()).reversed())
	        .forEach(alumno -> System.out.println(alumno));
			break;
		}

		/**
		 * Viendo que java me daba la opcion de .sorted(Comparator) busque en internet informacion para hacerlo con una expresion lambda
		 * añadiendo un punto vi me daba la opcion de .comparingInt para comprobar un int y haciendo un until (enlace de donde he sacado la informacion del until: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/LocalDate.html#until-java.time.temporal.Temporal-java.time.temporal.TemporalUnit-)
		 * vi que el until calcula el período entre una fecha y otra fecha entonces puse la fecha de ahora con el año de nacimiento del alumno
		 * y el .reversed vi que servia para ponerlo de mayor a menor o viceversa
		 */
		
	}
	
	//6- Muestra la media de edad de todas las personas
	public double mediaEdad(ArrayList<Alumno> alumnos) {
	    LocalDate fechaActual = LocalDate.now();
	    int edad;
	    int numAlumnos = alumnos.size();
	    double mediaEdad = 0;

	    for (Alumno alumno : alumnos) {
	        edad = fechaActual.getYear() - alumno.getFechaNac().getYear();
	        mediaEdad = mediaEdad + edad / numAlumnos;
	    }

	    return mediaEdad;
	}


	public void leerFichero() {

		FileReader fichero = null;
		BufferedReader lector = null;
		String cadena;

		try {
			fichero = new FileReader(ruta);
			lector = new BufferedReader(fichero);

			while ((cadena = lector.readLine()) != null) {
				System.out.println(cadena);
			}

		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el fichero");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error inesperado");
			e.printStackTrace();
		} finally {
			try {
				if (lector != null) {
					lector.close();
				}
				if (fichero != null) {
					fichero.close();
				}

			} catch (IOException e) {
				System.out.println("Error al cerrar el fichero");
				e.printStackTrace();
			}

		}

	}

	@SuppressWarnings("finally")
	public ArrayList<String> devolverFichero() {

		FileReader fichero = null;
		BufferedReader lector = null;
		String cadena;
		ArrayList<String> contenido = new ArrayList<String>();

		try {
			fichero = new FileReader(ruta);
			lector = new BufferedReader(fichero);

			while ((cadena = lector.readLine()) != null) {
				contenido.add(cadena);
			}

		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el fichero");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error inesperado");
			e.printStackTrace();
		} finally {
			try {
				if (lector != null) {
					lector.close();
				}
				if (fichero != null) {
					fichero.close();
				}

			} catch (IOException e) {
				System.out.println("Error al cerrar el fichero");
				e.printStackTrace();
			} finally {
				return contenido;
			}

		}

	}

	public void escribirFichero(ArrayList<String> datos) {
		FileWriter guardar = null;

		try {
			guardar = new FileWriter(ruta, true);// true = append

			for (String dato : datos) {
				guardar.write(dato + (char) 13);
			}
			// for(int i=0;i<datos.length;i++) {}

		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			System.out.println("Un error incontrolado");
		} finally {
			try {
				if (guardar != null) {
					guardar.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

	public void escribirFichero(Lista datos) {
		FileWriter guardar = null;
		FileReader fichero = null;
		BufferedReader lector = null;
		String cadena;

		try {
			guardar = new FileWriter(ruta, true);// true = append
			fichero = new FileReader(datos.getRuta());
			lector = new BufferedReader(fichero);

			while ((cadena = lector.readLine()) != null) {
				guardar.write(cadena + (char) 13);
			}

		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el fichero");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error inesperado");
			e.printStackTrace();
		} finally {
			try {
				if (guardar != null) {
					guardar.close();
				}
				if (lector != null) {
					lector.close();
				}
				if (fichero != null) {
					fichero.close();
				}

			} catch (IOException e) {
				System.out.println("Error al cerrar el fichero");
				e.printStackTrace();

			}

		}

	}

	public void escribirFichero(String dato) {
		FileWriter guardar = null;

		try {
			guardar = new FileWriter(ruta, true);// true = append
			guardar.write(dato + (char) 13);

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			System.out.println("Un error incontrolado");
		} finally {
			try {
				if (guardar != null) {
					guardar.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}
}
