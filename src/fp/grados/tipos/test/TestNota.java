package fp.grados.tipos.test;

import fp.grados.excepciones.ExcepcionNotaNoValida;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Nota;
import fp.grados.tipos.NotaImpl;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.tipos.Convocatoria;

public class TestNota {

	public static void main(String[] args) {

		testConstructor1Normal();
		testConstructor1Excepcional1();
		testConstructor1Excepcional2();

		testConstructor2Normal();
		testConstructor2Excepcional1();
		testConstructor2Excepcional2();
		testConstructor2Excepcional3();

	}

	private static void testConstructor1Normal() {
		System.out
				.println("==================================Probando el primer constructor");
		testConstructor1(new AsignaturaImpl("Circuitos", "2050002", 6.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 2, new DepartamentoImpl("DTE")), 1999,
				Convocatoria.PRIMERA, 6.0);

	}

	private static void testConstructor1Excepcional1() {
		System.out
				.println("==================================Probando el primer constructor con nota inferior a 0");
		testConstructor1(new AsignaturaImpl("Circuitos", "2050002", 6.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 2, new DepartamentoImpl("DTE")), 2014,
				Convocatoria.PRIMERA, -1.0);

	}

	private static void testConstructor1Excepcional2() {
		System.out
				.println("==================================Probando el primer constructor con nota superior a 10");
		testConstructor1(new AsignaturaImpl("Circuitos", "2050002", 6.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 2, new DepartamentoImpl("DTE")), 2014,
				Convocatoria.PRIMERA, 11.0);

	}

	private static void testConstructor2Normal() {
		System.out
				.println("==================================Probando el segundo constructor");
		testConstructor2(new AsignaturaImpl("Circuitos", "2050002", 6.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 2, new DepartamentoImpl("DTE")), 2014,
				Convocatoria.PRIMERA, 10.0, Boolean.FALSE);
	}

	private static void testConstructor2Excepcional1() {
		System.out
				.println("==================================Probando el segundo constructor con nota inferior a 0");
		testConstructor2(new AsignaturaImpl("Circuitos", "2050002", 6.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 2, new DepartamentoImpl("DTE")), 2014,
				Convocatoria.PRIMERA, -1.0, Boolean.FALSE);
	}

	private static void testConstructor2Excepcional2() {
		System.out
				.println("==================================Probando el segundo constructor con nota superior a 10");
		testConstructor2(new AsignaturaImpl("Circuitos", "2050002", 6.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 2, new DepartamentoImpl("DTE")), 2014,
				Convocatoria.PRIMERA, 11.0, Boolean.FALSE);
	}

	private static void testConstructor2Excepcional3() {
		System.out
				.println("==================================Probando el segundo constructor con nota inferior a 10 y mencion de honor true");
		testConstructor2(new AsignaturaImpl("Circuitos", "2050002", 6.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 2, new DepartamentoImpl("DTE")), 2014,
				Convocatoria.PRIMERA, 6.0, Boolean.TRUE);
	}

	private static void testConstructor1(Asignatura asignatura,
			Integer cursoAcademico, Convocatoria tipo, Double valor) {

		try {

			Nota n = new NotaImpl(asignatura, cursoAcademico, tipo, valor);
			muestroNota(n);

		} catch (ExcepcionNotaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionNotaNoValida, error en el primer constructor");
		} catch (Exception e) {
			System.out
					.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}

	private static void testConstructor2(Asignatura asignatura,
			Integer cursoAcademico, Convocatoria tipo, Double valor,
			Boolean mencionDeHonor) {

		try {

			Nota n = new NotaImpl(asignatura, cursoAcademico, tipo, valor,
					mencionDeHonor);
			muestroNota1(n);

		} catch (ExcepcionNotaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionNotaNoValida, error en el segundo constructor");
		} catch (Exception e) {
			System.out
					.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}

	private static void muestroNota(Nota n) {

		System.out.println("Nota: --> <" + n);

		System.out.println("\tAsignatura: <" + n.getAsignatura() + ">");
		System.out
				.println("\tCurso Academico: <" + n.getCursoAcademico() + ">");
		System.out.println("\tTipo Convocatoria: <" + n.getConvocatoria() + ">");
		System.out.println("\tValor: <" + n.getValor() + ">");
	}

	private static void muestroNota1(Nota n) {

		System.out.println("Nota: --> <" + n);

		System.out.println("\tAsignatura: <" + n.getAsignatura() + ">");
		System.out
				.println("\tCurso Academico: <" + n.getCursoAcademico() + ">");
		System.out.println("\tTipo Convocatoria: <" + n.getConvocatoria() + ">");
		System.out.println("\tValor: <" + n.getValor() + ">");
		System.out.println("\tMencion de honor: <" + n.getMencionHonor()
				+ ">");

	}
}
