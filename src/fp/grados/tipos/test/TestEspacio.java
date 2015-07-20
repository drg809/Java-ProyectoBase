package fp.grados.tipos.test;

import fp.grados.excepciones.ExcepcionEspacioNoValido;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.TipoEspacio;

public class TestEspacio {

	public static void main(String[] args) {

		testConstructor1();
		testConstructor1Excepcional();
		
		testSetTipoNormal();
		testSetNombreNormal();
		testSetCapacidadNormal();
		testSetCapacidadExcepcional();


	}

	private static void testConstructor1() {
		
		try {

			System.out.println("========Realizando el constructor========");
			Espacio e1 = new EspacioImpl(TipoEspacio.LABORATORIO,"L1.35", 24, 2);
			muestroEspacio1(e1);

		} catch (ExcepcionEspacioNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionEspacioNoValido, error en el constructor");
		} catch (Exception e) {
			System.out
					.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	
	private static void testConstructor1Excepcional() {
		
		try {

			System.out.println("========Realizando el constructor con capacidad 0========");
			Espacio e1 = new EspacioImpl(TipoEspacio.LABORATORIO,"L1.35", 0, 2);
			muestroEspacio1(e1);

		} catch (ExcepcionEspacioNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionEspacioNoValido, error en el constructor");
		} catch (Exception e) {
			System.out
					.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}

	private static void muestroEspacio1(Espacio e1) {

		System.out.println("Espacio:  --> <" + e1 + ">");

		System.out.println("\tTipo:  <" + e1.getTipo() + ">");
		System.out.println("\tNombre:  <" + e1.getNombre() + ">");
		System.out.println("\tCapacidad:  <" + e1.getCapacidad() + ">");
		System.out.println("\tPlanta:  <" + e1.getPlanta() + ">");
	}

	private static void testSetTipoNormal() {
		System.out
				.println("==================================Probando setTipo");
		Espacio e1 = new EspacioImpl(TipoEspacio.LABORATORIO,"L1.35", 24, 2);
		testSetTipo(e1, TipoEspacio.TEORIA);
	}

	private static void testSetTipo(Espacio e1, TipoEspacio tipo) {
		 try {
		System.out.println("El tipo antes de la operación es: " + e1.getTipo());
		System.out.println("El nuevo valor es: " + tipo);
		e1.setTipo(tipo);
		System.out
				.println("El tipo después de la operación es: " + e1.getTipo());
		
		  } catch (ExcepcionEspacioNoValido e) { System.out .println(
		  "******************** Se ha capturado la excepción ExcepcionEspacioNoValido"
		  ); } catch (Exception e) { System.out .println(
		  "******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!"
		  ); }
		 
	}

	private static void testSetNombreNormal() {
		System.out
				.println("==================================Probando setNombre");
		Espacio e1 = new EspacioImpl(TipoEspacio.LABORATORIO,"L1.35", 24, 2);
		testSetNombre(e1, "B1.23");
	}

	private static void testSetNombre(Espacio e1, String nombre) {
		 try {
		System.out.println("El nombre antes de la operación es: "
				+ e1.getNombre());
		System.out.println("El nuevo valor es: " + nombre);
		e1.setNombre(nombre);
		System.out.println("El nombre después de la operación es: "
				+ e1.getNombre());
		
		  } catch (ExcepcionEspacioNoValido e) { System.out .println(
		  "******************** Se ha capturado la excepción ExcepcionEspacioNoValido"
		  ); } catch (Exception e) { System.out .println(
		  "******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!"
		  ); }
		 
	}

	private static void testSetCapacidadNormal() {
		try {
			System.out
				.println("==================================Probando setCapacidad");
			Espacio e1 = new EspacioImpl(TipoEspacio.LABORATORIO,"L1.35", 24, 2);
			testSetCapacidad(e1, 30);
		} catch (ExcepcionEspacioNoValido e) { System.out .println(
		"******************** Se ha capturado la excepción ExcepcionEspacioNoValido, error en setCapacidad"
		); } catch (Exception e) { System.out .println(
		"******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!"
		); }		
	}
	
	private static void testSetCapacidadExcepcional() {
		try {
			System.out
				.println("==================================Probando setCapacidad con 0");
			Espacio e1 = new EspacioImpl(TipoEspacio.LABORATORIO,"L1.35", 24, 2);
			testSetCapacidad(e1, 0);
		} catch (ExcepcionEspacioNoValido e) { System.out .println(
		"******************** Se ha capturado la excepción ExcepcionEspacioNoValido, error en setCapacidad"
		); } catch (Exception e) { System.out .println(
		"******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!"
		); }		
	}

	private static void testSetCapacidad(Espacio e1, Integer capacidad) {
		 try {
		System.out.println("La capacidad antes de la operación es: "
				+ e1.getCapacidad());
		System.out.println("El nuevo valor es: " + capacidad);
		e1.setCapacidad(capacidad);
		System.out.println("El tipo después de la operación es: "
				+ e1.getCapacidad());
		
		 } catch (ExcepcionEspacioNoValido e) { System.out .println(
		 "******************** Se ha capturado la excepción ExcepcionEspacioNoValido, error en setCapacidad"
		 ); } catch (Exception e) { System.out .println(
		 "******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!"
		 ); }
		 
	}

}
