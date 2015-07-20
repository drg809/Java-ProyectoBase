package fp.grados.tipos.test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import fp.grados.excepciones.ExcepcionCentroNoValido;
import fp.grados.excepciones.ExcepcionCentroOperacionNoPermitida;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Centro;
import fp.grados.tipos.CentroImpl;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Despacho;
import fp.grados.tipos.DespachoImpl;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.TipoEspacio;

public class TestCentro {
	public static void main(String[] args) {

		testConstructorNormal();
		testContructorExcepcional1();
		testContructorExcepcional2();

		testNuevoEspacio();
		testNuevoEspacioExcepcional();
		testEliminaEspacio();
		testEliminaEspacioSinEfecto();

		testConteoEspacio();

		testGetDespacho();
		testGetDespachoDepartamento();

		testGetProfesores();
		testGetProfesoresDepartamento();

		testGetEspacioMayorCapacidad();
	}

	private static void testConstructorNormal() {

		System.out
				.println("\n=================================== Probando el primer constructor");

		testConstructor("Escuela de Informatica", "Avenida Reina Mercedes s/n",
				4, 1);
	}

	private static void testContructorExcepcional1() {

		System.out
				.println("\n=================================== Probando el primer constructor con error en planta.");

		testConstructor("Escuela de Informatica", "Avenida Reina Mercedes s/n",
				0, 2);
	}

	private static void testContructorExcepcional2() {

		System.out
				.println("\n=================================== Probando el primer constructor con error en sotanos.");

		testConstructor("Escuela de Informatica", "Avenida Reina Mercedes s/n",
				4, -1);
	}

