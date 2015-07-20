package fp.grados.tipos.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import fp.grados.excepciones.ExcepcionTutoriaNoValida;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.tipos.Tutoria;
import fp.grados.tipos.TutoriaImpl;

public class TestDepartamento {
	public static void main(String[] args) {
		testConstructorNormal();
		testNuevoProfesor();
		testEliminaProfesor();
		testNuevaAsignatura();
		testEliminaAsignatura();
		
		testBorraTutorias();
		testBorraTutorias1();
		
		testExisteProfersor();
		
		testEstanTodasAsignaturasAsignadas();
		
		
	}

	private static void testConstructorNormal() {

		System.out
				.println("\n================================== Probando el primer constructor");

		testConstructor("LSI");
	}

	private static void testNuevoProfesor() {

		System.out
				.println("\n================================== Probando NuevoProfesor");

		Departamento d = new DepartamentoImpl("MAT");

		testNuevoProfesor(d, new ProfesorImpl("12345678Z", "Juan",
				"Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@gmail.com", Categoria.AYUDANTE,
				new DepartamentoImpl("DTE")));

	}

	private static void testEliminaProfesor() {

		System.out
				.println("\n================================== Probando EliminaProfesor");

		Departamento d = new DepartamentoImpl("MAT");

		testEliminaProfesor(d, new ProfesorImpl("12345678Z", "Juan",
				"Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@gmail.com", Categoria.AYUDANTE,
				new DepartamentoImpl("DTE")));

	}

	private static void testNuevaAsignatura() {

		System.out
				.println("\n================================== Probando NuevaAsignatura");

		Departamento d = new DepartamentoImpl("MAT");

		testNuevaAsignatura(d, new AsignaturaImpl(
				"Introduccion a las Matemáticas Discretas", "1456701", 6.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 1, new DepartamentoImpl(
						"DTE")));

	}

	private static void testEliminaAsignatura() {

		System.out
				.println("\n================================== Probando EliminaAsignatura");

		Departamento d = new DepartamentoImpl("MAT");

		testEliminaAsignatura(d, new AsignaturaImpl(
				"Introduccion a las Matemáticas Discretas", "1456701", 6.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 1, new DepartamentoImpl(
						"DTE")));

	}

	private static void testConstructor(String nombre) {

		Departamento d = new DepartamentoImpl(nombre);

		mostrarDepartamento(d);

	}

	private static void testNuevoProfesor(Departamento d, Profesor profesor) {

		System.out.println("Los profesores antes de la operación son: "
				+ d.getProfesores());
		System.out.println("Nuevo profesor a añadir: " + profesor);
		System.out
				.println("El departamento del profesor antes de la operación es:"
						+ profesor.getDepartamento());
		d.nuevoProfesor(profesor);
		System.out.println("Los profesores despues de la operación son: "
				+ d.getProfesores());
		System.out
				.println("El departamento del profesor despues de la operacion es: "
						+ profesor.getDepartamento());

	}

