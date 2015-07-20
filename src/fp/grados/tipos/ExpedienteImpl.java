package fp.grados.tipos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import fp.grados.excepciones.ExcepcionExpedienteOperacionNoPermitida;

public class ExpedienteImpl implements Expediente {

	private List<Nota> notas;

	public ExpedienteImpl() {
		notas = new ArrayList<Nota>();
	}

	public void checkNota(Nota n) {
		int notaCuenta = 0;
		for (Nota ind : notas) {
			if (ind.getAsignatura().equals(n.getAsignatura())
					&& ind.getCursoAcademico().equals(n.getCursoAcademico())) {
				notaCuenta = notaCuenta + 1;
				if (notaCuenta > 1) {
					throw new ExcepcionExpedienteOperacionNoPermitida();
				}
			}
		}
	}

	public List<Nota> getNotas() {
		return new ArrayList<Nota>(notas);
	}

	public void nuevaNota(Nota n) {
		checkNota(n);
		if (notas.contains(n)) {
			notas.remove(n);
			notas.add(n);

		} else {
			notas.add(n);
		}
	}

	public Double getNotaMedia() {

		double notaMedia = 0.0;
		double sumaTotal = 0.0;
		int cont = 0;
		for (Nota n : notas) {
			if (n.getValor() >= 5) {
				sumaTotal = sumaTotal + n.getValor();
				cont = cont + 1;
			}
		}
		if (sumaTotal != 0.0) {
			notaMedia = sumaTotal / cont;
		}
		return notaMedia;
	}

	public List<Nota> getNotasOrdenadasPorAsignatura() {
		Comparator<Nota> cmp = Comparator.comparing(Nota::getAsignatura).thenComparing(Comparator.naturalOrder());
		List<Nota> res = new ArrayList<Nota>();
		res.addAll(getNotas());
		Collections.sort(res, cmp);
		return res;
	}

	public Nota getMejorNota() {
		/*Comparator<Nota> cmp = Comparator.comparing(Nota::getMencionHonor)
				.thenComparing(Nota::getValor).reversed()
				.thenComparing(Nota::getConvocatoria)
				.thenComparing(Nota::getCursoAcademico);*/
		
		Comparator<Nota> cmpM = Comparator.comparing(Nota::getMencionHonor);
		Comparator<Nota> cmpV = Comparator.comparing(Nota::getValor).reversed();
		Comparator<Nota> cmpC = Comparator.comparing(Nota::getConvocatoria);
		Comparator<Nota> cmpCA = Comparator.comparing(Nota::getCursoAcademico);
		
		List<Nota> res = new ArrayList<Nota>();
		res.addAll(getNotas());
		Collections.sort(res, cmpM.thenComparing(cmpM).thenComparing(cmpV).thenComparing(cmpC).thenComparing(cmpCA));
		if (res.isEmpty()) {
			throw new NoSuchElementException();
		}
		return res.get(0);
	}

	public String toString() {

		return notas.toString();
	}

	public int hashCode() {
		return notas.hashCode();
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		boolean res = false;
		if (o instanceof Expediente) {
			Expediente e = (Expediente) o;
			res = this.getNotas().equals(e.getNotas());

		}
		return res;
	}
}