	private static void testConstructor(String nombre, String direccion,
			Integer planta, Integer sotano) {

		try {
			Centro c = new CentroImpl(nombre, direccion, planta, sotano);
			mostrarCentro(c);

		} catch (ExcepcionCentroNoValido d) {
			System.out
					.println("*************************** Se ha capturado la excepción ExcepcionCentroNoValido");

		} catch (Exception e) {
			System.out
					.println("*************************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}

	private static void testNuevoEspacio() {
		System.out
				.println("\n================================Probando NuevoEspacio");

		Centro c = new CentroImpl("Escuela de Informatica",
				"Avenida Reina Mercedes s/n", 4, 0);
		Espacio e1 = new EspacioImpl(TipoEspacio.LABORATORIO, "L1.35", 24, 2);
		testNuevoEspacioCons(c, e1);
	}

	private static void testNuevoEspacioExcepcional() {
		System.out
				.println("\n================================Probando NuevoEspacio con planta no válida");

		Centro c = new CentroImpl("Escuela de Informatica",
				"Avenida Reina Mercedes s/n", 4, 0);
		Espacio e1 = new EspacioImpl(TipoEspacio.LABORATORIO, "L1.35", 24, 7);
		testNuevoEspacioCons(c, e1);
	}

	private static void testNuevoEspacioCons(Centro c, Espacio e1) {

		try {
			System.out
					.println("Los espacios existentes antes de la operacion son: "
							+ c.getEspacios());
			System.out.println("El espacio a añadir es: " + e1);
			c.nuevoEspacio(e1);
			System.out.println("El espacio despues de la operación es: "
					+ c.getEspacios());

		} catch (ExcepcionCentroOperacionNoPermitida d) {
			System.out
					.println("*************************** Se ha capturado la excepción ExcepcionCentroOperacionNoPermitida");

		} catch (Exception e) {
			System.out
					.println("*************************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}

	private static void testEliminaEspacio() {
		System.out
				.println("\n================================Probando EliminaEspacio");

		Centro c = new CentroImpl("Escuela de Informatica",
				"Avenida Reina Mercedes s/n", 4, 0);
		Espacio e = new EspacioImpl(TipoEspacio.LABORATORIO, "L1.35", 24, 2);
		testEliminaEspacioCons(c, e);
	}

	private static void testEliminaEspacioCons(Centro c, Espacio e) {

		c.nuevoEspacio(e);
		System.out
				.println("Los espacios existentes antes de la operacion son: "
						+ c.getEspacios());
		System.out.println("El espacio a borrar es: " + e);
		c.eliminaEspacio(e);
		System.out.println("El espacio despues de la operación es: "
				+ c.getEspacios());

	}

	private static void testEliminaEspacioSinEfecto() {
		System.out
				.println("\n================================Probando EliminaEspacio sin efecto");

		Centro c = new CentroImpl("Escuela de Informatica",
				"Avenida Reina Mercedes s/n", 4, 0);
		Espacio e = new EspacioImpl(TipoEspacio.LABORATORIO, "L1.35", 24, 2);
		testEliminaEspacioSin(c, e);
	}

	private static void testEliminaEspacioSin(Centro c, Espacio e) {

		System.out
				.println("Los espacios existentes antes de la operacion son: "
						+ c.getEspacios());
		System.out.println("El espacio a borrar es: " + e);
		c.eliminaEspacio(e);
		System.out.println("El espacio despues de la operación es: "
				+ c.getEspacios());

	}

	private static void testConteoEspacio() {
		System.out
				.println("\n================================Probando ConteoEspacio");

		testConteoEspacioCons1();
	}

	private static void testConteoEspacioCons1() {

		try {

			Centro c = new CentroImpl("Escuela de Informatica",
					"Avenida Reina Mercedes s/n", 4, 1);
			Espacio e1 = new EspacioImpl(TipoEspacio.EXAMEN, "A1.15", 60, 1);
			Espacio e2 = new EspacioImpl(TipoEspacio.LABORATORIO, "L3.35", 30,
					3);
			Espacio e3 = new EspacioImpl(TipoEspacio.TEORIA, "A2.10", 60, 2);
			Espacio e4 = new EspacioImpl(TipoEspacio.OTRO, "O1.35", 24, 1);
			Espacio e5 = new EspacioImpl(TipoEspacio.LABORATORIO, "L1.35", 30,
					1);
			Espacio e6 = new EspacioImpl(TipoEspacio.OTRO, "O2.35", 24, 2);
			Espacio e7 = new EspacioImpl(TipoEspacio.SEMINARIO, "A3.35", 24, 3);

			Integer[] res0 = c.getConteosEspacios();
			System.out
					.println("El conteo del espacio antes de la operación es: "
							+ Arrays.deepToString(res0));

			c.nuevoEspacio(e1);
			c.nuevoEspacio(e2);
			c.nuevoEspacio(e3);
			c.nuevoEspacio(e4);
			c.nuevoEspacio(e5);
			c.nuevoEspacio(e6);
			c.nuevoEspacio(e7);

			Integer[] res1 = c.getConteosEspacios();
			System.out
					.println("El conteo del espacio después de la operación es: "
							+ Arrays.deepToString(res1));

		} catch (ExcepcionCentroNoValido e) {
			System.out
					.println("*************************** Se ha capturado la excepción ExcepcionCentroNoValido");

		} catch (Exception e) {
			System.out
					.println("*************************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}

	private static void testGetDespacho() {
		System.out
				.println("\n================================Probando GetDespacho");

		Centro c = new CentroImpl("Escuela de Informatica",
				"Avenida Reina Mercedes s/n", 4, 1);

		testGetDespacho(c);
	}

	private static void testGetDespacho(Centro c) {

		try {
			Despacho d = new DespachoImpl("D3.10", 12, 3);
			Despacho d1 = new DespachoImpl("D3.30", 12, 3);
			Espacio e1 = new EspacioImpl(TipoEspacio.LABORATORIO, "L1.35", 24,
					1);

			System.out.println("El centro a comprobar es: " + c);

			System.out
					.println("Los despachos existentes antes de la operación son: "
							+ c.getDespachos());

			System.out.println("Los espacios a añadir son: " + e1 + d + d1);

			c.nuevoEspacio(e1);
			c.nuevoEspacio(d);
			c.nuevoEspacio(d1);

			System.out
					.println("Los despachos existentes después de la operación son: "
							+ c.getDespachos());

			System.out
					.println("Los espacios existentes después de la operación son: "
							+ c.getEspacios());
		} catch (ExcepcionCentroNoValido e) {
			System.out
					.println("*************************** Se ha capturado la excepción ExcepcionCentroNoValido");

		} catch (Exception e) {
			System.out
					.println("*************************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}

	private static void testGetDespachoDepartamento() {
		System.out
				.println("\n================================Probando GetDespacho(Departamento d)");

		Departamento d = new DepartamentoImpl("LSI");

		testGetDespacho1(d);
	}

	private static void testGetDespacho1(Departamento d) {

		try {
			Centro c = new CentroImpl("Escuela de Informatica",
					"Avenida Reina Mercedes s/n", 4, 1);
			Despacho des = new DespachoImpl("D3.10", 12, 3);
			Despacho d1 = new DespachoImpl("D3.30", 12, 3);
			Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",
					LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com",
					Categoria.TITULAR, new DepartamentoImpl("LSI"));
			Profesor p1 = new ProfesorImpl("28846273H", "Daniel",
					"Rodríguez Nadie", LocalDate.of(1960, 3, 15),
					"daniel.nadie@gmail.com", Categoria.AYUDANTE,
					new DepartamentoImpl("LSI"));
			Profesor p2 = new ProfesorImpl("37267364G", "Pepe", "Nadie Nadie",
					LocalDate.of(1950, 3, 7), "pepe.nadie@gmail.com",
					Categoria.AYUDANTE, new DepartamentoImpl("DTE"));

			Set<Profesor> profesores = new HashSet<Profesor>();

			des.setProfesores(profesores);

			System.out.println("El departamento a comprobar es: " + d);
			System.out
					.println("Los despachos existentes antes de la operación son: "
							+ c.getDespachos(d));

			profesores.add(p);
			profesores.add(p1);
			profesores.add(p2);

			c.nuevoEspacio(des);
			c.nuevoEspacio(d1);

			d.nuevoProfesor(p);

			System.out
					.println("Los despachos existentes después de la operación son: "
							+ c.getDespachos(d));

		} catch (ExcepcionCentroNoValido e) {
			System.out
					.println("*************************** Se ha capturado la excepción ExcepcionCentroNoValido");

		} catch (Exception e) {
			System.out
					.println("*************************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}

	private static void testGetProfesores() {
		System.out
				.println("\n================================Probando GetProfesores");

		Centro c = new CentroImpl("Escuela de Informatica",
				"Avenida Reina Mercedes s/n", 4, 1);

		testGetProfesores(c);
	}

	private static void testGetProfesores(Centro c) {

		try {
			Despacho d = new DespachoImpl("D3.10", 12, 3);
			Despacho d1 = new DespachoImpl("D3.30", 12, 3);
			Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",
					LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com",
					Categoria.TITULAR, new DepartamentoImpl("LSI"));
			Profesor p1 = new ProfesorImpl("28846273H", "Daniel",
					"Rodríguez Nadie", LocalDate.of(1960, 3, 15),
					"daniel.nadie@gmail.com", Categoria.AYUDANTE,
					new DepartamentoImpl("LSI"));
			Profesor p2 = new ProfesorImpl("37267364G", "Pepe", "Nadie Nadie",
					LocalDate.of(1950, 3, 7), "pepe.nadie@gmail.com",
					Categoria.AYUDANTE, new DepartamentoImpl("DTE"));

			System.out.println("El centro a comprobar es: " + c);

			System.out
					.println("Los profesores existentes con despachos en el centro antes de la operación son: "
							+ c.getProfesores());

			Set<Profesor> profesores = new HashSet<Profesor>();

			
			profesores.add(p);
			profesores.add(p1);
			d.setProfesores(profesores);

			profesores.add(p2);
			d1.setProfesores(profesores);
			
			c.nuevoEspacio(d);
			c.nuevoEspacio(d1);

			System.out
					.println("Los profesores existentes con despachos en el centro después de la operación son: "
							+ c.getProfesores());

		} catch (ExcepcionCentroNoValido e) {
			System.out
					.println("*************************** Se ha capturado la excepción ExcepcionCentroNoValido");

		} catch (Exception e) {
			System.out
					.println("*************************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}

	private static void testGetProfesoresDepartamento() {
		System.out
				.println("\n================================Probando GetProfesores(Departamento d)");

		Departamento d = new DepartamentoImpl("LSI");

		testGetProfesoresDepartamento(d);
	}

	private static void testGetProfesoresDepartamento(Departamento d) {

		try {
			Centro c = new CentroImpl("Escuela de Informatica",
					"Avenida Reina Mercedes s/n", 4, 1);
			Despacho des = new DespachoImpl("D3.10", 12, 3);
			Despacho d1 = new DespachoImpl("D3.30", 12, 3);
			Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",
					LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com",
					Categoria.TITULAR, new DepartamentoImpl("LSI"));
			Profesor p1 = new ProfesorImpl("28846273H", "Daniel",
					"Rodríguez Nadie", LocalDate.of(1960, 3, 15),
					"daniel.nadie@gmail.com", Categoria.AYUDANTE,
					new DepartamentoImpl("LSI"));
			Profesor p2 = new ProfesorImpl("37267364G", "Pepe", "Nadie Nadie",
					LocalDate.of(1950, 3, 7), "pepe.nadie@gmail.com",
					Categoria.AYUDANTE, new DepartamentoImpl("LSI"));

			System.out.println("El centro a comprobar es: " + c);

			System.out
					.println("Los profesores existentes con despachos en el centro antes de la operación son: "
							+ c.getProfesores(d));

			Set<Profesor> profesores = new HashSet<Profesor>();

			profesores.add(p);
			profesores.add(p1);
			des.setProfesores(profesores);

			profesores.add(p2);
			d1.setProfesores(profesores);

			c.nuevoEspacio(des);
			c.nuevoEspacio(d1);
			
			d.nuevoProfesor(p);		

			System.out
					.println("Los profesores existentes con despachos en el centro después de la operación son: "
							+ c.getProfesores(d));

		} catch (ExcepcionCentroNoValido e) {
			System.out
					.println("*************************** Se ha capturado la excepción ExcepcionCentroNoValido");

		} catch (Exception e) {
			System.out
					.println("*************************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}

	private static void testGetEspacioMayorCapacidad() {
		
		System.out
			.println("\n================================Probando GetEspacioMayorCapacidad");


		try {
			Centro c = new CentroImpl("Escuela de Informatica",
					"Avenida Reina Mercedes s/n", 4, 1);
			Despacho d = new DespachoImpl("D3.10", 12, 3);
			Despacho d1 = new DespachoImpl("D3.30", 12, 3);
			Espacio e1 = new EspacioImpl(TipoEspacio.LABORATORIO, "L1.35", 36,
					1);
			Espacio e2 = new EspacioImpl(TipoEspacio.OTRO, "O2.35", 24, 2);
			Espacio e3 = new EspacioImpl(TipoEspacio.SEMINARIO, "A3.35", 24, 3);

			System.out.println("El centro a comprobar es: " + c);

			System.out.println("Los espacios a añadir son: " + d + d1 + e1 + e2
					+ e3);

			c.nuevoEspacio(d);
			c.nuevoEspacio(d1);
			c.nuevoEspacio(e1);
			c.nuevoEspacio(e2);
			c.nuevoEspacio(e3);

			System.out
					.println("El espacio en el centro con mayor capacidad es: "
							+ c.getEspacioMayorCapacidad());

		} catch (ExcepcionCentroNoValido e) {
			System.out
					.println("*************************** Se ha capturado la excepción ExcepcionCentroNoValido");

		} catch (Exception e) {
			System.out
					.println("*************************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}

	private static void mostrarCentro(Centro c) {
		System.out.println("Centro --> <" + c + ">");
		System.out.println("\tNombre: <" + c.getNombre() + ">");
		System.out.println("\tDirección: <" + c.getDireccion() + ">");
		System.out.println("\tPlanta: <" + c.getNumeroPlantas() + ">");
		System.out.println("\tSotano: <" + c.getNumeroSotanos() + ">");
		System.out.println("\tEspacios: <" + c.getEspacios() + ">");

	}

}
