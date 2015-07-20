package fp.grados.tipos;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionCentroNoValido;
import fp.grados.excepciones.ExcepcionCentroOperacionNoPermitida;

public class CentroImpl implements Centro {
	private String nombre;
	private String direccion;
	private Integer numeroDePlantas;
	private Integer numeroDeSotanos;
	private Set<Espacio> espacios = new HashSet<Espacio>();

	public CentroImpl(String nombre, String direccion, Integer numeroDePlantas,
			Integer numeroDeSotanos) {

		checkPlanta(numeroDePlantas);
		checkSotano(numeroDeSotanos);

		this.nombre = nombre;
		this.direccion = direccion;
		this.numeroDePlantas = numeroDePlantas;
		this.numeroDeSotanos = numeroDeSotanos;
		espacios = new HashSet<Espacio>();

	}

	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public Integer getNumeroPlantas() {
		return numeroDePlantas;
	}

	public Integer getNumeroSotanos() {
		return numeroDeSotanos;
	}

	public Set<Espacio> getEspacios() {
		return new HashSet<Espacio>(espacios);
	}

	public void checkPlanta(Integer numeroPlantas) {
		if (numeroPlantas < 1) {
			throw new ExcepcionCentroNoValido(
					"El centro debe tener al menos 1 planta.");
		}
	}

	public void checkSotano(Integer numeroSotanos) {
		if (numeroSotanos < 0) {
			throw new ExcepcionCentroNoValido(
					"El centro debe de tener 0 o más sótanos.");
		}
	}

	public void nuevoEspacio(Espacio e) {
		if (!(espacios.contains(e))) {
			espacios.add(e);
			if (e.getPlanta() < getNumeroSotanos() * -1
					|| e.getPlanta() > getNumeroPlantas() - 1) {
				throw new ExcepcionCentroOperacionNoPermitida(
						"Operación no permitida, el centro debe de tener al menos una planta y 0 o más sótanos");
			}
		}
	}

	public void eliminaEspacio(Espacio e) {
		if (espacios.contains(e))
			espacios.remove(e);
	}

	public Integer[] getConteosEspacios() {
		int numeroAulasTeoria = 0, numeroAulasLaboratorio = 0, numeroAulasSeminario = 0, numeroAulasExamen = 0, numeroAulasOtro = 0;
		for (Espacio e : espacios) {
			switch (e.getTipo()) {
			default:
				break;
			case TEORIA:
				numeroAulasTeoria++;
				break;

			case LABORATORIO:
				numeroAulasLaboratorio++;
				break;

			case SEMINARIO:
				numeroAulasSeminario++;
				break;

			case EXAMEN:
				numeroAulasExamen++;
				break;

			case OTRO:
				numeroAulasOtro++;
				break;
			}
		}
		Integer[] conteosEspacios = { numeroAulasTeoria,
				numeroAulasLaboratorio, numeroAulasSeminario,
				numeroAulasExamen, numeroAulasOtro };

		return conteosEspacios;
	}

	public Set<Despacho> getDespachos() {
		Set<Despacho> despachos = new HashSet<Despacho>();
		for (Espacio e : espacios) {
			if (e instanceof Despacho) {
				despachos.add((Despacho) e);
			}
		}
		return despachos;
	}

	public Set<Despacho> getDespachos(Departamento d) {
		Set<Despacho> despachos = new HashSet<Despacho>();
		for (Despacho des : this.getDespachos()) {
			for (Profesor p : des.getProfesores()) {
				int contador = 0;
				if (d.getProfesores().contains(p)) {
					contador = 1;
				}
				if (contador == 1) {
					despachos.add(des);
				}
			}
		}
		return despachos;
	}

	public Set<Profesor> getProfesores() {
		Set<Profesor> profesores = new HashSet<Profesor>();
		for (Despacho des : getDespachos()) {
			profesores.addAll(des.getProfesores());
		}
		return profesores;
	}

	public Set<Profesor> getProfesores(Departamento d) {
		Set<Profesor> profesores = new HashSet<Profesor>();
		for (Profesor prof : getProfesores()) {
			if (prof.getDepartamento().equals(d)) {
				profesores.add(prof);
			}

		}
		return profesores;
	}

	public Espacio getEspacioMayorCapacidad() {
		/*
		 * Espacio max = null;
		 * 
		 * for(Espacio e: getEspacios()) {
		 * 
		 * }
		 */
		int contador = 0;
		Espacio espacioMayor = null;
		for (Espacio e : espacios) {
			if (e.getCapacidad() > contador) {
				contador = e.getCapacidad();
				espacioMayor = e;
			}
		}

		return espacioMayor;
	}

//	 public SortedMap<Profesor, Despacho> getDespachosPorProfesor() {
//	 SortedMap<Profesor, Despacho> despachosPorProfesor = new
//	 TreeMap<Profesor, Despacho>();
//	 for (Despacho des : this.getDespachos()) {
//	 for(Profesor p : des.getProfesores()) {
//	 if(!(des.getProfesores().isEmpty())) {
//	 despachosPorProfesor.put(p, des);
//	
//	 }
//	 }
//	 }
//	 return despachosPorProfesor;
//	 }

	public SortedMap<Profesor, Despacho> getDespachosPorProfesor() {
		SortedMap<Profesor, Despacho> res = new TreeMap<Profesor, Despacho>();
		for (Despacho des : this.getDespachos()) {
			actualizaMapProfesor(des.getProfesores(), des, res);
		}
		return res;
	}

	public void actualizaMapProfesor(Set<Profesor> profesores, Despacho des,
			SortedMap<Profesor, Despacho> mapa) {
		for(Profesor p: profesores) {
		mapa.put(p, des);
		}
	}

	public SortedSet<Espacio> getEspaciosOrdenadosPorCapacidad() {
		Comparator<Espacio> cmp = Comparator.comparing(Espacio::getCapacidad)
				.reversed().thenComparing(Comparator.naturalOrder());
		SortedSet<Espacio> res = new TreeSet<Espacio>(cmp);
		res.addAll(espacios);
		return res;
	}

	public String toString() {
		return getNombre();

	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		boolean res = false;
		if (o instanceof Centro) {
			Centro c = (Centro) o;
			res = this.getNombre().equals(c.getNombre());
		}
		return res;
	}

	public int hashCode() {
		return getNombre().hashCode();
	}

	public int compareTo(Centro c) {
		int res;
		if (c == null) {
			throw new NullPointerException();
		}
		res = getNombre().compareTo(c.getNombre());
		return res;
	}

}