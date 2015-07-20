package fp.grados.tipos.test;

import fp.grados.excepciones.ExcepcionExpedienteOperacionNoPermitida;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Expediente;
import fp.grados.tipos.ExpedienteImpl;
import fp.grados.tipos.Nota;
import fp.grados.tipos.NotaImpl;
import fp.grados.tipos.Convocatoria;
import fp.grados.tipos.TipoAsignatura;



public class TestExpediente {
	public static void main(String[] args) {

		testConstructorNormal();

		testNuevaNota();
		testNuevaNotaExcepcional();
		
	}

	private static void testConstructorNormal() {

		System.out
				.println("\n=================================== Probando el primer constructor");

		testConstructor();
	}
	
	private static void testConstructor() {

		try {

			Expediente e = new ExpedienteImpl();
			muestraExpediente(e);

		} catch (ExcepcionExpedienteOperacionNoPermitida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionExpedienteOperacionNoPermitida, error en el primer constructor");
		} catch (Exception e) {
			System.out
					.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	
	private static void testNuevaNota() {
		System.out
				.println("\n================================Probando NuevaNota");

		Expediente e1 = new ExpedienteImpl();
		Nota n = new NotaImpl(new AsignaturaImpl("Circuitos", "2050002", 6.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 2, new DepartamentoImpl("DTE")), 1999,
				Convocatoria.PRIMERA, 6.0);
		
		testNuevaNotaCons(e1, n);
	}

	private static void testNuevaNotaExcepcional() {
		
		System.out
				.println("\n================================Probando NuevaNota con tres convocatorias");

		Expediente e1 = new ExpedienteImpl();
		Nota n = new NotaImpl(new AsignaturaImpl("Circuitos", "2050002", 6.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 2, new DepartamentoImpl("DTE")), 1999,
				Convocatoria.PRIMERA, 5.0);
		Nota n1 = new NotaImpl(new AsignaturaImpl("Circuitos", "2050002", 6.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 2, new DepartamentoImpl("DTE")), 1999,
				Convocatoria.SEGUNDA, 5.0);
		Nota n2 = new NotaImpl(new AsignaturaImpl("Circuitos", "2050002", 6.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 2, new DepartamentoImpl("DTE")), 1999,
				Convocatoria.TERCERA, 5.0);
		testNuevaNotaConsExp(e1, n, n1, n2);
	}

	private static void testNuevaNotaCons(Expediente e1, Nota n) {

		try {
			System.out
					.println("Las notas existentes antes de la operacion son: "
							+ e1.getNotas());
			System.out.println("La nota a añadir es: " + n);
			e1.nuevaNota(n);
			System.out.println("Las notas existentes despues de la operación son: "
					+ e1.getNotas());

		} catch (ExcepcionExpedienteOperacionNoPermitida e) {
			System.out
					.println("*************************** Se ha capturado la excepción ExcepcionExpedienteOperacionNoPermitida");

		} catch (Exception e) {
			System.out
					.println("*************************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	
	private static void testNuevaNotaConsExp(Expediente e1, Nota n, Nota n1, Nota n2) {

		try {
			System.out
					.println("Las notas existentes antes de la operacion son: "
							+ e1.getNotas());
			System.out.println("La nota a añadir son: " + n + n1 + n2);
			e1.nuevaNota(n);
			e1.nuevaNota(n1);
			e1.nuevaNota(n2);

			System.out.println("Las notas existentes despues de la operación son: "
					+ e1.getNotas());

		} catch (ExcepcionExpedienteOperacionNoPermitida e) {
			System.out
					.println("*************************** Se ha capturado la excepción ExcepcionExpedienteOperacionNoPermitida");

		} catch (Exception e) {
			System.out
					.println("*************************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}

	private static void muestraExpediente(Expediente e) {
		System.out.println("Expediente --> <" + e + ">");
		System.out.println("\tNotas: <" + e.getNotas() + ">");
		System.out.println("\tNota media: <" + e.getNotaMedia() + ">");
	}

}