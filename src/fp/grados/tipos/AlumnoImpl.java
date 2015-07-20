package fp.grados.tipos;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionAlumnoNoValido;
import fp.grados.excepciones.ExcepcionAlumnoOperacionNoPermitida;

public class AlumnoImpl extends PersonaImpl implements Alumno {

	private Set<Asignatura> asignaturas;	 
	private Expediente expediente;
	
	public AlumnoImpl(String a) {
		super(a);
		checkEmailUniversidad(super.getEmail());
		asignaturas = new HashSet<Asignatura>();
		expediente = new ExpedienteImpl();
	}

	public AlumnoImpl(String dni, String nombre, String apellidos,		
			LocalDate fechaNacimiento, String email) {					
																		

		super(dni, nombre, apellidos, fechaNacimiento, email);
		checkEmailUniversidad(email);
		asignaturas = new HashSet<Asignatura>();
		expediente = new ExpedienteImpl();
	}

	private void checkEmailUniversidad(String email) {
		if (email.isEmpty() || !email.endsWith("@alum.us.es")) {
			throw new ExcepcionAlumnoNoValido(
					"El email de un alumno debe terminar en @alum.us.es");
		}
	}

	public Set<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	public void matriculaAsignatura(Asignatura a) {
		if (estaMatriculadoEn(a)) {
			throw new ExcepcionAlumnoOperacionNoPermitida(
					"El alumno ya está matriculo en esta asignatura");
		}

		asignaturas.add(a);
	}

	public void eliminaAsignatura(Asignatura a) {
		if (!(estaMatriculadoEn(a))) {
			throw new ExcepcionAlumnoOperacionNoPermitida(
					"El alumno no está matriculado en esta asignatura");
		}
		asignaturas.remove(a);

	}

	public Boolean estaMatriculadoEn(Asignatura a) {
		return asignaturas.contains(a);
	}

	public Expediente getExpediente() {
		return expediente;
	}

	public void evaluaAlumno(Asignatura a, Integer curso,
			Convocatoria convocatoria, Double nota) {

		if (getAsignaturas().contains(a)) {
			Nota n = new NotaImpl(a, curso, convocatoria, nota);
			expediente.nuevaNota(n);

		} else {
			throw new ExcepcionAlumnoOperacionNoPermitida();
		}
	}

	public void evaluaAlumno(Asignatura a, Integer curso,
			Convocatoria convocatoria, Double nota, Boolean mencionHonor) {

		if (getAsignaturas().contains(a)) {
			Nota n = new NotaImpl(a, curso, convocatoria, nota, mencionHonor);
			expediente.nuevaNota(n);

		} else {
			throw new ExcepcionAlumnoOperacionNoPermitida();
		}

	}

	public Integer getCurso() {
		int curso = 0;
		for (Asignatura a : asignaturas) {
			if (a.getCurso() > curso) {
				curso = a.getCurso();
			}
		}
		return curso;
	}

	public void setEmail(String email) {
		checkEmailUniversidad(email);
		super.setEmail(email);
	}
	
	public SortedMap<Asignatura, Calificacion> getCalificacionPorAsignatura() {
		SortedMap<Asignatura, Calificacion> calificaciones = new TreeMap<Asignatura, Calificacion>();
		for (Nota n: expediente.getNotas()) {
			if (calificaciones.containsKey(n.getAsignatura())) {
				if(calificaciones.get(n.getAsignatura()).equals(Calificacion.SUSPENSO)) {
					calificaciones.replace(n.getAsignatura(), n.getCalificacion());
				}
					
				if(calificaciones.get(n.getAsignatura()).equals(Calificacion.APROBADO) && n.getCalificacion().equals(Calificacion.NOTABLE)|| n.getCalificacion().equals(Calificacion.SOBRESALIENTE)|| n.getCalificacion().equals(Calificacion.MATRICULA_DE_HONOR)) {
					calificaciones.replace(n.getAsignatura(), n.getCalificacion());
				}
					
				if(calificaciones.get(n.getAsignatura()).equals(Calificacion.NOTABLE) && n.getCalificacion().equals(Calificacion.SOBRESALIENTE)|| n.getCalificacion().equals(Calificacion.MATRICULA_DE_HONOR)) {
					calificaciones.replace(n.getAsignatura(), n.getCalificacion());
				}
					
				if(calificaciones.get(n.getAsignatura()).equals(Calificacion.SOBRESALIENTE) && n.getCalificacion().equals(Calificacion.MATRICULA_DE_HONOR)) {
					calificaciones.replace(n.getAsignatura(), n.getCalificacion());
				}
				
			} else {
				calificaciones.put(n.getAsignatura(), n.getCalificacion());
			}
		}
		
		return calificaciones;
 	}
	
	public SortedSet<Asignatura> getAsignaturasOrdenadasPorCurso() {
		Comparator<Asignatura> cmp = Comparator.comparing(Asignatura::getCurso).reversed().thenComparing(Comparator.naturalOrder());
		SortedSet<Asignatura> res = new TreeSet<Asignatura>(cmp);
		res.addAll(asignaturas);
		return res;
	}

	public String toString() {
		return "(" + getCurso() + "º)" + super.toString();
	}
}