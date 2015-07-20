package fp.grados.tipos.test;

import java.time.LocalDate;

import fp.grados.excepciones.ExcepcionAlumnoNoValido;
import fp.grados.excepciones.ExcepcionAlumnoOperacionNoPermitida;
import fp.grados.tipos.Alumno;
import fp.grados.tipos.AlumnoImpl;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Convocatoria;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.TipoAsignatura;

public class TestAlumno {
	public static void main(String[] args) {
		testConstructorNormal();
		testConstructorExcepcionalEmail();

		testSetEmailNormal();
		testSetEmailExcepcional();

		testMatriculaAsignatura();
		testMatriculaAsignaturaExcepcional();

		testEliminaAsignatura();
		testEliminaAsignaturaExcepcional();

		testEvaluaAlumno();
		testEvaluaAlumno1();

	}

	private static void testConstructorNormal() {
		System.out
				.println("\n================================Probando el primer constructor");
		testConstructor("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
	}

	private static void testConstructorExcepcionalEmail() {
		System.out
				.println("\n================================Probando el primer constructor, email incorrecto");
		testConstructor("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testSetEmailNormal() {
		System.out
				.println("\n================================Probando el setEmail");

		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		testSetEmail(a, "juan@alum.us.es");
	}

	private static void testSetEmailExcepcional() {
		System.out
				.println("\n================================Probando setEmail, email incorrecto");

		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		testSetEmail(a, "juan@alum.us.es");
	}

	private static void testMatriculaAsignatura() {
		System.out
				.println("\n================================Probando matriculaAsignatura");

		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");

		Asignatura asig = new AsignaturaImpl("Fundamentos de Programacion",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1, new DepartamentoImpl(
						"LSI"));
		testMatriculaAsignatura(a, asig);
	}

	private static void testMatriculaAsignaturaExcepcional() {
		System.out
				.println("\n================================Probando matriculaAsignatura, matricula doble en una asignatura");

		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");

		Asignatura asig = new AsignaturaImpl("Fundamentos de Programación",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1, new DepartamentoImpl(
						"LSI"));
		a.matriculaAsignatura(asig);
		testMatriculaAsignatura(a, asig);
	}

	private static void testEliminaAsignatura() {
		System.out
				.println("\n================================Probando EliminaAsignatura");

		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programacion",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1, new DepartamentoImpl(
						"LSI"));
		testEliminaAsignatura(a, asig);
	}

