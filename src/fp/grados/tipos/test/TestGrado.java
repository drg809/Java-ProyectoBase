package fp.grados.tipos.test;

import java.util.HashSet;
import java.util.Set;

import fp.grados.excepciones.ExcepcionGradoNoValido;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Grado;
import fp.grados.tipos.GradoImpl;
import fp.grados.tipos.TipoAsignatura;

public class TestGrado {
	public static void main(String[] args) {

		testConstructorNormal();
		testConstructorExcepcional();
		testConstructorExcepcional1();

		testgetAsignaturasCurso();

		testgetAsignaturasCodigo();
		testgetAsignaturasCodigoInexistente();
	}

	private static void testConstructorNormal() {

		Set<Asignatura> asignaturasObligatorias = new HashSet<Asignatura>();
		Set<Asignatura> asignaturasOptativas = new HashSet<Asignatura>();

		Asignatura a = new AsignaturaImpl("Fundamentos de Programación",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1, new DepartamentoImpl(
						"LSI"));
		Asignatura a1 = new AsignaturaImpl(
				"Circuitos Electrónicos y Digitales", "1234567", 6.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 1, new DepartamentoImpl(
						"DTE"));
		Asignatura a2 = new AsignaturaImpl(
				"Introducción a las Matemáticas Discretas", "1234556", 6.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 1, new DepartamentoImpl(
						"CAL"));

		asignaturasObligatorias.add(a);
		asignaturasOptativas.add(a1);
		asignaturasOptativas.add(a2);

		System.out
				.println("\n=================================== Probando el primer constructor");
		testConstructor("D1.30", 45.0, asignaturasObligatorias,
				asignaturasOptativas);
	}

	private static void testConstructorExcepcional() {

		Set<Asignatura> asignaturasObligatorias = new HashSet<Asignatura>();
		Set<Asignatura> asignaturasOptativas = new HashSet<Asignatura>();
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1, new DepartamentoImpl(
						"LSI"));
		Asignatura a1 = new AsignaturaImpl(
				"Circuitos Electrónicos y Digitales", "1234567", 6.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 1, new DepartamentoImpl(
						"DTE"));
		Asignatura a2 = new AsignaturaImpl(
				"Introducción a las Matemáticas Discretas", "1050000", 100.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 1, new DepartamentoImpl(
						"CAL"));
		asignaturasObligatorias.add(a);
		asignaturasOptativas.add(a1);
		asignaturasOptativas.add(a2);


		System.out
				.println("\n================================== Probando el primer constructor con asignaturas optativa con créditos mayores");

