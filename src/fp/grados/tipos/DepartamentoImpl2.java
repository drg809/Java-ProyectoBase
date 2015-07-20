package fp.grados.tipos;

import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DepartamentoImpl2 extends DepartamentoImpl {

	public DepartamentoImpl2(String nombre) {
		super(nombre);
	}

	public void borraTutorias() {
		getProfesores().stream().forEach(Profesor::borraTutorias);
	}

	public void borraTutorias(Categoria categoria) {
		getProfesores().stream()
				.filter(p -> p.getCategoria().equals(categoria))
				.forEach(Profesor::borraTutorias);
	}

	public Boolean existeProfesorAsignado(Asignatura a) {
		return (!(getProfesores().stream()
				.flatMap(p -> p.getAsignaturas().stream())
				.filter(as -> as.equals(a)).collect(Collectors.toSet())
				.isEmpty()));
	}

	public Boolean estanTodasAsignaturasAsignadas() {
		return getAsignaturas().stream()
				.filter(a -> existeProfesorAsignado(a).equals(false))
				.collect(Collectors.toSet()).isEmpty();
	}

	public void eliminaAsignacionProfesorado(Asignatura a) {
		getProfesores().stream().filter(p -> p.getAsignaturas().contains(a))
				.collect(Collectors.toSet())
				.forEach(p -> p.eliminaAsignatura(a));
	}

	public SortedMap<Profesor, SortedSet<Tutoria>> getTutoriasPorProfesor() {
		Map<Profesor, SortedSet<Tutoria>> tutoriasPorProfesor = getProfesores()
				.stream().collect(
						Collectors.toMap(p -> p, p -> p.getTutorias()));

		return new TreeMap<>(tutoriasPorProfesor);
	}
}
