package fp.grados.utiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import fp.grados.tipos.Asignatura;
import fp.grados.tipos.Beca;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.tipos.TipoEspacio;

public class RepasoEntrega3 {
	
	public static <T> List<T> leeFichero(String nombreFichero,
			Function<String, T> funcion_deString_aT) {
		List<T> res = null;
		try {
			res = Files.lines(Paths.get(nombreFichero))
					.map(funcion_deString_aT).collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Error en la lectura del fichero: "
					+ nombreFichero);
		}

		return res;

	}
	
	//EJERCICIO 1
	public static SortedMap<Beca, Integer> duracionesBecas(String nombreFichero, Double cuantia) {
		Map<Beca, Integer> m = Grados.createBecas(nombreFichero).stream().filter(x -> x.getCuantiaTotal().compareTo(cuantia)>0)
				.collect(Collectors.toMap(x -> x, Beca::getDuracion, (duracion1, duracion2) -> duracion1, TreeMap::new));
		
		return new TreeMap<Beca, Integer>(m);
	}
	
	//Alternativa
	//public static SortedMap<Beca, Integer> duracionesBecas(String nombreFichero, Double cuantia) {
	//	return Grados.createBecas(nombreFichero).stream().filter(x -> x.getCuantiaTotal().compareTo(cuantia)>0)
	//	.collect(Collectors.toMap(x -> x, Beca::getDuracion, (duracion1, duracion2) -> duracion1, TreeMap::new));
	//}
	
	//EJERCICIO 2
	public static Integer numeroBecasDuracion(String nombreFichero, Integer meses) {

		return (int) Grados.createBecas(nombreFichero).stream().filter(x -> x.getDuracion().compareTo(meses)>0).count();
	}
	
	//EJERCICIO 3
	public static Integer capacidadTotalEspaciosTipo(String nombreFichero, TipoEspacio tipo) {
		
		return Grados.createEspacios(nombreFichero).stream().filter(x -> x.getTipo().equals(tipo)).mapToInt(Espacio::getCapacidad).sum();
	}
	
	//EJERCICIO 4
	public static SortedSet<Asignatura> seleccionaAnuales(String nombreFichero) {
		Comparator<Asignatura> cmp = Comparator.comparing(Asignatura::getCurso).thenComparing(Comparator.naturalOrder());
		SortedSet<Asignatura> res = new TreeSet<Asignatura>(cmp);
		
		List<Asignatura> asignaturas = Grados.createAsignaturas(nombreFichero).stream().filter(x -> x.getTipo().equals(TipoAsignatura.ANUAL)).collect(Collectors.toList());
		
		res.addAll(asignaturas);
		return res;
	}
}
