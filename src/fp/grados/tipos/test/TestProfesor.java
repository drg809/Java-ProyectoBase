package fp.grados.tipos.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import fp.grados.excepciones.ExcepcionProfesorNoValido;
import fp.grados.excepciones.ExcepcionProfesorOperacionNoPermitida;
import fp.grados.excepciones.ExcepcionTutoriaNoValida;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.tipos.Tutoria;
import fp.grados.tipos.TutoriaImpl;

public class TestProfesor {

	public static void main(String[] args) {

		testConstructor1Normal();
		testConstructor1Excepcional1();

		testSetCategoriaNormal();
		testSetDepartamento();

		testNuevaTutoria();

		testBorraTutoria();
		testBorraTutorias();

		testImparteAsignatura();
		testImparteAsignaturaExcepcional1();
		testImparteAsignaturaExcepcional2();
		
		testDedicacionAsignatura();
		
		testEliminaAsignatura();


	}

	private static void testConstructor1Normal() {
		System.out
				.println("================================== Probando el primer constructor");

		testConstructor1("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com",
				Categoria.AYUDANTE, new DepartamentoImpl("DTE"));
	}

	private static void testConstructor1Excepcional1() {
		System.out
				.println("================================== Probando el primer constructor con edad menor a 18");

		testConstructor1("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(2008, 3, 15), "juan.nadie@gmail.com",
				Categoria.AYUDANTE, new DepartamentoImpl("DTE"));
	}

	private static void testSetCategoriaNormal() {
		System.out
				.println("================================== Probando setCategoria");
		Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com",
				Categoria.AYUDANTE, new DepartamentoImpl("DTE"));