		testConstructor("D1.30", 45.0, asignaturasObligatorias,
				asignaturasOptativas);
		;
	}

	private static void testConstructorExcepcional1() {

		Set<Asignatura> asignaturasObligatorias = new HashSet<Asignatura>();
		Set<Asignatura> asignaturasOptativas = new HashSet<Asignatura>();
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1, new DepartamentoImpl(
						"LSI"));
		Asignatura a1 = new AsignaturaImpl(
				"Circuitos Electrónicos y Digitales", "1234567", 6.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 1, new DepartamentoImpl(
						"DTE"));
		Asignatura a2 = new AsignaturaImpl(
				"Introducción a las Matemáticas Discretas", "1050000", 5.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 1, new DepartamentoImpl(
						"CAL"));
		asignaturasObligatorias.add(a);
		asignaturasOptativas.add(a1);
		asignaturasOptativas.add(a2);

		System.out
				.println("\n================================== Probando el primer constructor con asignaturas optativas con diferentes créditos");

		testConstructor("D1.30", 45.0, asignaturasObligatorias,
				asignaturasOptativas);
		;
	}

	private static void testConstructor(String nombre,
			Double numeroMinimoCreditosOptativas,
			Set<Asignatura> asignaturasObligatorias,
			Set<Asignatura> asignaturasOptativas) {

		try {
			Grado g = new GradoImpl(nombre, asignaturasObligatorias,
					asignaturasOptativas, numeroMinimoCreditosOptativas);
			mostrarGrado(g);

		} catch (ExcepcionGradoNoValido d) {
			System.out
					.println("*************************** Se ha capturado la excepción ExcepcionGradoNoValido");

		} catch (Exception e) {
			System.out
					.println("*************************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}

	private static void testgetAsignaturasCurso() {
		System.out
				.println("\n================================Probando GetAsignatura(Integer curso)");

		testgetAsignaturasCurso(1);
	}

	private static void testgetAsignaturasCurso(Integer curso) {

		try {
			Set<Asignatura> asignaturasObligatorias = new HashSet<Asignatura>();
			Set<Asignatura> asignaturasOptativas = new HashSet<Asignatura>();

			Grado g = new GradoImpl("Informatica", asignaturasObligatorias,
					asignaturasOptativas, 45.0);

			Asignatura a = new AsignaturaImpl("Fundamentos de Programación",
					"2050001", 12.0, TipoAsignatura.ANUAL, 1,
					new DepartamentoImpl("LSI"));
			Asignatura a1 = new AsignaturaImpl(
					"Circuitos Electrónicos y Digitales", "1234567", 6.0,
					TipoAsignatura.PRIMER_CUATRIMESTRE, 1,
					new DepartamentoImpl("DTE"));
			Asignatura a2 = new AsignaturaImpl(
					"Introducción a las Matemáticas Discretas", "1050000", 6.0,
					TipoAsignatura.PRIMER_CUATRIMESTRE, 2,
					new DepartamentoImpl("CAL"));

			asignaturasObligatorias.add(a);
			asignaturasOptativas.add(a1);
			asignaturasOptativas.add(a2);

			System.out.println("El curso elegido es: " + curso);

			System.out.println("Las asignaturas existentes son: "
					+ asignaturasObligatorias + asignaturasOptativas);

			System.out.println("Las asignaturas del curso elegido son: "
					+ g.getAsignaturas(curso));

		} catch (ExcepcionGradoNoValido e) {
			System.out
					.println("*************************** Se ha capturado la excepción ExcepcionGradoNoValido");

		} catch (Exception e) {
			System.out
					.println("*************************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}

	private static void testgetAsignaturasCodigo() {
		System.out
				.println("\n================================Probando GetAsignatura(String codigo)");

		testgetAsignaturasCodigo("2050001");
	}

	private static void testgetAsignaturasCodigoInexistente() {
		System.out
				.println("\n================================Probando GetAsignatura(String codigo) con codigo de asignatura inexistente");

		testgetAsignaturasCodigo("2040001");
	}

	private static void testgetAsignaturasCodigo(String codigo) {

		try {
			Set<Asignatura> asignaturasObligatorias = new HashSet<Asignatura>();
			Set<Asignatura> asignaturasOptativas = new HashSet<Asignatura>();

			Grado g = new GradoImpl("Informatica", asignaturasObligatorias,
					asignaturasOptativas, 45.0);

			Asignatura a = new AsignaturaImpl("Fundamentos de Programación",
					"2050001", 12.0, TipoAsignatura.ANUAL, 1,
					new DepartamentoImpl("LSI"));
			Asignatura a1 = new AsignaturaImpl(
					"Circuitos Electrónicos y Digitales", "1234567", 6.0,
					TipoAsignatura.PRIMER_CUATRIMESTRE, 1,
					new DepartamentoImpl("DTE"));
			Asignatura a2 = new AsignaturaImpl(
					"Introducción a las Matemáticas Discretas", "1050000", 6.0,
					TipoAsignatura.PRIMER_CUATRIMESTRE, 2,
					new DepartamentoImpl("CAL"));

			asignaturasObligatorias.add(a);
			asignaturasOptativas.add(a1);
			asignaturasOptativas.add(a2);

			System.out.println("El codigo elegido es: " + codigo);

			System.out.println("Las asignaturas existentes son: "
					+ asignaturasObligatorias + asignaturasOptativas);

			System.out.println("La asignatura con el codigo elegido es: "
					+ g.getAsignatura(codigo));

		} catch (ExcepcionGradoNoValido e) {
			System.out
					.println("*************************** Se ha capturado la excepción ExcepcionGradoNoValido");

		} catch (Exception e) {
			System.out
					.println("*************************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}

	private static void mostrarGrado(Grado g) {

		System.out.println("Grado --> <" + g + ">");
		System.out.println("\tNombre: <" + g.getNombre() + ">");
		System.out.println("\tNumero mínimo de creditos optativos: <"
				+ g.getNumeroMinimoCreditosOptativas() + ">");
		System.out.println("\tNumero total de creditos: <"
				+ g.getNumeroTotalCreditos() + ">");
		System.out.println("\tAsignaturas Optativas: <"
				+ g.getAsignaturasOptativas() + ">");
		System.out.println("\tAsignaturas Obligatorias: <"
				+ g.getAsignaturasObligatorias() + ">");
		System.out.println("\tDepartamentos: <" + g.getDepartamentos() + ">");
		System.out.println("\tProfesores: <" + g.getProfesores() + ">");
	}
}
