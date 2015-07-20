package fp.grados.tipos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;




import fp.grados.excepciones.ExcepcionPersonaNoValida;
import fp.grados.tipos.Persona;

public class PersonaImpl implements Persona {

	private String dni;
	private String nombre;
	private String apellidos;
	private LocalDate fechaNacimiento;
	public Integer edad;
	private String email;
	
	public PersonaImpl(String s) {
		String [] p = s.split(",");
		if(p.length != 5) {
			throw new IllegalArgumentException("El tamaño del constructor es erroneo");
		}
		String dniN = p[0].trim();
		checkDni(dniN);
		this.dni = dniN;
		nombre = p[1].trim();
		apellidos = p[2].trim();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/y");
		fechaNacimiento = LocalDate.parse(p[3].trim(), formatter);	
		String emailA = p[4].trim();
		checkEmail(emailA);
		this.email = emailA;
	}

	public PersonaImpl(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento, String email) {

		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
		checkDni(dni);
		checkEmail(email);
	}

	public PersonaImpl(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento) {

		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		email = "";
		checkDni(dni);
		checkEmail(email);

	}

	public String getDNI() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getEmail() {
		return email;
	}

	public Integer getEdad() {
		edad = (int) getFechaNacimiento().until(LocalDate.now(),
				ChronoUnit.YEARS);
		return edad;
	}
	

	private void checkDni(String dni) {
	
		boolean res = false;
		String cad = "TRWAGMYFPDXBNJZSQVHLCKE";
		
		if (dni.length() != 9 || !Character.isDigit(dni.charAt(0))
				|| !Character.isDigit(dni.charAt(1))
				|| !Character.isDigit(dni.charAt(2))
				|| !Character.isDigit(dni.charAt(3))
				|| !Character.isDigit(dni.charAt(4))
				|| !Character.isDigit(dni.charAt(5))
				|| !Character.isDigit(dni.charAt(6))
				|| !Character.isDigit(dni.charAt(7))
				|| !Character.isAlphabetic(dni.charAt(8))) {

			throw new ExcepcionPersonaNoValida(
					"El dni introducido no es valido");
		}
		else if (dni.length() == 9){
			Integer val = new Integer(dni.substring(0, 8));
			res = dni.charAt(8) == cad.charAt(val%23);
			if(res == false) {
				throw new ExcepcionPersonaNoValida("El dni introducido no es valido");
			}
		}
	}

	private void checkEmail(String email) {
		if (!(email.isEmpty() || email.contains("@"))) {
			throw new ExcepcionPersonaNoValida(
					"Email no valido");
		}
	}

	public void setDNI(String dni) {
		checkDni(dni);
		this.dni = dni;

	}

	public void setNombre(String nombre) {

		this.nombre = nombre;

	}

	public void setApellidos(String apellidos) {

		this.apellidos = apellidos;

	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {

		this.fechaNacimiento = fechaNacimiento;

	}

	public void setEmail(String email) {
		checkEmail(email);
		this.email = email;

	}

	public String toString() {

		return getDNI()
				+ " - "
				+ getApellidos()
				+ ", "
				+ getNombre()
				+ " - "
				+ getFechaNacimiento().format(
						DateTimeFormatter.ofPattern("dd/MM/yyyy"));

	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		boolean res = false;
		if (o instanceof Persona) {
			Persona p = (Persona) o;
			res = this.getDNI().equals(p.getDNI())
					&& this.getNombre().equals(p.getNombre())
					&& this.getApellidos().equals(p.getApellidos());
		}
		return res;
	}

	public int hashCode() {
		return getDNI().hashCode() * 31 + getApellidos().hashCode() * 31
				+ getNombre().hashCode() * 31;
	}

	public int compareTo(Persona p) {
		int res;
		if (p == null) {
			throw new NullPointerException();
		}
		res = getApellidos().compareTo(p.getApellidos());
		if (res == 0) {
			res = getNombre().compareTo(p.getNombre());
			if (res == 0) {
				res = getDNI().compareTo(p.getDNI());
			}
		}
		return res;
	}

}