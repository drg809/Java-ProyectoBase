package fp.grados.tipos;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Stream;

import com.google.common.collect.Maps;

public class AlumnoImpl2 extends AlumnoImpl {

	public AlumnoImpl2(String s) {
		super(s);
	}

	public AlumnoImpl2(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento, String email) {

		super(dni, nombre, apellidos, fechaNacimiento, email);

	}

	public Integer getCurso() {
		Comparator<Asignatura> cmp = Comparator.comparing(Asignatura::getCurso);
		Optional<Asignatura> res = getAsignaturas().stream().max(cmp);
		if (res.isPresent()) {
			return res.get().getCurso();
		} else {
			return 0;
		}
	}

	public SortedMap<Asignatura, Calificacion> getCalificacionPorAsignatura() {
		Stream<Asignatura> sn = getExpediente().getNotas().stream()
				.map(n -> n.getAsignatura());
		SortedMap<Asignatura, Calificacion> mapa = Maps.newTreeMap();
		sn.forEach(a -> mapa.put(a, calificacionMax(a)));
		
		return new TreeMap<Asignatura, Calificacion>(mapa);
	}

	private Calificacion calificacionMax(Asignatura a) {
		return getExpediente().getNotas().stream()
				.filter(n -> n.getAsignatura().equals(a))
				.max(Comparator.comparing(Nota::getValor)).get()
				.getCalificacion();
	}
}
