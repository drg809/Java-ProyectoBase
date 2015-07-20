package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionProfesorNoValido;
import fp.grados.excepciones.ExcepcionProfesorOperacionNoPermitida;

public class ProfesorImpl2 extends PersonaImpl implements Profesor {

	private Categoria categoria;
	private Departamento departamento;
	private SortedSet<Tutoria> tutorias = new TreeSet<Tutoria>();
	private Map<Asignatura, Double> mapaAsignaturaCreditos;
	public static Double dedicacionTotal;
	public static final Double MC = 24.0;

	public ProfesorImpl2(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento, String email, Categoria categoria,
			Departamento departamento) {
		super(dni, nombre, apellidos, fechaNacimiento, email);
		this.categoria = categoria;
		tutorias = new TreeSet<Tutoria>();

		mapaAsignaturaCreditos = new HashMap<Asignatura, Double>();
		setDepartamento(departamento);
		checkEdad(getEdad());
	}

	public void checkCreditos() {
		if (getDedicacionTotal() > MC) {
			throw new ExcepcionProfesorOperacionNoPermitida();
		}
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public SortedSet<Tutoria> getTutorias() {
		return new TreeSet<Tutoria>(tutorias);
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;

	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento d) {
		if (d != this.departamento) {
			Departamento antiguoD = this.departamento;
			this.departamento = d;

			if (antiguoD != null) {
				antiguoD.eliminaProfesor(this);
			}

			if (d != null) {
				d.nuevoProfesor(this);
			}
		}
	}

	public void nuevaTutoria(LocalTime horaComienzo, Integer duracion,
			DayOfWeek dia) {
		Tutoria t = new TutoriaImpl(dia, horaComienzo, duracion);
		tutorias.add(t);
	}

	public void borraTutoria(LocalTime horaComienzo, DayOfWeek dia) {
		Tutoria t = new TutoriaImpl(dia, horaComienzo, 30);

		if (tutorias.contains(t)) {
			tutorias.remove(t);
		}
	}

	public void borraTutorias() {
		tutorias.clear();

	}

	public List<Asignatura> getAsignaturas() {
		List<Asignatura> asignatura = new ArrayList<Asignatura>();
		for (Asignatura a : mapaAsignaturaCreditos.keySet()) {
			asignatura.add(a);
		}
		return asignatura;
	}

	public List<Double> getCreditos() {
		List<Double> creditos = new ArrayList<Double>();
		for (Double c : mapaAsignaturaCreditos.values()) {
			creditos.add(c);
		}
		return creditos;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		super.setFechaNacimiento(fechaNacimiento);
		checkEdad(super.getEdad());
	}

	public void imparteAsignatura(Asignatura a, Double dedicacion) {
		mapaAsignaturaCreditos.put(a, dedicacion);

		checkDedicacionTotal(a, dedicacion);
		checkDedicacion(a, dedicacion);
		if (a.getDepartamento() != null) {
			checkDepartamento(a.getDepartamento());
		}
	}

	public Double dedicacionAsignatura(Asignatura a) {
		if (!(mapaAsignaturaCreditos.containsKey(a))) {
			return 0.0;
		} else {
			return mapaAsignaturaCreditos.get(a);
		}
	}

	public void eliminaAsignatura(Asignatura a) {
		if (mapaAsignaturaCreditos.containsKey(a)) {
			mapaAsignaturaCreditos.remove(a);
		}
	}

	public void checkEdad(Integer edad) {
		if (getEdad() < 18) {
			throw new ExcepcionProfesorNoValido();
		}
	}

	public void checkDedicacion(Asignatura a, Double dedicacion) {
		if (dedicacion > a.getCreditos() || dedicacion <= 0) {
			throw new ExcepcionProfesorOperacionNoPermitida();
		}
	}

	public void checkDedicacionTotal(Asignatura asig, Double dedicacion) {
		Double dedicacion0 = dedicacionAsignatura(asig);
		Double dedicacionTotal = getDedicacionTotal();
		dedicacionTotal = dedicacionTotal - dedicacion0 + dedicacion;
		if (dedicacionTotal > MC) {
			throw new ExcepcionProfesorOperacionNoPermitida();
		}
	}

	public void checkDepartamento(Departamento d) {
		if (!(d.equals(departamento))) {
			throw new ExcepcionProfesorOperacionNoPermitida();
		}
	}

	public Double getDedicacionTotal() {
		dedicacionTotal = 0.0;
		for (Double c : mapaAsignaturaCreditos.values()) {
			dedicacionTotal += c;
		}

		return dedicacionTotal;
	}

	public String toString() {
		String res = super.toString() + "(" + getCategoria() + ")";
		return res;
	}
}
