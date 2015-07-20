package fp.grados.tipos;

import java.util.HashSet;
import java.util.Set;

import fp.grados.excepciones.ExcepcionDespachoNoValido;

public class DespachoImpl extends EspacioImpl implements Despacho {
	
	private Set<Profesor> profesores = new HashSet<Profesor>();

	public DespachoImpl(String s) {
		super(s.concat(",OTRO"));
	}
	
	public DespachoImpl(String nombre, Integer capacidad, Integer planta, Set<Profesor> profesores) {
		
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
		this.profesores = profesores;
		CheckCapacidad(profesores, capacidad);
	}

	public DespachoImpl(String nombre, Integer capacidad, Integer planta, Profesor profesor) {
		
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
		profesores.add(profesor);
		CheckCapacidad(profesores, capacidad);

	}

	public DespachoImpl(String nombre, Integer capacidad, Integer planta) {
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
		profesores = new HashSet<Profesor>();
		CheckCapacidad(profesores, capacidad);

	}

	public Set<Profesor> getProfesores() {
		return profesores;
	}

	public void setTipo(TipoEspacio tipo) {
		throw new UnsupportedOperationException("");
	}

	public void setProfesores(Set<Profesor> profesores) {
		CheckCapacidad(profesores, getCapacidad());
		this.profesores = profesores;
	}
	
	private void CheckCapacidad(Set<Profesor> profesores, Integer capacidad) {
		if (profesores.size() > capacidad) {
			throw new ExcepcionDespachoNoValido("No puede haber más profesores que la capacidad del despacho.");
		}
	}

	public String toString() {
		return super.toString() + profesores.toString();
	}

}
