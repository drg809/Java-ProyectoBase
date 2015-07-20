package fp.grados.utiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import fp.grados.tipos.Alumno;
import fp.grados.tipos.AlumnoImpl;
import fp.grados.tipos.AlumnoImpl2;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Beca;
import fp.grados.tipos.BecaImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Centro;
import fp.grados.tipos.CentroImpl;
import fp.grados.tipos.CentroImpl2;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.DepartamentoImpl2;
import fp.grados.tipos.Despacho;
import fp.grados.tipos.DespachoImpl;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.Grado;
import fp.grados.tipos.GradoImpl;
import fp.grados.tipos.GradoImpl2;
import fp.grados.tipos.Nota;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.ProfesorImpl2;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.tipos.TipoBeca;
import fp.grados.tipos.TipoEspacio;
import fp.grados.tipos.Tutoria;

public class Grados {
	private static Set<Departamento> departamentos = new HashSet<Departamento>();
	private static Set<Beca> becas = new HashSet<Beca>();
	private static Integer[] numBecasPorTipo = { 0, 0, 0 };
	private static Map<String, Asignatura> codigoAsignatura = new HashMap<String, Asignatura>();
	private static Boolean usarMapProfesores = true;
	private static Boolean usarJava8 = true;
	private static Set<Profesor> profesores = new HashSet<Profesor>();
	private static Set<Alumno> alumnos = new HashSet<Alumno>();
	private static Set<Centro> centros = new HashSet<Centro>();
	private static Set<Integer> plantas = new HashSet<Integer>();
	private static Set<Integer> sotanos = new HashSet<Integer>();
	private static SortedSet<Espacio> espacios = new TreeSet<Espacio>();
	private static Set<Grado> grados = new HashSet<Grado>();

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

	public static void setUsarJava8(Boolean usarMap) {
		usarJava8 = usarMap;
	}
	
	// Alumno
	
	public static Alumno createAlumno(String dni, String nombre,
			String apellidos, LocalDate fechaNacimiento, String email) {
		Alumno a = null;
		if (usarJava8) {
			a = new AlumnoImpl2(dni, nombre, apellidos, fechaNacimiento, email);
		} else {
			a = new AlumnoImpl(dni, nombre, apellidos, fechaNacimiento, email);
		}
		alumnos.add(a);
		return a;
	}

	public static Alumno createAlumno(Alumno a) {
		Alumno alumno = createAlumno(a.getDNI(), a.getNombre(), a.getApellidos(),
				a.getFechaNacimiento(), a.getEmail());
		for(Nota n: a.getExpediente().getNotas()){
			alumno.getExpediente().nuevaNota(n);
		}
		for(Asignatura a1 :a.getAsignaturas()){
		alumno.matriculaAsignatura(a1);
		}
		return alumno;
		
	}

	public static Alumno createAlumno(String s) {
		Alumno a = new AlumnoImpl(s);
		alumnos.add(a);
		return a;
	}

	public static List<Alumno> createAlumnos(String nombreFichero) {
		return leeFichero(nombreFichero, s -> createAlumno(s));
	}

	public static Set<Alumno> getAlumnosCreados() {
		return new HashSet<Alumno>(alumnos);
	}

	public static Integer getNumAlumnosCreados() {
		return getAlumnosCreados().size();
	}

	// Asignatura
	public static Asignatura createAsignatura(String nombre, String codigo,
			Double creditos, TipoAsignatura tipo, Integer curso,
			Departamento dpto) {
		Asignatura a = new AsignaturaImpl(nombre, codigo, creditos, tipo,
				curso, dpto);
		codigoAsignatura.put(codigo, a);
		return a;
	}

	public static Asignatura createAsignatura(String s) {
		Asignatura a = new AsignaturaImpl(s);
		codigoAsignatura.put(a.getCodigo(), a);
		return a;
	}

	public static List<Asignatura> createAsignaturas(String nombreFichero) {
		return leeFichero(nombreFichero, s -> createAsignatura(s));
	}

	public static Set<Asignatura> getAsignaturasCreadas() {
		return new HashSet<Asignatura>(codigoAsignatura.values());
	}

