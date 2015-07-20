package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionBecaNoValida;

public final class BecaInmutableImpl implements BecaInmutable {

	private final String codigo;
	private final Double cuantiaTotal;
	private final Integer duracion;
	private final TipoBeca tipo;
	private static final Double cuantiaTOTAL = 1500.00;

	public BecaInmutableImpl(String codigo, Double cuantiaTotal, Integer duracion,
			TipoBeca tipo) {

		this.codigo = codigo;
		this.cuantiaTotal = cuantiaTotal;
		this.duracion = duracion;
		this.tipo = tipo;
		checkCodigo(codigo);
		checkDuracion(duracion);
		checkCuantia(cuantiaTotal);
	}
	
	public BecaInmutableImpl(String s) {
		String [] b = s.split(",");
		if(b.length != 4) {
			throw new IllegalArgumentException("El tamaño del constructor es erroneo");
		}
		codigo = b[0].trim();
		cuantiaTotal = new Double(b[1].trim());
		duracion = Integer.parseInt(b[2].trim());
		tipo = TipoBeca.valueOf(b[3].trim());
		checkCodigo(codigo);
		checkDuracion(duracion);
		checkCuantia(cuantiaTotal);
	}

	public BecaInmutableImpl(String codigo, TipoBeca tipo) {
		
		this.codigo = codigo;
		this.tipo = tipo;
		cuantiaTotal = cuantiaTOTAL;
		duracion = 1;
		checkCodigo(codigo);
		checkDuracion(duracion);
		checkCuantia(cuantiaTotal);

	}

	public String getCodigo() {
		return codigo;
	}

	public Double getCuantiaTotal() {
		return cuantiaTotal;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public TipoBeca getTipo() {
		return tipo;
	}

	public Double getCuantiaMensual() {
		return cuantiaTotal / duracion;
	}
	
	private void checkCodigo(String codigo2) {
		if(codigo2.length() != 7 || !Character.isAlphabetic(codigo2.charAt(0)) || !Character.isAlphabetic(codigo2.charAt(1)) || !Character.isAlphabetic(codigo2.charAt(2)) || !Character.isDigit(codigo2.charAt(3)) || !Character.isDigit(codigo2.charAt(4)) || !Character.isDigit(codigo2.charAt(5))|| !Character.isDigit(codigo2.charAt(6))){
					
				throw new ExcepcionBecaNoValida("El código introducido no tiene 7 digitos");
		}
	}

	private void checkDuracion(Integer duracion) {
		if(duracion < 1){
					
				throw new ExcepcionBecaNoValida("La duracion minima de la beca es 1.");
		}
	}
	
	private void checkCuantia(Double cuantiaTotal) {
		if(cuantiaTotal < cuantiaTOTAL){
					
				throw new ExcepcionBecaNoValida("La cuantia minima de la beca es 1500.");
		}
	}
	
	public String toString() {
		return "[" + getCodigo() + ", " + getTipo() + "]";

	}

	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof BecaInmutable) {
			BecaInmutable b = (BecaInmutable) o;
			res = this.getCodigo().equals(b.getCodigo())
					&& this.getTipo().equals(b.getTipo());
		}
		return res;
	}

	public int hashCode() {
		return getCodigo().hashCode() * 31 + getTipo().hashCode();
	}

	public int compareTo(BecaInmutable b) {
		int res;
		if (b == null) {
			throw new NullPointerException();
		}
		res = getCodigo().compareTo(b.getCodigo());
		if (res == 0) {
			res = getTipo().compareTo(b.getTipo());
		}
		return res;
	}
}
