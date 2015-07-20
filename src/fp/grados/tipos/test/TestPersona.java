package fp.grados.tipos.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import fp.grados.excepciones.ExcepcionPersonaNoValida;
import fp.grados.tipos.Persona;
import fp.grados.tipos.PersonaImpl;

public class TestPersona {

	public static void main(String[] args) {

		testConstructor1Normal();
		testConstructor1Excepcional1();
		testConstructor1Excepcional2();
		testConstructor1Excepcional3();
		testConstructor1Excepcional4();
		testConstructor2Normal();
		testConstructor2Excepcional1();


		testSetDNINormal();
		testSetDNIExcepcional1();
		testSetDNIExcepcional2();
		testSetDNIExcepcional3();
		testSetNombreNormal();
		testSetApellidosNormal();
		testSetFechaNacimientoNormal();
		testSetEmailNormal();
		testSetEmailExcepcional1();
		testSetEmailExcepcional2();

	}

	private static void testConstructor1Normal() {
		System.out
				.println("==================================Probando el primer constructor");
		testConstructor1("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional1() {
		System.out
				.println("==================================Probando el primer constructor con dni sin letra");
		testConstructor1("123456789", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional2() {
		System.out
				.println("==================================Probando el primer constructor con dni de longitud menor de la esperada");
		testConstructor1("1234567S", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional3() {
		System.out
				.println("==================================Probando el primer constructor con letra en dni que no se corresponde a los dígitos");
		testConstructor1("12345678S", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional4() {
		System.out
				.println("==================================Probando el primer constructor con email sin arroba");
		testConstructor1("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadiegmail.com");
	}

	private static void testConstructor2Normal() {
		System.out
				.println("==================================Probando el segundo constructor");
		testConstructor2("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "");
	}
	
	private static void testConstructor2Excepcional1() {
		System.out
				.println("==================================Probando el segundo constructor con email no valido");
		testConstructor2("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "sdsd");
	}

	private static void testSetDNINormal() {
		System.out.println("==================================Probando setDNI");
		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetDNI(p, "12345677J");
	}
	
	private static void testSetNombreNormal() {
		System.out.println("==================================Probando setNombre");
		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetNombre(p, "Pepe");
	}
	
	private static void testSetApellidosNormal() {
		System.out.println("==================================Probando setApellido");
		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetApellidos(p, "Apellido Nuevo");
	}
	
	private static void testSetFechaNacimientoNormal() {
		System.out.println("==================================Probando setDNI");
		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetFechaNacimiento(p, LocalDate.of(1975, 4, 29));
	}

	private static void testSetDNIExcepcional1() {
		System.out
				.println("==================================Probando setDNI con dni sin letra");

		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetDNI(p, "123456779");
	}

	private static void testSetDNIExcepcional2() {
		System.out
				.println("==================================Probando setDNI con dni de longitud menor de la esperada");

		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetDNI(p, "12345679");
	}

	private static void testSetDNIExcepcional3() {
		System.out
				.println("==================================Probando setDNI con letra en dni que no se corresponde a los dígitos");

		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetDNI(p, "12345677X");
	}

	private static void testSetEmailNormal() {
		System.out.println("==================================Probando setEmail");
		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetEmail(p, "emailnuevo@gmail.com");
	}
	
	private static void testSetEmailExcepcional1() {
		System.out.println("==================================Probando setEmail sin @ en el email");
		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetEmail(p, "emailnuevogmail.com");
	}

	private static void testSetEmailExcepcional2() {
		System.out.println("==================================Probando setEmail con una cadena no vacía");
		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetEmail(p, "dasd");
	}
	
	private static void testConstructor1(String dni, String nombre,
			String apellidos, LocalDate fechaNacimiento, String email) {

		 try {
		Persona p = new PersonaImpl(dni, nombre, apellidos, fechaNacimiento,
				email);
		mostrarPersona(p);
		
		 } catch (ExcepcionPersonaNoValida e) { System.out .println(
		 "******************** Se ha capturado la excepción ExcepcionPersonaNoValida"
		 ); } catch (Exception e) { System.out .println(
		 "******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente"
		 ); }
		 
	}

	private static void testConstructor2(String dni, String nombre,
			String apellidos, LocalDate fechaNacimiento, String email) {

		 try {
		Persona p = new PersonaImpl(dni, nombre, apellidos, fechaNacimiento, email);
		mostrarPersona(p);
		
		 } catch (ExcepcionPersonaNoValida e) { System.out .println(
		 "******************** Se ha capturado la excepción ExcepcionPersonaNoValida"
		 ); } catch (Exception e) { System.out .println(
		 "******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente"
		 ); }
		 

	}

	private static void testSetDNI(Persona p, String nuevoDNI) {

		try {
		System.out.println("El dni antes de la operación es: " + p.getDNI());
		System.out.println("El nuevo dni es: " + nuevoDNI);
		p.setDNI(nuevoDNI);
		System.out.println("El dni después de la operación es: " + p.getDNI());
		
		 } catch (ExcepcionPersonaNoValida e) { System.out .println(
		 "******************** Se ha capturado la excepción ExcepcionPersonaNoValida"
		 ); } catch (Exception e) { System.out .println(
		 "******************** Se ha capturado una excepción inesperada. setDNI no funciona correctamente"
		 ); }
		 
	}
	
	private static void testSetNombre(Persona p, String nuevoNombre) {

		
		System.out.println("El nombre antes de la operación es: " + p.getNombre());
		System.out.println("El nuevo nombre es: " + nuevoNombre);
		p.setNombre(nuevoNombre);
		System.out.println("El nombre después de la operación es: " + p.getNombre());
	}
	
	private static void testSetApellidos(Persona p, String nuevosApellidos) {

		
		System.out.println("Los apellidos antes de la operación son: " + p.getApellidos());
		System.out.println("Los nuevos apellidos son: " + nuevosApellidos);
		p.setApellidos(nuevosApellidos);
		System.out.println("Los apellidos después de la operación son: " + p.getApellidos());
	}
	
	private static void testSetFechaNacimiento(Persona p, LocalDate nuevaFechaNacimiento) {

		
		System.out.println("Los apellidos antes de la operación son: " + p.getFechaNacimiento());
		System.out.println("Los nuevos apellidos son: " + nuevaFechaNacimiento);
		p.setFechaNacimiento(nuevaFechaNacimiento);
		System.out.println("Los apellidos después de la operación son: " + p.getFechaNacimiento());
	}
	
	private static void testSetEmail(Persona p, String nuevoEmail) {

		try {
		System.out.println("El email antes de la operación es: " + p.getEmail());
		System.out.println("El nuevo email es: " + nuevoEmail);
		p.setEmail(nuevoEmail);
		System.out.println("El email después de la operación es: " + p.getEmail());
		
		 } catch (ExcepcionPersonaNoValida e) { System.out .println(
		 "******************** Se ha capturado la excepción ExcepcionPersonaNoValida"
		 ); } catch (Exception e) { System.out .println(
		 "******************** Se ha capturado una excepción inesperada. setEmail no funciona correctamente"
		 ); }
		 
	}

	private static void mostrarPersona(Persona p) {
		System.out.println("Persona --> <" + p + ">");

		System.out.println("\tNombre: <" + p.getNombre() + ">");
		System.out.println("\tApellidos: <" + p.getApellidos() + ">");
		System.out.println("\tDNI: <" + p.getDNI() + ">");
		System.out.println("\tFecha Nacimiento: <"
				+ p.getFechaNacimiento().format(
						DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ">");
		System.out.println("\tEdad: <" + p.getEdad() + ">");
		System.out.println("\tEmail:  <" + p.getEmail() + ">");
	}

}