	public static Integer getNumAsignaturasCreadas() {
		return getAsignaturasCreadas().size();
	}

	public static Asignatura getAsignaturaCreada(String codigo) {
		return codigoAsignatura.get(codigo);
	}

	public static Set<String> getCodigosAsignaturasCreadas() {
		return new HashSet<String>(codigoAsignatura.keySet());
	}

	// Beca
	public static Beca createBeca(String codigo, Double cuantiaTotal,
			Integer duracion, TipoBeca tipo) {
		Beca b = new BecaImpl(codigo, cuantiaTotal, duracion, tipo);
		becas.add(b);
		actualizaNumBecasPorTipo(b);
		return b;
	}

	public static Beca createBeca(String codigo, TipoBeca tipo) {
		Beca b = new BecaImpl(codigo, tipo);
		becas.add(b);
		actualizaNumBecasPorTipo(b);
		return b;
	}

	public static Beca createBeca(Beca beca) {
		return createBeca(beca.getCodigo(), beca.getCuantiaTotal(),
				beca.getDuracion(), beca.getTipo());
	}

	public static Beca createBeca(String s) {
		Beca b = new BecaImpl(s);
		becas.add(b);
		actualizaNumBecasPorTipo(b);
		return b;
	}

	public static List<Beca> createBecas(String nombreFichero) {
		return leeFichero(nombreFichero, s -> createBeca(s));
	}

	public static Set<Beca> getBecasCreadas() {
		return new HashSet<Beca>(becas);
	}

	public static Integer getNumBecasCreadas() {
		return getBecasCreadas().size();
	}

	private static void actualizaNumBecasPorTipo(Beca b) {
		switch (b.getTipo()) {
		case ORDINARIA:
			numBecasPorTipo[0]++;
			break;
		case MOVILIDAD:
			numBecasPorTipo[1]++;
			break;
		case EMPRESA:
			numBecasPorTipo[2]++;
			break;
		}
	}

	public static Integer getNumBecasTipo(TipoBeca tipo) {
		Integer res = 0;
		switch (tipo) {
		case ORDINARIA:
			res = numBecasPorTipo[0];
			break;
		case MOVILIDAD:
			res = numBecasPorTipo[1];
			break;
		case EMPRESA:
			res = numBecasPorTipo[2];
			break;
		}
		return res;
	}

	// Centro

//	public static Centro createCentro(String nombre, String direccion,
//			Integer numeroDePlantas, Integer numeroDeSotanos) {
//		Centro c = null;
//		if (usarJava8) {
//			c = new CentroImpl2(nombre, direccion, numeroDePlantas,
//					numeroDeSotanos);
//		} else {
//			c = new CentroImpl(nombre, direccion, numeroDePlantas,
//					numeroDeSotanos);
//		}
//			centros.add(c);
//			plantas.add(c.getNumeroPlantas());
//			return c;
//	}
//
//	public static Centro createCentro(Centro centro) {
//		Centro c = createCentro(centro.getNombre(), centro.getDireccion(),
//				centro.getNumeroPlantas(), centro.getNumeroSotanos());
//		for(Espacio e: centro.getEspacios()) {
//			c.nuevoEspacio(e);
//		}
//		centros.add(c);
//		plantas.add(c.getNumeroPlantas());
//		return c;
//	}
//
//	public static Set<Centro> getCentrosCreados() {
//		return new HashSet<Centro>(centros);
//	}
//
//	public static Integer getNumCentrosCreados() {
//		return getCentrosCreados().size();
//	}
//
//	public static Integer getMaxPlantas() {
//		Boolean esPrimero = true;
//		Integer mayor = 0;
//		for (Integer p : plantas) {
//			if (esPrimero) {
//				mayor = p;
//				esPrimero = false;
//			} else {
//				if (p > mayor) {
//					mayor = p;
//				}
//			}
//		}
//		return mayor;
//	}
//
//	public static Integer getMaxSotanos() {
//		Boolean esPrimero = true;
//		Integer mayor = 0;
//		for (Integer s : sotanos) {
//			if (esPrimero) {
//				mayor = s;
//				esPrimero = false;
//			} else {
//				if (s > mayor) {
//					mayor = s;
//				}
//			}
//		}
//		return mayor;
//	}
//
//	public static Double getMediaPlantas() {
//		Double suma = 0.0;
//		Double res = 0.;
//		for (Centro c : centros) {
//			suma += c.getNumeroPlantas();
//		}
//		if(centros.size()!=0){
//		res = (double) (suma / centros.size());
//		}
//		return res;
//
//	}
//
//	public static Double getMediaSotanos() {
//		Double suma = 0.0;
//		Double res = 0.;
//		for (Centro c : centros) {
//			res += c.getNumeroSotanos();
//		}
//		if(centros.size()!=0){
//		suma = (double) (res / centros.size());
//		}
//		return suma;
//
//	}

