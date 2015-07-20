package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.SortedSet;

public interface Profesor extends Persona {

	Categoria getCategoria();

	SortedSet<Tutoria> getTutorias();

	void setCategoria(Categoria categoria);

	void nuevaTutoria(LocalTime horaComienzo, Integer duracion, DayOfWeek dia);

	void borraTutoria(LocalTime horaComienzo, DayOfWeek dia);

	void borraTutorias();
	
	Departamento getDepartamento();
	
	void setDepartamento(Departamento d);

	List<Asignatura> getAsignaturas();

	List<Double> getCreditos();

	void imparteAsignatura(Asignatura a, Double dedicacion);

	Double dedicacionAsignatura(Asignatura a);

	void eliminaAsignatura(Asignatura a);

	Double getDedicacionTotal();
}
