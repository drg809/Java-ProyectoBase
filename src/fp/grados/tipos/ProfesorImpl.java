package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionProfesorNoValido;
import fp.grados.excepciones.ExcepcionProfesorOperacionNoPermitida;

public class ProfesorImpl extends PersonaImpl implements Profesor {

	private Categoria categoria;
	private Departamento departamento;
	private List<Asignatura> asignaturas;
	private List<Double> creditos;
	private SortedSet<Tutoria> tutorias = new TreeSet<Tutoria>();
	public static Double dedicacionTotal;
	public static final Double MC = 24.0;

	public ProfesorImpl(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento, String email, Categoria categoria,
			Departamento departamento) {
		super(dni, nombre, apellidos, fechaNacimiento, email);
		this.categoria = categoria;
		asignaturas = new ArrayList<Asignatura>();
		creditos = new ArrayList<Double>();
		tutorias = new TreeSet<Tutoria>();
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
		return new ArrayList<Asignatura>(asignaturas);
	}

	public List<Double> getCreditos() {
		return new ArrayList<Double>(creditos);
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		super.setFechaNacimiento(fechaNacimiento);
		checkEdad(super.getEdad());
	}

	public void imparteAsignatura(Asignatura a, Double dedicacion) {
		if (asignaturas.contains(a)) {
			creditos.remove(asignaturas.indexOf(a));
			creditos.add(asignaturas.indexOf(a), dedicacion);

		} else {
			asignaturas.add(a);
			creditos.add(dedicacion);
		}
		checkCreditos();
		checkDedicacion(a, dedicacion);
		checkDepartamento(a.getDepartamento());
	}

	public Double dedicacionAsignatura(Asignatura a) {
		if (!asignaturas.contains(a)) {
			return 0.0;
		} else {
			Integer ind = asignaturas.indexOf(a);
			return creditos.get(ind);
		}
	}

	public void eliminaAsignatura(Asignatura a) {
		if (asignaturas.contains(a)) {
			creditos.remove(asignaturas.indexOf(a));
			asignaturas.remove(a);
			
		}
	}

	public void checkEdad(Integer edad) {
		if (getEdad() < 18) {
			throw new ExcepcionProfesorNoValido();
		}
	}

	public void checkDedicacion(Asignatura a, Double dedicacion) {
		if (dedicacion <= 0 || dedicacion > a.getCreditos()) {
			throw new ExcepcionProfesorOperacionNoPermitida();
		}
	}

	public void checkDepartamento(Departamento d) {
		if (!(getDepartamento().getNombre().equals(d.getNombre()))) {
			throw new ExcepcionProfesorOperacionNoPermitida();
		}
	}

	public Double getDedicacionTotal() {
		Double dedicacionTotal = 0.0;
		for (Double ind : creditos) {
			dedicacionTotal += ind;
		}

		return dedicacionTotal;
	}

	public String toString() {
		String res = super.toString() + "(" + getCategoria() + ")";
		return res;
	}
}