	public static Centro createCentro(String nombre, String direccion,
			Integer numeroDePlantas, Integer numeroDeSotanos) {
		Centro c = null;
		if (usarJava8) {
			c = new CentroImpl2(nombre, direccion, numeroDePlantas,
					numeroDeSotanos);
		} else {
			c = new CentroImpl(nombre, direccion, numeroDePlantas,
					numeroDeSotanos);
		}
		centros.add(c);
		plantas.add(c.getNumeroPlantas());
		return c;
	}

	public static Centro createCentro(Centro centro) {

		Centro c = createCentro(centro.getNombre(), centro.getDireccion(),
				centro.getNumeroPlantas(), centro.getNumeroSotanos());
		for(Espacio e: centro.getEspacios()){
			c.nuevoEspacio(e);
			}
		centros.add(c);
		plantas.add(c.getNumeroPlantas());
		
		return c;
	}

	public static Set<Centro> getCentrosCreados() {
		return new HashSet<Centro>(centros);
	}

	public static Integer getNumCentrosCreados() {
		return getCentrosCreados().size();
	}

	public static Integer getMaxPlantas() {
		Boolean esPrimero = true;
		Integer mayor = 0;
		for (Centro c: centros) {
			if (esPrimero) {
				mayor = c.getNumeroPlantas();
				esPrimero = false;
			} else {
				if (c.getNumeroPlantas() > mayor) {
					mayor = c.getNumeroPlantas();
				}
			}
		}
		return mayor;
	}

	public static Integer getMaxSotanos() {
		Boolean esPrimero = true;
		Integer mayor = 0;
		for (Centro c: centros) {
			if (esPrimero) {
				mayor = c.getNumeroSotanos();
				esPrimero = false;
			} else {
				if (c.getNumeroSotanos() > mayor) {
					mayor = c.getNumeroSotanos();
				}
			}
		}
		return mayor;
	}

	public static Double getMediaPlantas() {
		Double suma = 0.;
		Double res = 0.;
		for (Centro c : centros) {
			suma += c.getNumeroPlantas();
		}
		if(centros.size()!=0){
		res = new Double(suma / centros.size());
		}
		return res;

	}

	public static Double getMediaSotanos() {
		 Double media = 0.0;
		 Double numSotanosTotal = 0.;
		 for (Centro c : centros)
		 numSotanosTotal += c.getNumeroSotanos();
		 if (centros.size() != 0) 
		 media = new Double(numSotanosTotal / centros.size());
		 return media;
		
	}
	
	// Departamento
	public static Departamento createDepartamento(String nombre) {
		Departamento d = null;
		if(usarJava8) {
			 d = new DepartamentoImpl2(nombre);
		} else {
			 d = new DepartamentoImpl(nombre);
		}
		departamentos.add(d);
		return d;
	}

	public static Set<Departamento> getDepartamentosCreados() {
		return new HashSet<Departamento>(departamentos);
	}

	public static Integer getNumDepartamentosCreados() {
		return getDepartamentosCreados().size();
	}

	// Despacho
	public static Despacho createDespacho(String nombre, Integer capacidad,
			Integer planta) {
		Despacho d = new DespachoImpl(nombre, capacidad, planta);
		espacios.add(d);
		return d;
	}

	public static Despacho createDespacho(Despacho despacho) {
		Despacho d = createDespacho(despacho.getNombre(),
				despacho.getCapacidad(), despacho.getPlanta());
		espacios.add(d);
		return d;
	}

