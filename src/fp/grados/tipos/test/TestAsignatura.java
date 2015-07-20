package fp.grados.tipos.test;

import fp.grados.excepciones.ExcepcionAsignaturaNoValida;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.TipoAsignatura;

public class TestAsignatura {

	public static void main(String[] args) throws Exception{
		testConstructorNormal();
		testConstructorExcepcion1();
		testConstructorExcepcion2();
		testConstructorExcepcion3();
		testConstructorExcepcion4();
		testConstructorExcepcion5();
		testConstructorExcepcion6();
		testConstructorExcepcion7();
		
		testSetDepartamentoNormal();
	}
	

	private static void testConstructorNormal() {
		System.out
				.println("==================================Probando el constructor");
		testConstructor("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1, new DepartamentoImpl("LSI"));
	}

	private static void testConstructorExcepcion1() {
		System.out
		.println("==================================Probando el constructor, código de asignatura más largo");
		testConstructor("Fundamentos de Programación","20500010",12.0, TipoAsignatura.ANUAL, 1, new DepartamentoImpl("LSI"));
	}
	
	private static void testConstructorExcepcion2() {
		System.out
		.println("==================================Probando el constructor, código de asignatura más corto");
		testConstructor("Fundamentos de Programación","205000",12.0, TipoAsignatura.ANUAL, 1, new DepartamentoImpl("LSI"));
	}
	
	private static void testConstructorExcepcion3() {
		System.out
				.println("==================================Probando el constructor, código de asignatura no numérico");
		testConstructor("Fundamentos de Programación","2A50001",12.0, TipoAsignatura.ANUAL, 1, new DepartamentoImpl("LSI"));
	}
	
	private static void testConstructorExcepcion4() {
		System.out
				.println("==================================Probando el constructor, créditos incorrectos (0.0)");
		testConstructor("Fundamentos de Programación","2050001",0.0, TipoAsignatura.ANUAL, 1, new DepartamentoImpl("LSI"));
	}
		
	private static void testConstructorExcepcion5() {
		System.out
				.println("==================================Probando el constructor, créditos incorrectos (-1.0)");
		testConstructor("Fundamentos de Programación","2050001",-1.0, TipoAsignatura.ANUAL, 1, new DepartamentoImpl("LSI"));
	}
	
	
	private static void testConstructorExcepcion6() {
		System.out
				.println("==================================Probando el constructor, curso menor de 1");
		testConstructor("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, -2, new DepartamentoImpl("LSI"));
	}
	
	private static void testConstructorExcepcion7() {
		System.out
				.println("==================================Probando el constructor, curso mayor de 4");
		testConstructor("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 5, new DepartamentoImpl("LSI"));
	}
	
	/******************************** METODOS AUXILIARES **************************/
	
	private static void testConstructor(String nombre, String codigo, Double creditos,
			TipoAsignatura tipo, Integer curso, Departamento departamento) {
		 try {
			Asignatura a = new AsignaturaImpl(nombre, codigo, creditos, tipo, curso, departamento);
			mostrarAsignatura(a);
		} catch (ExcepcionAsignaturaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionAsignaturaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	
	private static void testSetDepartamentoNormal() {
		System.out
				.println("================================== Probando setDepartamento");
		Asignatura a = new AsignaturaImpl("Fundamentos de Programación","2050001",12.0, TipoAsignatura.ANUAL, 1, new DepartamentoImpl("LSI"));
		
		testSetDepartamentoNormal(a, new DepartamentoImpl("DTE"));
	}
	
	private static void testSetDepartamentoNormal(Asignatura a, Departamento d) {

		try {
			System.out.println("El departamento antes de la operación es: "
					+ a.getDepartamento());
			System.out.println("El nuevo departamento es: " + d);
			a.setDepartamento(d);
			System.out.println("El departamento después de la operación es: "
					+ a.getDepartamento());

		} catch (ExcepcionAsignaturaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionAsignaturaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. setDepartamento no funciona correctamente");
		}

	}

	private static void mostrarAsignatura(Asignatura a) {		
		System.out.println("Asignatura --> <" + a + ">");
		System.out.println("\tNombre: <" + a.getNombre() + ">");
		System.out.println("\tAcronimo: <" + a.getAcronimo() + ">");
		System.out.println("\tCódigo: <" + a.getCodigo() + ">");
		System.out.println("\tCréditos: <" + a.getCreditos() + ">");
		System.out.println("\tTipo: <" + a.getTipo() + ">");
		System.out.println("\tCurso: <" + a.getCurso() + ">");
		System.out.println("\tDepartamento: <" + a.getDepartamento() + ">");

	}

}