import java.time.LocalDate;

public class Alumno {

	private String nombre;
	private LocalDate fechaNac;
	private String curso;
	
	
	public Alumno(String nombre, LocalDate fechaNac, String curso) {
		super();
		this.nombre = nombre;
		this.fechaNac = fechaNac;
		this.curso = curso;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public LocalDate getFechaNac() {
		return fechaNac;
	}


	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}


	public String getCurso() {
		return curso;
	}


	public void setCurso(String curso) {
		this.curso = curso;
	}


	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + ", fechaNac=" + fechaNac + ", curso=" + curso + "]";
	}
	
	
	
	
}
