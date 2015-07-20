package fp.grados.tipos;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import fp.grados.excepciones.ExcepcionCentroOperacionNoPermitida;

public class CentroImpl2 extends CentroImpl{
	
	public CentroImpl2 (String nombre, String direccion, Integer numPlantas, Integer numSotanos) {
		super(nombre, direccion, numPlantas, numSotanos);
	}
	
	public Espacio getEspacioMayorCapacidad() {
		Comparator<Espacio> cmp = Comparator.comparing(Espacio::getCapacidad);
		Optional<Espacio> res = getEspacios().stream().max(cmp);
		if(!res.isPresent()) {
			throw new ExcepcionCentroOperacionNoPermitida();
		} else {
			return res.get();
		}
	}
	
	//private Integer cuentaEspaciosTipo(TipoEspacio tipo, Set<Espacio> espacios) {
	//	return (int) espacios.stream().filter(esp -> esp.getTipo().equals(tipo)).count();
	//}
	
	public Integer[] getConteosEspacios() {
		//Set<Espacio> espacios = getEspacios();
		//return new Integer[] {
		//		cuentaEspaciosTipo(TipoEspacio.TEORIA, espacios),
		//		cuentaEspaciosTipo(TipoEspacio.LABORATORIO, espacios),
		//		cuentaEspaciosTipo(TipoEspacio.SEMINARIO, espacios),
		//		cuentaEspaciosTipo(TipoEspacio.EXAMEN, espacios),
		//		cuentaEspaciosTipo(TipoEspacio.OTRO, espacios) };
	
		Integer[] res = {0,0,0,0,0};
		getEspacios().stream().forEach(esp -> res[esp.getTipo().ordinal()]++);
		return res;
	
	}
	
	public Set<Profesor> getProfesores() {
		return getDespachos().stream().flatMap(des -> des.getProfesores().stream()).collect(Collectors.toSet());
	}
	
	public Set<Profesor> getProfesores(Departamento d) {
		return getProfesores().stream().filter(prof -> prof.getDepartamento().equals(d)).collect(Collectors.toSet());
	}
	
	public Set<Despacho> getDespachos() {
		return getEspacios().stream().filter(e -> e instanceof Despacho).map(e -> (Despacho) e).collect(Collectors.toSet());
	}
	
	public Set<Despacho> getDespachos (Departamento d){
		return getDespachos().stream().filter(des -> existeProfesorDep(des.getProfesores(), d)).collect(Collectors.toSet());
	}
	
	private boolean existeProfesorDep (Set<Profesor> profesores, Departamento d) {
		return getProfesores().stream().anyMatch(prof -> prof.getDepartamento().equals(d));
	}
	
	public SortedMap<Profesor, Despacho> getDespachosPorProfesor() {
		Map<Profesor, Despacho> res = getProfesores().stream().filter(p -> despachoProfesor(p)).collect(Collectors.toMap(p -> p, p -> existeDespacho(p)));
		
		return new TreeMap<Profesor, Despacho>(res);
	}
	
	private Boolean despachoProfesor(Profesor p) {
		return getDespachos().stream().anyMatch(d -> d.getProfesores().contains(p));
	}
	
	private Despacho existeDespacho(Profesor p) {
		return getDespachos().stream().filter(d -> d.getProfesores().contains(p)).findFirst().get();
	}
}