	public static Despacho createDespacho(String despacho) {
		Despacho d = new DespachoImpl(despacho);
		espacios.add(d);
		return d;
	}

	public static List<Despacho> createDespachos(String nombreFichero) {
		return leeFichero(nombreFichero, s -> createDespacho(s));
	}

	// Espacio
	public static Espacio createEspacio(TipoEspacio tipo, String nombre,
			Integer capacidad, Integer planta) {
		Espacio e = new EspacioImpl(tipo, nombre, capacidad, planta);
		espacios.add(e);
		return e;
	}

	public static Espacio createEspacio(Espacio espacio) {
		Espacio e = createEspacio(espacio.getTipo(), espacio.getNombre(),
				espacio.getCapacidad(), espacio.getPlanta());
		espacios.add(e);
		return e;
	}

	public static Espacio createEspacio(String espacio) {
		Espacio e = new EspacioImpl(espacio);
		espacios.add(e);
		return e;
	}

	public static List<Espacio> createEspacios(String nombreFichero) {
		return leeFichero(nombreFichero, s -> createEspacio(s));
	}

	public static SortedSet<Espacio> getEspaciosCreados() {
		return new TreeSet<Espacio>(espacios);
	}

	public static Integer getNumEspaciosCreados() {
		return getEspaciosCreados().size();
	}

	public static Integer getPlantaMayorEspacio() {
		Boolean esPrimero = true;
		Integer mayor = 0;
		if (espacios.size() != 0) {
			for (Espacio e : espacios) {

				if (esPrimero) {
					mayor = e.getPlanta();
					esPrimero = false;
				} else {
					if (e.getPlanta() > mayor) {
						mayor = e.getPlanta();
					}
				}
			}
		} else {
			mayor = null;
		}
		return mayor;
	}

	public static Integer getPlantaMenorEspacio() {
		Boolean esPrimero = true;
		Integer menor = 0;
		if (espacios.size() != 0) {
			for (Espacio e : espacios) {

				if (esPrimero) {
					menor = e.getPlanta();
					esPrimero = false;
				} else {
					if (e.getPlanta() < menor) {
						menor = e.getPlanta();
					}
				}
			}
		} else {
			menor = null;
		}

		return menor;
	}

//	// Grado
//	public static Grado createGrado(String nombre,
//			Set<Asignatura> asignaturasObligatorias,
//			Set<Asignatura> asignaturasOptativas,
//			Double numeroMinimoCreditosOptativas) {
//		Grado g = null;
//		
//		if (usarJava8) {
//			g = new GradoImpl2(nombre, asignaturasObligatorias,
//					asignaturasOptativas, numeroMinimoCreditosOptativas);
//			
//		} else {
//			g = new GradoImpl(nombre, asignaturasObligatorias,
//					asignaturasOptativas, numeroMinimoCreditosOptativas);
//		}
//		grados.add(g);
//		return g;
//	}
//
//	public static Set<Grado> getGradosCreados() {
//		return new HashSet<Grado>(grados);
//	}
//
//	public static Integer getNumGradosCreados() {
//		return getGradosCreados().size();
//	}
//
//	public static Double getMediaAsignaturasGrados() {
//		Double suma = 0.0;
//		Double res = 0.0;
//		if (grados.size() != 0) {
//			for (Grado g : grados) {
//				suma += g.getAsignaturasObligatorias().size()
//						+ g.getAsignaturasOptativas().size();
//			}
//			if (grados.size() != 0) {
//			res = (double) (suma / plantas.size());
//			}
//		} else {
//			res = 0.0;
//		}
//		return res;
//
//	}
//
//	public static Double getMediaAsignaturasObligatoriasGrados() {
//		Double suma = 0.0;
//		Double res = 0.0;
//		if (grados.size() != 0) {
//			for (Grado g : grados) {
//				suma += g.getAsignaturasObligatorias().size();
//			}
//			if (grados.size() != 0) {
//			res = (double) (suma / sotanos.size());
//			}
//		} else {
//			res = 0.0;
//		}
//		return res;
//
//	}
//
//	public static Double getMediaAsignaturasOptativasGrados() {
//		Double suma = 0.0;
//		Double res = 0.0;
//		if (grados.size() != 0) {
//			for (Grado g : grados) {
//				suma += g.getAsignaturasOptativas().size();
//			}
//			if (grados.size() != 0) {
//			res = (double) (suma / plantas.size());
//			}
//		} else {
//			res = 0.0;
//		}
//		return res;
//
//	}
	public static Grado createGrado(String nombre,
			Set<Asignatura> asignaturasObligatorias,
			Set<Asignatura> asignaturasOptativas,
			Double numeroMinimoCreditosOptativas) {
		Grado g = null;
		if (usarJava8) {
			g = new GradoImpl2(nombre, asignaturasObligatorias,
					asignaturasOptativas, numeroMinimoCreditosOptativas);
		} else {
			g = new GradoImpl(nombre, asignaturasObligatorias,
					asignaturasOptativas, numeroMinimoCreditosOptativas);
		}
		grados.add(g);
		return g;
	}

