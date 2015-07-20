package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import fp.grados.excepciones.ExcepcionTutoriaNoValida;
import fp.grados.tipos.Tutoria;


public class TutoriaImpl implements Tutoria {

	private LocalTime horaComienzo;
	private LocalTime horaFin;
	public Integer duracion;
	private DayOfWeek diaSemana;
	
	public TutoriaImpl(String s) {
		String [] t = s.split(",");
		if(t.length != 3) {
			throw new IllegalArgumentException("El tamaño del constructor es erroneo");
		}
		DayOfWeek diaSemanaT = null;
		switch(t[0].trim()) {
			case "L":
				diaSemanaT = DayOfWeek.MONDAY;
				break;
			case "M":
				diaSemanaT = DayOfWeek.TUESDAY;
				break;
			case "X":
				diaSemanaT = DayOfWeek.WEDNESDAY;
				break;
			case "J":
				diaSemanaT = DayOfWeek.THURSDAY;
				break;
			case "V":
				diaSemanaT = DayOfWeek.FRIDAY;
				break;	
		}
		diaSemana = diaSemanaT;
		checkDiaSemana(diaSemana);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:m");
		horaComienzo = LocalTime.parse(t[1].trim(), formatter);
		horaFin = LocalTime.parse(t[2].trim(), formatter);
		
		duracion=getDuracion();
		checkDuracion(duracion);
	}

	public TutoriaImpl(DayOfWeek diaSemana, LocalTime horaComienzo,
			LocalTime horaFin) {

		this.diaSemana = diaSemana;
		this.horaComienzo = horaComienzo;
		this.horaFin = horaFin;
		duracion = (int) getHoraComienzo().until(getHoraFin(), ChronoUnit.MINUTES);
		checkDiaSemana(diaSemana);
		checkDuracion(duracion);


	}

	public TutoriaImpl(DayOfWeek diaSemana, LocalTime horaComienzo, Integer duracion) {
		checkDuracion(duracion);
		checkDiaSemana(diaSemana);
		
		this.diaSemana = diaSemana;
		this.horaComienzo = horaComienzo;
		this.duracion = duracion;
		horaFin = horaComienzo.plusMinutes(duracion);
	

	}

	public DayOfWeek getDiaSemana() {

		return diaSemana;
	}

	public LocalTime getHoraComienzo() {

		return horaComienzo;
	}

	public LocalTime getHoraFin() {

		return horaFin;
	}

	public Integer getDuracion() {

		duracion = (int) getHoraComienzo().until(getHoraFin(),
				ChronoUnit.MINUTES);
		return duracion;
	}
	
	private void checkDiaSemana(DayOfWeek diaSemana) {
		if(!(diaSemana == DayOfWeek.MONDAY || diaSemana == DayOfWeek.THURSDAY || diaSemana == DayOfWeek.WEDNESDAY || diaSemana == DayOfWeek.TUESDAY || diaSemana == DayOfWeek.FRIDAY)){
					
				throw new ExcepcionTutoriaNoValida("La duracion minima de la tutoria es 15.");
		}
	}
	
	private void checkDuracion(Integer duracion) {
		if(duracion < 15){
					
				throw new ExcepcionTutoriaNoValida("La duracion minima de la tutoria es 15.");
		}
	}

	public String toString() {
		String res = null;
		if (getDiaSemana() == DayOfWeek.MONDAY) {
			res = "L";
		} else if (getDiaSemana() == DayOfWeek.TUESDAY) {
			res = "M";
		} else if (getDiaSemana() == DayOfWeek.WEDNESDAY) {
			res = "X";
		} else if (getDiaSemana() == DayOfWeek.THURSDAY) {
			res = "J";
		} else if (getDiaSemana() == DayOfWeek.FRIDAY) {
			res = "V";
		}
		
		return res
		
				+ " " + getHoraComienzo().format(DateTimeFormatter.ofPattern("HH:mm")) + "-" + getHoraFin().format(DateTimeFormatter.ofPattern("HH:mm"));
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		boolean res = false;
		if (o instanceof Tutoria) {
			Tutoria t = (Tutoria) o;
			res = this.getDiaSemana().equals(t.getDiaSemana())
					&& this.getHoraComienzo().equals(t.getHoraComienzo());
		}
		return res;
	}

	public int hashCode() {
		return getDiaSemana().hashCode() * 31
				+ getHoraComienzo().hashCode();
	}

	public int compareTo(Tutoria t) {
		int res;
		if (t == null) {
			throw new NullPointerException();
		}
		res = getDiaSemana().compareTo(t.getDiaSemana());
		if (res == 0) {
			res = getHoraComienzo().compareTo(t.getHoraComienzo());
		}
		return res;
	}

}
