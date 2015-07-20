package fp.grados.tipos;

import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

public interface Departamento extends Comparable<Departamento> {

	String getNombre();

	Set<Profesor> getProfesores();

	void nuevoProfesor(Profesor prof);

	void eliminaProfesor(Profesor prof);
	
	Set<Asignatura> getAsignaturas();

	void nuevaAsignatura(Asignatura asig);

	void eliminaAsignatura(Asignatura asig);
	
	void borraTutorias();
	
	void borraTutorias(Categoria c);
	
	Boolean existeProfesorAsignado(Asignatura a);
	
	Boolean estanTodasAsignaturasAsignadas();

	void eliminaAsignacionProfesorado(Asignatura a);
	
	SortedMap<Asignatura, SortedSet<Profesor>> getProfesoresPorAsignatura();
	
	SortedMap<Profesor, SortedSet<Tutoria>> getTutoriasPorProfesor();
	
	Profesor getProfesorMaximaDedicacionMediaPorAsignatura();
}
