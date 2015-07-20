package fp.grados.tipos;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class DepartamentoImpl implements Departamento {

	private String nombre;
	private Set<Profesor> profesores = new HashSet<Profesor>();
	private Set<Asignatura> asignaturas = new HashSet<Asignatura>();

	public DepartamentoImpl(String nombre) {

		this.nombre = nombre;
		profesores = new HashSet<Profesor>();
		asignaturas = new HashSet<Asignatura>();

	}

	public String getNombre() {
		return nombre;
	}

	public Set<Profesor> getProfesores() {
		return new HashSet<Profesor>(profesores);
	}

	public Set<Asignatura> getAsignaturas() {
		return new HashSet<Asignatura>(asignaturas);
	}

	public void nuevoProfesor(Profesor prof) {
		profesores.add(prof);
		prof.setDepartamento(this);
	}

	public void eliminaProfesor(Profesor prof) {
		if (profesores.contains(prof)) {
			profesores.remove(prof);
			prof.setDepartamento(null);
		}
	}

	public void nuevaAsignatura(Asignatura asig) {
		asignaturas.add(asig);
		asig.setDepartamento(this);

	}

	public void eliminaAsignatura(Asignatura asig) {
		if (asignaturas.contains(asig)) {
			asignaturas.remove(asig);
			asig.setDepartamento(null);

		}
	}

	public void borraTutorias() {
		for (Profesor p : this.getProfesores()) {
			p.borraTutorias();
		}
	}

	public void borraTutorias(Categoria c) {
		for (Profesor p : this.getProfesores()) {
			if (p.getCategoria() == c) {
				p.borraTutorias();
			}
		}
	}

	public Boolean existeProfesorAsignado(Asignatura a) {
		return (!(getProfesores().stream().flatMap(p -> p.getAsignaturas()
				.stream()).filter(asig -> asig.equals(a))
				.collect(Collectors.toSet()).isEmpty()));
	}

	public Boolean estanTodasAsignaturasAsignadas() {
		return getAsignaturas().stream()
				.filter(a -> existeProfesorAsignado(a).equals(false))
				.collect(Collectors.toSet()).isEmpty();
	}

	public void eliminaAsignacionProfesorado(Asignatura a) {
		for (Profesor p : this.getProfesores()) {
			if (p.getAsignaturas().contains(a)) {
				p.eliminaAsignatura(a);
			}
		}
	}

	public SortedMap<Asignatura, SortedSet<Profesor>> getProfesoresPorAsignatura() {
		SortedMap<Asignatura, SortedSet<Profesor>> profesoresPorAsignatura = new TreeMap<Asignatura, SortedSet<Profesor>>();
		for (Profesor p : this.getProfesores()) {
			anyadeAsinaturas(p.getAsignaturas(), p, profesoresPorAsignatura);
		}

		return profesoresPorAsignatura;

	}

	private void anyadeAsinaturas(List<Asignatura> asignaturas, Profesor p,
			SortedMap<Asignatura, SortedSet<Profesor>> profesoresPorAsignatura) {
		for (Asignatura a : asignaturas) {
			if (!(profesoresPorAsignatura.containsKey(a))) {
				SortedSet<Profesor> profe = new TreeSet<Profesor>();
				profe.add(p);
				profesoresPorAsignatura.put(a, profe);
			} else {
				profesoresPorAsignatura.get(a).add(p);
			}
		}
	}

	public SortedMap<Profesor, SortedSet<Tutoria>> getTutoriasPorProfesor() {
		SortedMap<Profesor, SortedSet<Tutoria>> tutoriasPorProfesor = new TreeMap<Profesor, SortedSet<Tutoria>>();
		for (Profesor p : this.getProfesores()) {
			tutoriasPorProfesor.put(p, p.getTutorias());
		}
		return tutoriasPorProfesor;
	}

	public Profesor getProfesorMaximaDedicacionMediaPorAsignatura() {
		Comparator<Profesor> cmp = Comparator.comparing(prof -> prof
				.getDedicacionTotal() / prof.getAsignaturas().size());
		return getProfesores().stream()
				.filter(prof -> !prof.getAsignaturas().isEmpty()).max(cmp)
				.get();
	}

	public String toString() {
		return getNombre() + "";

	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		boolean res = false;
		if (o instanceof Departamento) {
			Departamento d = (Departamento) o;
			res = this.getNombre().equals(d.getNombre());
		}
		return res;
	}

	public int hashCode() {
		return getNombre().hashCode() * 31;
	}

	public int compareTo(Departamento d) {
		int res;
		if (d == null) {
			throw new NullPointerException();
		}
		res = getNombre().compareTo(d.getNombre());
		return res;
	}
}