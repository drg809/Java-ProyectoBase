package fp.grados.tipos.test;

import fp.grados.excepciones.ExcepcionTutoriaNoValida;
import fp.grados.tipos.Tutoria;
import fp.grados.tipos.TutoriaImpl;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class TestTutoria {

	public static void main(String[] args) {
		testConstructor1Normal();
		testConstructor1Excepcional1();
		testConstructor1Excepcional2();
		testConstructor1Excepcional3();

		testConstructor2Normal();
		testConstructor2Excepcional1();
		testConstructor2Excepcional2();

	

	}

	private static void testConstructor1Normal() {
		System.out
				.println("================================== Probando el primer constructor");
		testConstructor1(DayOfWeek.WEDNESDAY, LocalTime.of(13, 30), LocalTime.of(12, 30));
	}
	
	private static void testConstructor1Excepcional1() {
		System.out
				.println("================================== Probando el primer constructor con un día no valido (Sabado)");
		testConstructor1(DayOfWeek.SATURDAY, LocalTime.of(13, 30), LocalTime.of(12, 30));
	}

	
	private static void testConstructor1Excepcional2() {
		System.out
				.println("================================== Probando el primer constructor con un día no valido (Domingo)");
		testConstructor1(DayOfWeek.SUNDAY, LocalTime.of(13, 30), LocalTime.of(12, 30));
	}
	
	private static void testConstructor1Excepcional3() {
		System.out
				.println("================================== Probando el primer constructor con una duracion no valida");
		testConstructor1(DayOfWeek.WEDNESDAY, LocalTime.of(12, 40), LocalTime.of(12, 30));
	}
	
	private static void testConstructor2Normal() {
		System.out
				.println("================================== Probando el segundo constructor");
		testConstructor2(DayOfWeek.THURSDAY, LocalTime.of(12, 30), 120);
	}
	
	private static void testConstructor2Excepcional1() {
		System.out
				.println("==================================Probando el segundo constructor con un día no valido (Sabado)");
		testConstructor2(DayOfWeek.SATURDAY, LocalTime.of(12, 30), 45);
	}

	
	private static void testConstructor2Excepcional2() {
		System.out
				.println("==================================Probando el segundo constructor con una duracion no valida");
		testConstructor2(DayOfWeek.THURSDAY, LocalTime.of(12, 30), 10);
		
	}
	


	private static void testConstructor1(DayOfWeek diaSemana, LocalTime horaComienzo, LocalTime horaFin) {
		
		try {
		Tutoria t = new TutoriaImpl(diaSemana, horaFin, horaComienzo);
		mostrarTutoria(t);
		
		 } catch (ExcepcionTutoriaNoValida e) { System.out .println(
		 "******************** Se ha capturado la excepción ExcepcionTutoriaNoValida"
		 
		 ); } catch (Exception e) { System.out .println(
		 "******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!"
		 ); }
		 
	}

	private static void testConstructor2(DayOfWeek diaSemana, LocalTime horaComienzo, Integer duracion) {
		 
		try {
		Tutoria t = new TutoriaImpl(diaSemana, horaComienzo, duracion);
		mostrarTutoria(t);
		
		  
		} catch (ExcepcionTutoriaNoValida e) { System.out .println(
		  "******************** Se ha capturado la excepción ExcepcionTutoriaNoValida"
		  
		); } catch (Exception e) { System.out .println(
		"******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!"
		); }
		
	}

	private static void mostrarTutoria(Tutoria t) {
		System.out.println("Tutoria --> <" + t + ">");
		System.out.println("\tDia de la semana: <" + t.getDiaSemana() + ">");
		System.out.println("\tHora de comienzo: <" + t.getHoraComienzo() + ">");
		System.out.println("\tHora de Fin: <" + t.getHoraFin() + ">");
		System.out.println("\tDuracion: <" + t.getDuracion() + ">");


	}
	
	/*private static void mostrarTutoria1(Tutoria t) {
		System.out.println("Tutoria --> <" + t + ">");
		System.out.println("\tDia de la semana: <" + t.getDiaSemana() + ">");
		System.out.println("\tHora de comienzo: <" + t.getHoraComienzo() + ">");
		System.out.println("\tHora de Fin: <" + t.getHoraFin() + ">");
	}*/

}