		testSetCategoria(p, Categoria.CATEDRATICO);

	}
	
	private static void testSetDepartamento() {
		System.out
				.println("================================== Probando setDepartamento");
		Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com",
				Categoria.AYUDANTE, new DepartamentoImpl("DTE"));

		testSetDepartamento(p, new DepartamentoImpl("LSI"));

	}

	private static void testConstructor1(String dni, String nombre,
			String apellidos, LocalDate fechaNacimiento, String email,
			Categoria categoria, Departamento departamento) {

		try {
			Profesor p = new ProfesorImpl(dni, nombre, apellidos,
					fechaNacimiento, email, categoria, departamento);
			mostrarProfesor(p);

		} catch (ExcepcionProfesorNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionProfesorNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");

		}

	}

	private static void testSetCategoria(Profesor p, Categoria nuevaCategoria) {

		try {
			System.out.println("La categoria antes de la operación es: "
					+ p.getCategoria());
			System.out.println("La nueva categoria es: " + nuevaCategoria);
			p.setCategoria(nuevaCategoria);
			System.out.println("La categoria después de la operación es: "
					+ p.getCategoria());

		} catch (ExcepcionProfesorNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionProfesorNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. setCategoria no funciona correctamente");
		}

	}
	
	private static void testSetDepartamento(Profesor p, Departamento departamento) {

		try {
			System.out.println("El departamento antes de la operación es: "
					+ p.getDepartamento());
			System.out.println("El nuevo departamento es: " + departamento);
			p.setDepartamento(departamento);
			System.out.println("El departamento después de la operación es: "
					+ p.getDepartamento());

		} catch (ExcepcionProfesorNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionProfesorNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. setCategoria no funciona correctamente");
		}

	}

	private static void testNuevaTutoria() {
		try {

		System.out
				.println("================================== Probando NuevaTutoria");
		Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",
				Categoria.AYUDANTE, new DepartamentoImpl("DTE"));

		testNuevaTutoria(p,
				new TutoriaImpl(DayOfWeek.FRIDAY, LocalTime.of(17, 30), 60));
		} catch (ExcepcionTutoriaNoValida e) {
			System.out
				.println("******************** Se ha capturado la excepción ExcepcionTutoriaNoValida");
		} catch (Exception e) {
			System.out
				.println("******************** Se ha capturado una excepción inesperada. setCategoria no funciona correctamente");
		}
	}

	private static void testNuevaTutoria(Profesor p, Tutoria t) {

		System.out.println("Las tutorias antes de la operación son: "
				+ p.getTutorias());
		System.out.println("Nueva tutoria a matricular: " + t);
		p.nuevaTutoria(t.getHoraComienzo(), t.getDuracion(), t.getDiaSemana());
		System.out.println("Las tutorias después de la operación son: "
				+ p.getTutorias());

	}

	private static void testBorraTutoria() {
		try {
		System.out
				.println("\n=============================== Probando BorraTutoria");
		Profesor p = new ProfesorImpl("28846273H", "Daniel", "Nadie Nadie",
				LocalDate.of(1950, 6, 15), "d.nadie@gmail.com",
				Categoria.AYUDANTE, new DepartamentoImpl("DTE"));
		testBorraTutoria(p,
				new TutoriaImpl(DayOfWeek.MONDAY, LocalTime.of(12, 30), 30));
		} catch (ExcepcionTutoriaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionTutoriaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. setCategoria no funciona correctamente");
		}
	}

	private static void testBorraTutoria(Profesor p, Tutoria t) {
		p.nuevaTutoria(t.getHoraComienzo(), t.getDuracion(), t.getDiaSemana());
		Tutoria t1 = new TutoriaImpl(DayOfWeek.THURSDAY, LocalTime.of(12, 30),
				30);
		p.nuevaTutoria(t1.getHoraComienzo(), t1.getDuracion(), t.getDiaSemana());
		System.out.println("Las tutorias antes de la operación son: "
				+ p.getTutorias());
		System.out.println("Tutoria a eliminar: " + t);
		p.borraTutoria(t.getHoraComienzo(), t.getDiaSemana());
		System.out.println("Las tutorias después de la operación son: "
				+ p.getTutorias());

	}

	private static void testBorraTutorias() {
		try {
		System.out
				.println("\n=============================== Probando BorraTutorias");
		Profesor p = new ProfesorImpl("28846273H", "Daniel", "Nadie Nadie",
				LocalDate.of(1950, 6, 15), "d.nadie@gmail.com",
				Categoria.AYUDANTE, new DepartamentoImpl("DTE"));
		testBorraTutorias(p);
		} catch (ExcepcionTutoriaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionTutoriaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. setCategoria no funciona correctamente");
		}

	}

	private static void testBorraTutorias(Profesor p) {
		Tutoria t = new TutoriaImpl(DayOfWeek.MONDAY, LocalTime.of(16, 30), 45);
		p.nuevaTutoria(t.getHoraComienzo(), t.getDuracion(), t.getDiaSemana());
		Tutoria t1 = new TutoriaImpl(DayOfWeek.TUESDAY, LocalTime.of(10, 30),
				30);
		p.nuevaTutoria(t1.getHoraComienzo(), t1.getDuracion(),
				t1.getDiaSemana());
		System.out.println("Las tutorias antes de la operación son: "
				+ p.getTutorias());
		p.borraTutorias();
		System.out.println("Las tutorias después de la operación son: "
				+ p.getTutorias());
	}

	private static void testImparteAsignatura() {
		try {
		System.out
				.println("================================== Probando ImparteAsignatura");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1, new DepartamentoImpl("LSI"));
		
		Double dedicacion = 6.0;
		
		testImparteAsignatura(asig, dedicacion);
		} catch (ExcepcionProfesorOperacionNoPermitida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionProfesorOperacionNoPermitida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. setCategoria no funciona correctamente");
		}
	}
	
	private static void testImparteAsignaturaExcepcional1() {
		try {
		System.out
				.println("================================== Probando ImparteAsignatura con créditos superiores a los permitidos");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1, new DepartamentoImpl("LSI"));
		
		Double dedicacion = 100.0;
		
		testImparteAsignatura(asig, dedicacion);
		} catch (ExcepcionProfesorOperacionNoPermitida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionProfesorOperacionNoPermitida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. setCategoria no funciona correctamente");
		}
	}
	
	private static void testImparteAsignaturaExcepcional2() {
		try {
		System.out
				.println("================================== Probando ImparteAsignatura con departamento incorrecto");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1, new DepartamentoImpl("DTE"));
		
		Double dedicacion = 6.0;
		
		testImparteAsignatura(asig, dedicacion);
		} catch (ExcepcionProfesorOperacionNoPermitida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionProfesorOperacionNoPermitida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. setCategoria no funciona correctamente");
		}
	}
	
	private static void testImparteAsignatura(Asignatura asig, Double dedicacion) {
		
		Profesor p = new ProfesorImpl("28846273H", "Daniel", "Nadie Nadie",
				LocalDate.of(1950, 6, 15), "d.nadie@gmail.com",
				Categoria.AYUDANTE, new DepartamentoImpl("LSI"));
		
		p.imparteAsignatura(asig, dedicacion);
		System.out.println("Las asignaturas antes de la operación son: "
				+ p.getAsignaturas());
		System.out.println("Los creditos antes de la operación son: "
				+ p.getCreditos());
		System.out.println("asignaturas a añadir: " + asig + " [" + dedicacion + "]");
		p.imparteAsignatura(asig, dedicacion);
		System.out.println("Las asignaturas despues de la operación son: "
				+ p.getAsignaturas());
		System.out.println("Los creditos despues de la operación son: "
				+ p.getCreditos());
	}
	
	private static void testDedicacionAsignatura() {
		try {
		System.out
				.println("================================== Probando DedicacionAsignatura");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1, new DepartamentoImpl("LSI"));
		
		testDedicacionAsignatura(asig);
		} catch (ExcepcionProfesorOperacionNoPermitida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionProfesorOperacionNoPermitida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. setCategoria no funciona correctamente");
		}
	}
	
	private static void testDedicacionAsignatura(Asignatura asig) {
		Profesor p = new ProfesorImpl("28846273H", "Daniel", "Nadie Nadie",
				LocalDate.of(1950, 6, 15), "d.nadie@gmail.com",
				Categoria.AYUDANTE, new DepartamentoImpl("LSI"));
		
		p.imparteAsignatura(asig, 12.0);
		
		System.out.println("Las asignaturas antes de la operación son: "
				+ p.getAsignaturas());
		System.out.println("La dedicacion de la asignatura elegida es: "
				+ p.dedicacionAsignatura(asig));
		
	}
	
	private static void testEliminaAsignatura() {
		System.out
			.println("================================== Probando EliminaAsignatura");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1, new DepartamentoImpl("LSI"));
		
		testEliminaAsignatura(asig);
	}
	
	private static void testEliminaAsignatura(Asignatura asig) {
		Profesor p = new ProfesorImpl("28846273H", "Daniel", "Nadie Nadie",
				LocalDate.of(1950, 6, 15), "d.nadie@gmail.com",
				Categoria.AYUDANTE, new DepartamentoImpl("LSI"));
		
		p.imparteAsignatura(asig, 12.0);
		
		System.out.println("Las asignaturas antes de la operación son: "
				+ p.getAsignaturas());
		System.out.println("Los creditos antes de la operación son: "
				+ 12.0);
		System.out.println("asignaturas a borrar: " + asig);
		p.eliminaAsignatura(asig);
		System.out.println("Las asignaturas despues de la operación son: "
				+ p.getAsignaturas());
		System.out.println("Los creditos despues de la operación son: "
				+ p.getCreditos());
		
	}

	private static void mostrarProfesor(Profesor p) {

		System.out.println("Profesor --> <" + p + ">");

		System.out.println("\tNombre: <" + p.getNombre() + ">");
		System.out.println("\tApellidos: <" + p.getApellidos() + ">");
		System.out.println("\tDNI: <" + p.getDNI() + ">");
		System.out.println("\tFecha Nacimiento: <"
				+ p.getFechaNacimiento().format(
						DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ">");
		System.out.println("\tEdad: <" + p.getEdad() + ">");
		System.out.println("\tEmail:  <" + p.getEmail() + ">");
		System.out.println("\tCategoria:  <" + p.getCategoria() + ">");
		System.out.println("\tDepartamento:  <" + p.getDepartamento() + ">");
		System.out.println("\tAsignaturas:  <" + p.getAsignaturas() + ">");
		System.out.println("\tCreditos:  <" + p.getCreditos() + ">");
		System.out.println("\tDedicacion total:  <" + p.getDedicacionTotal()
				+ ">");
		System.out.println("\tTutorias:  <" + p.getTutorias() + ">");

	}

}