	private static void testEliminaAsignaturaExcepcional() {
		System.out
				.println("\n================================Probando EliminaAsignatura sin estar matriculado");

		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programacion",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1, new DepartamentoImpl(
						"LSI"));
		testEliminaAsignatura1(a, asig);
	}

	private static void testConstructor(String dni, String nombre,
			String apellidos, LocalDate fechaNacimiento, String email) {

		try {
			Alumno a = new AlumnoImpl(dni, nombre, apellidos, fechaNacimiento,
					email);
			mostrarAlumno(a);

		} catch (ExcepcionAlumnoNoValido e) {
			System.out
					.println("******************************Se ha capturado la excepcion ExcepcionAlumnoNoValido");

		} catch (Exception e) {
			System.out
					.println("******************************Se ha capturado una excepción inesperada.");
		}
	}

	private static void testSetEmail(Alumno a, String nuevoEmail) {

		try {
			System.out.println("El email antes de la operación es: "
					+ a.getEmail());
			System.out.println("El nuevo email es: " + nuevoEmail);
			a.setEmail(nuevoEmail);
			System.out.println("El email es: " + a.getEmail());

		} catch (ExcepcionAlumnoNoValido e) {
			System.out
					.println("****************************** Se ha capturado la excepción ExcepcionAlumnoNoValido");

		} catch (Exception e) {
			System.out
					.println("****************************** Se ha capturado una excepción inesperada.");
		}
	}

	private static void testMatriculaAsignatura(Alumno a, Asignatura asig) {

		try {
			System.out.println("Las asignaturas antes de la operación son: "
					+ a.getAsignaturas());
			System.out.println("Nueva asignatura a matricular: " + asig);
			a.matriculaAsignatura(asig);
			System.out.println("Las asignaturas después de la operación son: "
					+ a.getAsignaturas());

		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out
					.println("****************************** Se ha capturado la excepción ExcepcionAlumnoOperacionNoPermitida");

		} catch (Exception e) {
			System.out
					.println("****************************** Se ha capturado una excepción inesperada.");

		}
	}

	private static void testEliminaAsignatura(Alumno a, Asignatura asig) {

		try {
			a.matriculaAsignatura(asig);
			System.out.println("Las asignaturas antes de la operación son: "
					+ a.getAsignaturas());
			System.out.println("Asignatura a eliminar: " + asig);
			a.eliminaAsignatura(asig);
			System.out.println("las asignaturas después de la operación son: "
					+ a.getAsignaturas());
			
			
		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out
					.println("****************************** Se ha capturado la excepción ExcepcionAlumnoOperacionNoPermitida");

		} catch (Exception e) {
			System.out
					.println("****************************** Se ha capturado una excepción inesperada.");

		}
	}

	private static void testEliminaAsignatura1(Alumno a, Asignatura asig) {

		try {
			System.out.println("Las asignaturas antes de la operación son: "
					+ a.getAsignaturas());
			System.out.println("Asignatura a eliminar: " + asig);
			a.eliminaAsignatura(asig);
			System.out.println("las asignaturas después de la operación son: "
					+ a.getAsignaturas());

		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out
					.println("****************************** Se ha capturado la excepción ExcepcionAlumnoOperacionNoPermitida");

		} catch (Exception e) {
			System.out
					.println("****************************** Se ha capturado una excepción inesperada.");

		}
	}

	private static void testEvaluaAlumno() {
		System.out
				.println("\n================================Probando EvaluaAlumno con mencion de honor");

		testEvaluaAlumno(new AsignaturaImpl("Fundamentos de Programacion",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1, new DepartamentoImpl(
						"LSI")), 1999, Convocatoria.PRIMERA, 10.0, true);
	}

	private static void testEvaluaAlumno(Asignatura asig, int curso,
			Convocatoria convocatoria, double nota, boolean mencionHonor) {

		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");

		System.out.println("Las asignaturas del alumno son: "
				+ a.getAsignaturas());
		
		System.out.println("Las evaluaciones del alumno son: "
				+ a.getExpediente());

		a.matriculaAsignatura(asig);

		try {
			a.evaluaAlumno(asig, curso, convocatoria, nota, mencionHonor);

			System.out.println("Las asignaturas existentes despues de la operación son: "
					+ a.getAsignaturas());
			
			System.out.println("Las evaluaciones existentes despues de la operación son: "
					+ a.getExpediente());
			
		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out
					.println("*************************** Se ha capturado la excepción ExcepcionAlumnoOperacionNoPermitida");

		} catch (Exception e) {
			System.out
					.println("*************************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}

	private static void testEvaluaAlumno1() {
		System.out
				.println("\n================================Probando EvaluaAlumno sin mencion de honor");

		testEvaluaAlumno1(new AsignaturaImpl("Fundamentos de Programacion",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1, new DepartamentoImpl(
						"LSI")), 1999, Convocatoria.PRIMERA, 6.0);
	}

	private static void testEvaluaAlumno1(Asignatura asig, int curso,
			Convocatoria convocatoria, double nota) {

		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");

		System.out.println("Las asignaturas del alumno son: "
				+ a.getAsignaturas());
		
		System.out.println("Las evaluaciones del alumno son: "
				+ a.getExpediente());

		a.matriculaAsignatura(asig);

		try {
			a.evaluaAlumno(asig, curso, convocatoria, nota);
			
			System.out.println("Las asignaturas existentes despues de la operación son: "
					+ a.getAsignaturas());
			
			System.out.println("Los evaluaciones existentes despues de la operación son: "
					+ a.getExpediente());

		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out
					.println("*************************** Se ha capturado la excepción ExcepcionAlumnoOperacionNoPermitida");

		} catch (Exception e) {
			System.out
					.println("*************************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}

	private static void mostrarAlumno(Alumno a) {
		System.out.println("Alumno --> <" + a + ">");
		System.out.println("\tDNI: <" + a.getDNI() + ">");
		System.out.println("\tNombre: <" + a.getNombre() + ">");
		System.out.println("\tApellidos: <" + a.getApellidos() + ">");
		System.out.println("\tFecha Nacimiento: <"
				+ a.getFechaNacimiento().format(
						java.time.format.DateTimeFormatter
								.ofPattern("dd/MM/yyyy")) + ">");
		System.out.println("\tEdad: <" + a.getEdad() + ">");
		System.out.println("\tEmail: <" + a.getEmail() + ">");
		System.out.println("\tAsignaturas: <" + a.getAsignaturas() + ">");
	}
}
