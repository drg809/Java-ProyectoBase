package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionAsignaturaNoValida;

public class AsignaturaImpl implements Asignatura {

	private String nombre;
	private String codigo;
	private Double creditos;
	private TipoAsignatura tipo;
	private Integer curso;
	private Departamento departamento;
	public static String acronimo;
	
	public AsignaturaImpl(String s) {
		String [] a = s.split("#");
		if(a.length != 5) {
			throw new IllegalArgumentException("El tamaño del constructor es erroneo");
		}
		String codigoA = a[1].trim();
		Double creditosA = new Double(a[2].trim());
		Integer cursoA = new Integer(a[4].trim());
		checkCodigo(codigoA);
		checkCreditos(creditosA);
		checkCurso(cursoA);
		nombre = a[0].trim();
		codigo = codigoA;
		creditos = creditosA;
		tipo = TipoAsignatura.valueOf(a[3].trim());
		curso = cursoA;
		departamento = new DepartamentoImpl("");
		

		
	}

	public AsignaturaImpl(String nombre, String codigo, Double credito,
			TipoAsignatura tipo, Integer curso, Departamento departamento) {

		checkCodigo(codigo);
		checkCreditos(credito);
		checkCurso(curso);
		
		this.nombre = nombre;
		this.codigo = codigo;
		this.creditos = credito;
		this.tipo = tipo;
		this.curso = curso;
		setDepartamento(departamento);

	}

	public String getNombre() {
		return nombre;
	}

	public String getAcronimo() {
		String acronimo = "";
		for(int n = 0;n < nombre.length();n++) {
			
			char acr = nombre.charAt(n);
			if(Character.isUpperCase(nombre.charAt(n))) {
				acronimo = acronimo + acr;
			}
		}
		return acronimo;
	}

	public String getCodigo() {
		return codigo;
	}

	public Double getCreditos() {
		return creditos;
	}

	public TipoAsignatura getTipo() {
		return tipo;
	}

	public Integer getCurso() {
		return curso;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento d) {
		if (d != this.departamento) {
			Departamento antiguoD = this.departamento;
			this.departamento = d;

			if (antiguoD != null) {
				antiguoD.eliminaAsignatura(this);
			}
			
			if (d != null) {
				d.nuevaAsignatura(this);
			}
		}
	}
	
	private void checkCodigo(String codigo2) {
		if(codigo2.length() != 7 || !Character.isDigit(codigo2.charAt(0)) || !Character.isDigit(codigo2.charAt(1)) || !Character.isDigit(codigo2.charAt(2)) || !Character.isDigit(codigo2.charAt(3)) || !Character.isDigit(codigo2.charAt(4)) || !Character.isDigit(codigo2.charAt(5))|| !Character.isDigit(codigo2.charAt(6))){
					
				throw new ExcepcionAsignaturaNoValida("El código introducido no tiene 7 digitos");
		}
	}
	
	private void checkCreditos(Double credito) {
		if(credito <= 0.0 || credito == 0.0){
			throw new ExcepcionAsignaturaNoValida("No puede existir una asignatura con 0 creditos");
		}
	}

	private void checkCurso(Integer curso) {
		if(curso<1 || curso>4){
			throw new ExcepcionAsignaturaNoValida("El curso debe de ser superior a 1 y como maximo 4");
		}
	}

	public String toString() {
		return "(" + getCodigo() + ") " + getNombre();

	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		boolean res = false;
		if (o instanceof Asignatura) {
			Asignatura a = (Asignatura) o;
			res = this.getCodigo().equals(a.getCodigo());
		}
		return res;

	}

	public int hashCode() {
		return getCodigo().hashCode();

	}

	public int compareTo(Asignatura a) {
		int res;
		if (a == null) {
			throw new NullPointerException();
		}
		res = getCodigo().compareTo(a.getCodigo());

		return res;
	}

}
