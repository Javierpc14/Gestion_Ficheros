import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Apéndice de método generado automáticamente

		int numeroAlum = 0;
		int dia;
		int mes;
		int año;
		boolean correcto = true;
		String nombre;
		String curso;
		LocalDate fechaN = LocalDate.of(2000, 01, 11);

		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();

		Scanner lector = new Scanner(System.in);

		Lista miLista = new Lista(alumnos, "src/alumnos.txt");

		
		//Metodo que inserta usuarios de prueba
		Main.alumnosPrueba(miLista);

		//Metodo que inserta el menu con todas las opciones
		menu(miLista, alumnos);

	}

	// En este metodo se realiza el menu de opciones
	public static void menu(Lista miLista, ArrayList<Alumno> alumnos) {
		int opcion = 0;

		String mes;
		String curso;

		Scanner lector = new Scanner(System.in);

		do {
			Main.opciones();

			opcion = lector.nextInt();
			lector.nextLine();
			
			// en este switch se van a realizar los distintos enunciados
			switch (opcion) {
			case 1:
				System.out.println("Introduce el mes que quieras comprobar");
				Main.meses();
				mes = lector.nextLine();

				miLista.cumpleMes(mes);
				System.out.println();
				break;
			case 2:
				System.out.println("Introduce el grupo que quieras comprobar");
				curso = lector.nextLine();

				miLista.mayorMenor(alumnos, curso);
				System.out.println();
				break;
			case 3:
				miLista.mayorDeEdad(alumnos, "DAM");
				System.out.println();
				break;
			case 4:
				LocalDate edad1;
				LocalDate edad2;
				edad1 = Main.edades();
				edad2 = Main.edades2();

				miLista.rangoEdad(alumnos, edad1, edad2);
				System.out.println();
				break;
			case 5:
				miLista.ordenarLista(alumnos);
				System.out.println();
				break;
			case 6:
				System.out.println("La media de edad de todos los alumnos es: " + miLista.mediaEdad(alumnos));
				System.out.println();
				break;
			case 7:
				Main.mostrarAlumnos(alumnos);
				System.out.println();
				break;
			case 8:
				Main.insertarAlumno(miLista);
				System.out.println();
			}
		} while (opcion != 9);
	}

	public static void opciones() {
		System.out.println("Introduce la opcion a realizar:");
		System.out.println("1- Muestra cuantos cumpleaños hay segun un mes dado");
		System.out.println("2- Muestra por pantalla al mayor y menor alumno de un grupo dado");
		System.out.println("3- Muestra todos los alumnos mayores a 18 del grupo DAM");
		System.out.println("4- Muestra el nombre de los alumnos que estan en un rango de edades dados");
		System.out.println("5- Muestra una lista ordenada a tu eleccion por edad");
		System.out.println("6- Muestra la media de edad de todas las personas");
		System.out.println("7- Muestra los usuarios que hay");
		System.out.println("8- Insertar un alumno a la lista");
		System.out.println("9- Salir");
	}

	public static void meses() {
		System.out.println("JANUARY");
		System.out.println("FEBRUARY");
		System.out.println("MARCH");
		System.out.println("APRIL");
		System.out.println("MAY");
		System.out.println("JUNE");
		System.out.println("JULY");
		System.out.println("AUGUST");
		System.out.println("SEPTEMBER");
		System.out.println("OCTOBER");
		System.out.println("NOVEMBER");
		System.out.println("DECEMBER");
	}

	// metodo en el que le pregunto al usuario las fechas para el ejercicio 4
	public static LocalDate edades() {
		int dia;
		int mes;
		int año;
		LocalDate edad;

		Scanner lector = new Scanner(System.in);

		System.out.println("Introduce la primera fecha");
		System.out.println("Introduce el dia");
		dia = lector.nextInt();
		lector.nextLine();

		System.out.println("Introduce el mes");
		mes = lector.nextInt();
		lector.nextLine();

		System.out.println("Introduce el año");
		año = lector.nextInt();
		lector.nextLine();

		edad = LocalDate.of(año, mes, dia);

		return edad;
	}

	public static LocalDate edades2() {
		int dia;
		int mes;
		int año;
		LocalDate edad;

		Scanner lector = new Scanner(System.in);

		System.out.println("Introduce la segunda fecha");
		System.out.println("Introduce el dia");
		dia = lector.nextInt();
		lector.nextLine();

		System.out.println("Introduce el mes");
		mes = lector.nextInt();
		lector.nextLine();

		System.out.println("Introduce el año");
		año = lector.nextInt();
		lector.nextLine();

		edad = LocalDate.of(año, mes, dia);

		return edad;
	}
	
	public static void insertarAlumno(Lista miLista) {
		int numeroAlum = 0;
		int dia;
		int mes;
		int año;
		boolean correcto = true;
		String nombre;
		String curso;
		LocalDate fechaN = LocalDate.of(2000, 01, 11);
		
		Scanner lector = new Scanner (System.in);
		
		//Le pregunto al usuario cuantos usuarios va a meter ahora en el fichero
				System.out.println("¿Cuantos alumnos vas a introducir?");
				numeroAlum = lector.nextInt();
				lector.nextLine();

				//En este bucle mete todos los datos del alumno
				do {
					
					for (int i = 0; i < numeroAlum; i++) {
						System.out.println("Introduce el nombre del alumno " + (i+1));
						nombre = lector.nextLine();
						
						System.out.println("Introduce el dia en que nació el alumno " + (i+1));
						dia = lector.nextInt();
						lector.nextLine();
						
						System.out.println("Introduce el mes en que nació el alumno " + (i+1));
						mes = lector.nextInt();
						lector.nextLine();
						
						System.out.println("Introduce el año en que nació el alumno " + (i+1));
						año = lector.nextInt();
						lector.nextLine();
						
						System.out.println("Introduce a que curso pertenece el alumno " + (i+1));
						curso = lector.nextLine();
						
						fechaN = LocalDate.of(año, mes, dia);
						
						miLista.escribirFichero("Nombre " + nombre + "; " + "Fecha de Nacimiento " + fechaN + "; " + "Curso " + curso);
						Alumno a = new Alumno(nombre, fechaN, curso);
						miLista.addAlumnos(a);
						
					}
					
				}while(numeroAlum < numeroAlum);
				
				System.out.println("Fichero escrito con exito");
	}
	
	public static void alumnosPrueba(Lista miLista) {
		Alumno pepe = new Alumno("Pepe", LocalDate.of(2000, 05, 12), "DAM");
		Alumno lola = new Alumno("Lola", LocalDate.of(2003, 03, 07), "DAM");
		Alumno alex = new Alumno("Alex", LocalDate.of(2015, 01, 01), "DAM");
		
		Alumno juan = new Alumno("Juan", LocalDate.of(2002, 01, 06), "DAW");
		Alumno paco = new Alumno("Paco", LocalDate.of(2011, 11, 03), "DAW");
		Alumno sara = new Alumno("Sara", LocalDate.of(2018, 06, 01), "DAW");

		miLista.addAlumnos(pepe);
		miLista.addAlumnos(lola);
		miLista.addAlumnos(alex);
		miLista.addAlumnos(paco);
		miLista.addAlumnos(sara);
		miLista.addAlumnos(juan);
	}
	
	public static void mostrarAlumnos(ArrayList<Alumno> alumnos) {
		alumnos.stream().forEach(item -> System.out.println(item.toString()));
	}

}

 
