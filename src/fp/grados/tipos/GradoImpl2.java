package fp.grados.tipos;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class GradoImpl2 extends GradoImpl {
	
	public GradoImpl2 (String nombre, Set<Asignatura> asiganturasObligatorias, Set<Asignatura> asignaturasOptativas, Double numeroMinimoCreditosOptativas) {
		super(nombre, asiganturasObligatorias, asignaturasOptativas, numeroMinimoCreditosOptativas);
	}
	
	public Double getNumeroTotalCreditos() {
		return getAsignaturasObligatorias().stream().mapToDouble(asig -> asig.getCreditos()).sum() + getNumeroMinimoCreditosOptativas();
	}
	
	public Set<Asignatura> getAsignaturas(Integer curso) {
		Set<Asignatura> res = new HashSet<Asignatura>();
		Set<Asignatura> seleccion = res.stream().filter(a -> a.getCurso().equals(curso)).collect(Collectors.toSet());
		
		seleccion.addAll(getAsignaturas());
		
		res.addAll(seleccion);
		
		return res;
	}
	
	private Set<Asignatura> getAsignaturas() {
		Set<Asignatura> res = new HashSet<Asignatura>();
		res.addAll(getAsignaturasObligatorias());
		res.addAll(getAsignaturasOptativas());
		
		return res;
	}
	
	public Asignatura getAsignatura(String codigo) {
		Optional<Asignatura> res = getAsignaturas().stream().filter(a -> a.getCodigo().equals(codigo)).findFirst();
		
		if(res.isPresent()) {
			return res.get();
		} else {
			return null;
		}
	}
	
	public Set<Profesor> getProfesores() {
		Set<Profesor> profesores = new HashSet<Profesor>();
		getDepartamentos().stream().forEach(d -> profesores.addAll(d.getProfesores()));
		
		return profesores;
	}
	
	public Set<Departamento> getDepartamentos() {
		return getAsignaturas().stream().map(a -> a.getDepartamento()).collect(Collectors.toSet());
	}
	
	public SortedMap<Asignatura, Double> getCreditosPorAsignatura() {
		
		SortedMap<Asignatura, Double> res = new TreeMap<Asignatura, Double>();
		getAsignaturas().stream().forEach(a -> res.put(a, a.getCreditos()));
		
		return res;
	}
}
