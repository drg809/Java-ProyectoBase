package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalTime;

public interface Tutoria extends Comparable<Tutoria> {

	LocalTime getHoraComienzo();

	LocalTime getHoraFin();

	Integer getDuracion();

	DayOfWeek getDiaSemana();

}