	private static void testEliminaProfesor(Departamento d, Profesor profesor) {

		d.nuevoProfesor(new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com",
				Categoria.AYUDANTE, new DepartamentoImpl("DTE")));
		System.out.println("Los profesores antes de la operación son: "
				+ d.getProfesores());
		System.out.println("Profesor a eliminar: " + profesor);
		System.out
				.println("El departamento del profesor antes de la operación es:"
						+ profesor.getDepartamento());
		d.eliminaProfesor(profesor);
		System.out.println("Los profesores despues de la operación son: "
				+ d.getProfesores());
		System.out
				.println("El departamento del profesor despues de la operacion es: "
						+ profesor.getDepartamento());
	}

	private static void testNuevaAsignatura(Departamento d,
			Asignatura asignatura) {

		System.out.println("Las asignaturas antes de la operación son: "
				+ d.getAsignaturas());
		System.out.println("Nueva asignatura a añadir: " + asignatura);
		System.out
				.println("El departamento de la asignatura antes de la operación es:"
						+ asignatura.getDepartamento());

		d.nuevaAsignatura(asignatura);
		System.out.println("Las asignaturas despues de la operación son: "
				+ d.getAsignaturas());
		System.out
				.println("El departamento de la asignatura despues de la operación es: "
						+ asignatura.getDepartamento());
	}

	private static void testEliminaAsignatura(Departamento d,
			Asignatura asignatura) {

		d.nuevaAsignatura(asignatura);
		System.out.println("Las asignaturas antes de la operación son: "
				+ d.getAsignaturas());
		System.out.println("Asignatura a eliminar: " + asignatura);
		System.out
				.println("El departamento de la asignatura antes de la operación es:"
						+ asignatura.getDepartamento());

		d.eliminaAsignatura(asignatura);
		System.out.println("Las asignaturas despues de la operación son: "
				+ d.getAsignaturas());
		System.out
				.println("El departamento de la asignatura despues de la operación es: "
						+ asignatura.getDepartamento());
	}

	private static void testBorraTutorias() {
		try {
			System.out
					.println("\n=============================== Probando BorraTutorias");
			Profesor p = new ProfesorImpl("28846273H", "Daniel", "Nadie Nadie",
					LocalDate.of(1950, 6, 15), "d.nadie@gmail.com",
					Categoria.AYUDANTE, new DepartamentoImpl("DTE"));
			Departamento d = new DepartamentoImpl("DTE");

			testBorraTutorias(d, p,
					new TutoriaImpl(DayOfWeek.MONDAY, LocalTime.of(12, 30), 30));
		} catch (ExcepcionTutoriaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionTutoriaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. setCategoria no funciona correctamente");
		}
	}

	private static void testBorraTutorias(Departamento d, Profesor p, Tutoria t) {
		d.nuevoProfesor(p);
		p.nuevaTutoria(t.getHoraComienzo(), t.getDuracion(), t.getDiaSemana());
		Tutoria t1 = new TutoriaImpl(DayOfWeek.THURSDAY, LocalTime.of(12, 30),
				30);
		p.nuevaTutoria(t1.getHoraComienzo(), t1.getDuracion(), t.getDiaSemana());
		System.out.println("Las tutorias antes de la operación son: "
				+ p.getTutorias());
		System.out.println("Tutoria a eliminar: " + t);
		d.borraTutorias();
		System.out.println("Las tutorias después de la operación son: "
				+ p.getTutorias());

	}

	private static void testBorraTutorias1() {
		try {
			System.out
					.println("\n=============================== Probando BorraTutorias(Categoria c)");
			Profesor p = new ProfesorImpl("28846273H", "Daniel", "Nadie Nadie",
					LocalDate.of(1950, 6, 15), "d.nadie@gmail.com",
					Categoria.AYUDANTE, new DepartamentoImpl("DTE"));
			Departamento d = new DepartamentoImpl("DTE");

			testBorraTutoria1(d, p,
					new TutoriaImpl(DayOfWeek.MONDAY, LocalTime.of(12, 30), 30));
		} catch (ExcepcionTutoriaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionTutoriaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. setCategoria no funciona correctamente");
		}
	}

	private static void testBorraTutoria1(Departamento d, Profesor p, Tutoria t) {
		d.nuevoProfesor(p);
		p.nuevaTutoria(t.getHoraComienzo(), t.getDuracion(), t.getDiaSemana());
		Tutoria t1 = new TutoriaImpl(DayOfWeek.THURSDAY, LocalTime.of(12, 30),
				30);
		p.nuevaTutoria(t1.getHoraComienzo(), t1.getDuracion(), t.getDiaSemana());
		System.out.println("Las tutorias antes de la operación son: "
				+ p.getTutorias());
		System.out.println("Tutoria a eliminar: " + t);
		d.borraTutorias(Categoria.AYUDANTE);
		System.out.println("Las tutorias después de la operación son: "
				+ p.getTutorias());

	}

	private static void testExisteProfersor() {
			System.out
					.println("\n=============================== Probando ExisteProfersorAsignado");
			
			Asignatura a = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1, new DepartamentoImpl("LSI"));

			testExisteProfersor(a);
	}

	private static void testExisteProfersor(Asignatura a) {
		
		Profesor p = new ProfesorImpl("28846273H", "Daniel", "Nadie Nadie",
				LocalDate.of(1950, 6, 15), "d.nadie@gmail.com",
				Categoria.AYUDANTE, new DepartamentoImpl("LSI"));
		Departamento d = new DepartamentoImpl("LSI");

		d.nuevoProfesor(p);
		p.imparteAsignatura(a, 8.0);
		System.out.println("La asignatura a preguntar: "
				+ a);
		System.out.println("El resultado: "
				+ d.existeProfesorAsignado(a));

	}
	
	private static void testEstanTodasAsignaturasAsignadas() {
		System.out
				.println("\n=============================== Probando EstanTodasAsignaturasAsignadas");
		
		Asignatura a = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1, new DepartamentoImpl("LSI"));
		
		Profesor p = new ProfesorImpl("28846273H", "Daniel", "Nadie Nadie",
			LocalDate.of(1950, 6, 15), "d.nadie@gmail.com",
			Categoria.AYUDANTE, new DepartamentoImpl("LSI"));
		Departamento d = new DepartamentoImpl("LSI");

		d.nuevoProfesor(p);
		p.imparteAsignatura(a, 8.0);
		
		p.eliminaAsignatura(a);
		System.out.println("La asignatura a preguntar: "
			+ a);
		System.out.println("El resultado: "
			+ d.estanTodasAsignaturasAsignadas());

	}

	private static void mostrarDepartamento(Departamento d) {
		System.out.println("Departamento --> <" + d + ">");
		System.out.println("\tNombre: <" + d.getNombre() + ">");
		System.out.println("\tProfesores: <" + d.getProfesores() + ">");
		System.out.println("\tAsignaturas: <" + d.getAsignaturas() + ">");

	}
}