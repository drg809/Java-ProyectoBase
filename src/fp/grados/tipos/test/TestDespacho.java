package fp.grados.tipos.test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import fp.grados.excepciones.ExcepcionDespachoNoValido;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Despacho;
import fp.grados.tipos.DespachoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.TipoEspacio;

public class TestDespacho {
	public static void main(String[] args) {
		
		testConstructor1Normal();
		testConstructor1Excepcional();
		
		testConstructor2Normal();
		
		testConstructor3Normal();

		testSetTipo();

	}

	private static void testConstructor1Normal() {
		
		HashSet<Profesor> profesores = new HashSet<Profesor>();
		Profesor profe1 = new ProfesorImpl("28846273H", "Daniel", "Rodriguez", LocalDate.of(1950, 3, 15), "daniel@alum.us.es", Categoria.AYUDANTE, new DepartamentoImpl("CED"));
		Profesor profe2 = new ProfesorImpl("30268248H", "Julian", "Ninguno", LocalDate.of(1945, 3, 15), "jninguno@gmail.com", Categoria.AYUDANTE, new DepartamentoImpl("CED"));
		profesores.add(profe1);
		profesores.add(profe2);
		System.out.println("\n=================================== Probando el primer constructor");
		testConstructor1("D1.30", 40, 2, profesores);
	}

	private static void testConstructor1Excepcional() {
		
		HashSet<Profesor> profesores = new HashSet<Profesor>();
		Profesor profe1 = new ProfesorImpl("28846273H", "Daniel", "Rodriguez", LocalDate.of(1950, 3, 15), "daniel@alum.us.es", Categoria.AYUDANTE, new DepartamentoImpl("CED"));
		Profesor profe2 = new ProfesorImpl("30268248H", "Julian", "Nadie", LocalDate.of(1945, 3, 15), "jnadie@gmail.com", Categoria.AYUDANTE, new DepartamentoImpl("CED"));
		profesores.add(profe1);
		profesores.add(profe2);
		System.out.println("\n================================== Probando el primer constructor con capacidad menor al numero de profesores");
		
		testConstructor1("D1.44", 1, 2, profesores);
	}

	private static void testConstructor2Normal() {
		
		System.out.println("\n=================================== Probando el segundo constructor");
		
		testConstructor2( "D3.15", 50, 2, new ProfesorImpl("28846273H", "Daniel", "Rodriguez", LocalDate.of(1950, 3, 15), "daniel@alum.us.es",Categoria.AYUDANTE, new DepartamentoImpl("CED")));
	}

	private static void testConstructor3Normal() {
		
		System.out.println("\n===================================== Probando el tercer constructor");
		
		testConstructor3( "D2.12", 50, 2);
	}

	private static void testSetTipo() {
		
		System.out.println("\n================================== Probando SetTipo");
		
		try {
			Despacho d = new DespachoImpl("D2.5", 30,  2);
			d.setTipo(TipoEspacio.LABORATORIO);
		
		} catch (UnsupportedOperationException d) {
			System.out.println("******************** Se ha capturado la excepción UnsupportedOperationException ");
		
		} catch (Exception e) {
			System.out.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}

	private static void testConstructor1(String nombre, Integer capacidad, Integer planta, Set<Profesor> profesores) {
		
		try {
			Despacho d = new DespachoImpl(nombre, capacidad, planta,  profesores);
			mostrarDespacho1(d);
		
		} catch (ExcepcionDespachoNoValido d) {
			System.out.println("*************************** Se ha capturado la excepción ExcepcionDespachoNoValido");
		
		} catch (Exception e) {
			System.out.println("*************************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}

	private static void testConstructor2(String nombre, Integer capacidad, Integer planta, Profesor profesor) {
		
		try {
			Despacho d = new DespachoImpl(nombre,  capacidad, planta, profesor);
			mostrarDespacho2(d);
			
		} catch (ExcepcionDespachoNoValido d) {
			System.out.println("*************************** Se ha capturado la excepción ExcepcionDespachoNoValido");

		} catch (Exception e) {
			System.out.println("*************************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}

	private static void testConstructor3(String nombre,  Integer capacidad, Integer planta) {
		
		try {
			Despacho d = new DespachoImpl(nombre,  capacidad, planta);
			mostrarDespacho1(d);
		
		} catch (ExcepcionDespachoNoValido d) {
			System.out.println("*************************** Se ha capturado la excepción ExcepcionDespachoNoValido");
	
		} catch (Exception e) {
			System.out.println("*************************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	

	private static void mostrarDespacho1(Despacho d) {
		
		System.out.println("Despacho --> <" + d + ">");
		System.out.println("\tTipo: <" + d.getTipo() + ">");
		System.out.println("\tNombre: <" + d.getNombre() + ">");
		System.out.println("\tCapacidad: <" + d.getCapacidad() + ">");
		System.out.println("\tPlanta: <" + d.getPlanta() + ">");
		System.out.println("\tProfesores: <" + d.getProfesores() + ">");
	}

	private static void mostrarDespacho2(Despacho d) {
		
		System.out.println("Despacho --> <" + d + ">");
		System.out.println("\tTipo: <" + d.getTipo() + ">");
		System.out.println("\tNombre: <" + d.getNombre() + ">");
		System.out.println("\tCapacidad: <" + d.getCapacidad() + ">");
		System.out.println("\tPlanta: <" + d.getPlanta() + ">");
		System.out.println("\tProfesor: <" + d.getProfesores() + ">");
	}
}