	public static Set<Grado> getGradosCreados() {
		return new HashSet<Grado>(grados);
	}

	public static Integer getNumGradosCreados() {
		return getGradosCreados().size();
	}

	public static Double getMediaAsignaturasGrados() {
		Double suma = 0.;
		Double res = 0.;
		if (grados.size() != 0) {
			for (Grado g : grados) {
				suma += g.getAsignaturasObligatorias().size()
						+ g.getAsignaturasOptativas().size();
			}
			if(grados.size()!=0){
			res = suma / grados.size();
			}
		} else {
			res = 0.;
		}
		return res;

	}

	public static Double getMediaAsignaturasObligatoriasGrados() {
		Double suma = 0.;
		Double res = 0.;
		if (grados.size() != 0) {
			for (Grado g : grados) {
				suma += g.getAsignaturasObligatorias().size();
			}
			if(grados.size()!=0){
			res = new Double(suma / grados.size());
			}
		} else {
			res = 0.;
		}
		return res;

	}

	public static Double getMediaAsignaturasOptativasGrados() {
		Double suma = 0.;
		Double res = 0.;
		if (grados.size() != 0) {
			for (Grado g : grados) {
				suma += g.getAsignaturasOptativas().size();
			}
			if(grados.size()!=0){
			res = new Double(suma / grados.size());
			}
		} else {
			res = 0.;
		}
		return res;

	}
	

	// Profesor
	public static void setUsarImplementacionMapProfesor(Boolean usarMap) {
		usarMapProfesores = usarMap;
	}

	public static Profesor createProfesor(String dni, String nombre,
			String apellidos, LocalDate fechaNacimiento, String email, Categoria categoria,
			Departamento departamento) {
		Profesor p = null;
		if (usarMapProfesores) {
			p = new ProfesorImpl2(dni, nombre, apellidos, fechaNacimiento, email, categoria,
					departamento);
		} else {
			p = new ProfesorImpl(dni, nombre, apellidos, fechaNacimiento, email, categoria, departamento);
		}
		profesores.add(p);
		return p;
	}

	public static Set<Profesor> getProfesoresCreados() {
		return new HashSet<Profesor>(profesores);
	}

	public static Integer getNumProfesoresCreados() {
		return getProfesoresCreados().size();
	}

	public static Profesor createProfesor(Profesor profesor) {
		Profesor p = createProfesor(profesor.getDNI(), profesor.getNombre(),
				profesor.getApellidos(), profesor.getFechaNacimiento(),
				profesor.getEmail(), profesor.getCategoria(),
				profesor.getDepartamento());
		copiaAsignaturasYTutorias(p, profesor);
		return p;
	}

	private static void copiaAsignaturasYTutorias(Profesor nuevoProfesor, Profesor antiguoProfesor) {
		for (Asignatura a : antiguoProfesor.getAsignaturas()) {
			nuevoProfesor.imparteAsignatura(a, antiguoProfesor.dedicacionAsignatura(a));
		}
		for (Tutoria t : antiguoProfesor.getTutorias()) {
			nuevoProfesor.nuevaTutoria(t.getHoraComienzo(), t.getDuracion(),
					t.getDiaSemana());
		}
	}
}