package fp.grados.tipos;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionGradoNoValido;

public class GradoImpl implements Grado {
	private String nombre;
	private Double numeroMinimoCreditosOptativas;
	private Set<Asignatura> asignaturasObligatorias = new HashSet<Asignatura>();
	private Set<Asignatura> asignaturasOptativas = new HashSet<Asignatura>();


	public GradoImpl(String nombre, Set<Asignatura> asignaturasObligatorias,
			Set<Asignatura> asignaturasOptativas,
			Double numeroMinimoCreditosOptativas) {
		this.nombre = nombre;
		this.asignaturasObligatorias = asignaturasObligatorias;
		this.asignaturasOptativas = asignaturasOptativas;
		this.numeroMinimoCreditosOptativas = numeroMinimoCreditosOptativas;
		checkCreditos(asignaturasOptativas);
		checkCreditosMinimos(numeroMinimoCreditosOptativas);
	}

	public String getNombre() {
		return nombre;
	}

	public Double getNumeroMinimoCreditosOptativas() {
		return numeroMinimoCreditosOptativas;
	}

	public Set<Asignatura> getAsignaturasObligatorias() {
		return new HashSet<Asignatura>(asignaturasObligatorias);
	}

	public Set<Asignatura> getAsignaturasOptativas() {
		return new HashSet<Asignatura>(asignaturasOptativas);
	}

	public Double getNumeroTotalCreditos() {
		Double numeroTotalCreditos;
		Double contador = 0.0;
		for (Asignatura a : asignaturasObligatorias) {
			contador = contador + a.getCreditos();
		}
		numeroTotalCreditos = contador
				+ this.getNumeroMinimoCreditosOptativas();

		return numeroTotalCreditos;
	}

	public Set<Departamento> getDepartamentos() {
		Set<Departamento> departamentos = new HashSet<Departamento>();
		for (Asignatura a : asignaturasObligatorias) {
			departamentos.add(a.getDepartamento());
		}
		for (Asignatura ao : asignaturasOptativas) {

			departamentos.add(ao.getDepartamento());
		}

		return departamentos;
	}

	public Set<Profesor> getProfesores() {
		Set<Profesor> profesores = new HashSet<Profesor>();
		for (Departamento d : this.getDepartamentos()) {
			profesores.addAll(d.getProfesores());
		}
		return profesores;
	}

	public Set<Asignatura> getAsignaturas(Integer curso) {
		Set<Asignatura> asignaturas = new HashSet<Asignatura>();
		for (Asignatura a : asignaturasOptativas) {
			if (a.getCurso().equals(curso)) {
				asignaturas.add(a);
			}
		}
		for (Asignatura a : asignaturasObligatorias) {
			if (a.getCurso().equals(curso)) {
				asignaturas.add(a);
			}
		}
		return asignaturas;
	}

	public Asignatura getAsignatura(String codigo) {
		Asignatura asig = null;
		for (Asignatura a : asignaturasObligatorias) {
			if (a.getCodigo().equals(codigo)) {
				asig = a;
			}
		}
		for (Asignatura ao : asignaturasOptativas) {
			if (ao.getCodigo().equals(codigo)) {
				asig = ao;
			}

		}
		return asig;
	}
	public SortedMap<Asignatura, Double> getCreditosPorAsignatura() {
		SortedMap<Asignatura, Double> creditosPorAsignatura = new TreeMap<Asignatura, Double>();
		for (Asignatura a : asignaturasOptativas) {
			if (!(a.getCreditos().doubleValue() == 0.0)) {
				creditosPorAsignatura.put(a, a.getCreditos());
			}
		}
		
		for (Asignatura a : asignaturasObligatorias) {
			if (!(a.getCreditos().doubleValue() == 0.0)) {
				creditosPorAsignatura.put(a, a.getCreditos());
			}
		}
		
		return creditosPorAsignatura;
		
	}

	
	public SortedSet<Departamento> getDepartamentosOrdenadosPorAsignaturas() {
		Comparator<Departamento> cmp = Comparator.comparing(d -> d.getAsignaturas().size());
		
		SortedSet<Departamento> res = new TreeSet<Departamento>(cmp.reversed().thenComparing(Comparator.naturalOrder()));
		res.addAll(getDepartamentos());
		return res;
	}

	public void checkCreditos(Set<Asignatura> asignaturasOptativas) {
		Boolean Primero = true;
		Double cred = 0.0;
		for(Asignatura a : asignaturasOptativas) {
			if(Primero.equals(true)) {
				cred = a.getCreditos();
				Primero = false;
			} else {
				if(!(cred.equals(a.getCreditos()))) {
					throw new ExcepcionGradoNoValido();
				}
			}
			
		}
	}

	public void checkCreditosMinimos(Double numeroMinimoCreditosOptativas) {
		Double creditosMinimos = 0.0;
		for (Asignatura a : asignaturasOptativas) {
			creditosMinimos += a.getCreditos();
		}
		if (numeroMinimoCreditosOptativas < 0.0
				|| numeroMinimoCreditosOptativas > creditosMinimos) {
			throw new ExcepcionGradoNoValido();
		}

	}

	public String toString() {
		return getNombre();

	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		boolean res = false;
		if (o instanceof Grado) {
			Grado g = (Grado) o;
			res = this.getNombre().equals(g.getNombre());
		}
		return res;
	}

	public int hashCode() {
		return getNombre().hashCode();
	}

	public int compareTo(Grado g) {
		int res;
		if (g == null) {
			throw new NullPointerException();
		}
		res = getNombre().compareTo(g.getNombre());

		return res;

	}

}
