package fp.grados.tipos;

import java.time.LocalDate;

public interface Persona extends Comparable<Persona> {
	String getDNI();

	String getNombre();

	String getApellidos();

	LocalDate getFechaNacimiento();

	String getEmail();

	Integer getEdad();

	void setDNI(String dni);

	void setNombre(String nombre);

	void setApellidos(String apellidos);

	void setFechaNacimiento(LocalDate fecha);

	void setEmail(String email);
}