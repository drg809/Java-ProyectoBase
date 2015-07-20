package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionNotaNoValida;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.Nota;
import fp.grados.tipos.Convocatoria;
import fp.grados.tipos.Calificacion;

public class NotaImpl implements Nota {

	private Asignatura asignatura;
	private Integer cursoAcademico;
	private Convocatoria convocatoria;
	private Double valor;
	private Boolean mencionHonor;
	public Calificacion calificacion;

	public NotaImpl(String s) {
		String[] n = s.split(";");
		if (n.length != 5) {
			throw new IllegalArgumentException(
					"El tamaño del constructor es erroneo");
		}
		asignatura = new AsignaturaImpl(n[0].trim());
		cursoAcademico = new Integer(n[1].trim());
		convocatoria = Convocatoria.valueOf(n[2].trim());
		checkValor(new Double(n[3].trim()));
		valor = new Double(n[3].trim());
		mencionHonor = new Boolean(n[4].trim());
		checkMencionHonor(valor);
	}

	public NotaImpl(Asignatura asignatura, Integer cursoAcademico,
			Convocatoria convocatoria, Double valor, Boolean mencionHonor) {

		this.asignatura = asignatura;
		this.cursoAcademico = cursoAcademico;
		this.convocatoria = convocatoria;
		this.valor = valor;
		this.mencionHonor = mencionHonor;
		checkValor(valor);
		checkMencionHonor(valor);
	}

	public NotaImpl(Asignatura asignatura, Integer cursoAcademico,
			Convocatoria convocatoria, Double valor) {

		this.asignatura = asignatura;
		this.cursoAcademico = cursoAcademico;
		this.convocatoria = convocatoria;
		this.valor = valor;
		mencionHonor = false;
		checkValor(valor);
		checkMencionHonor(valor);
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public Convocatoria getConvocatoria() {
		return convocatoria;
	}

	public Integer getCursoAcademico() {
		return cursoAcademico;
	}

	public Double getValor() {
		return valor;
	}

	public Calificacion getCalificacion() {
		if (getValor() >= 0 && getValor() < 5) {
			calificacion = Calificacion.SUSPENSO;
		} else if (getValor() >= 5 && getValor() < 7) {
			calificacion = Calificacion.APROBADO;
		} else if (getValor() >= 7 && getValor() < 9) {
			calificacion = Calificacion.NOTABLE;
		} else if (getValor() >= 9 && mencionHonor == false) {
			calificacion = Calificacion.SOBRESALIENTE;
		} else if (getValor() >= 9 && mencionHonor == true) {
			calificacion = Calificacion.MATRICULA_DE_HONOR;
		}

		return calificacion;
	}

	public Boolean getMencionHonor() {

		if (getValor() > 9) {
			mencionHonor = true;
		}
		return mencionHonor;

	}

	private void checkValor(Double valor) {
		if (valor < 0 || valor > 10) {
			throw new ExcepcionNotaNoValida(
					"La nota debe de ser superior a 0 e inferior a 10");
		}
	}

	private void checkMencionHonor(Double valor) {
		if (valor < 9 && mencionHonor == true) {
			throw new ExcepcionNotaNoValida(
					"La nota debe de ser superior a 9 para contar con mencion de honor");
		}
	}

	public String toString() {
		int cursoS = getCursoAcademico() + 1;
		return getAsignatura() + ", " + getCursoAcademico() + "-"
				+ String.valueOf(cursoS).charAt(2)
				+ String.valueOf(cursoS).charAt(3) + ", " + getConvocatoria()
				+ ", " + getValor() + ", " + getCalificacion();
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		boolean res = false;
		if (o instanceof Nota) {
			Nota n = (Nota) o;
			res = this.getCursoAcademico().equals(n.getCursoAcademico())
					&& this.getAsignatura().equals(n.getAsignatura())
					&& this.getConvocatoria().equals(n.getConvocatoria());
		}
		return res;
	}

	public int hashCode() {
		return getCursoAcademico().hashCode() * 31 + getAsignatura().hashCode()
				* 31 + getConvocatoria().hashCode();
	}

	public int compareTo(Nota n) {
		int res;
		if (n == null) {
			throw new NullPointerException();
		}
		res = getCursoAcademico().compareTo(n.getCursoAcademico());
		if (res == 0) {
			res = getAsignatura().compareTo(n.getAsignatura());
			if (res == 0) {
				res = getConvocatoria().compareTo(n.getConvocatoria());
			}
		}
		return res;
	}

}