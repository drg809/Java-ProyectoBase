package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionBecaNoValida;
import fp.grados.tipos.Beca;
import fp.grados.tipos.TipoBeca;

public class BecaImpl implements Beca {

	private String codigo;
	private Double cuantiaTotal;
	private Integer duracion;
	private TipoBeca tipo;
	private static final Double CUANTIATOTAL = 1500.00;

	public BecaImpl(String codigo, Double cuantiaTotal, Integer duracion,
			TipoBeca tipo) {

		this.codigo = codigo;
		this.cuantiaTotal = cuantiaTotal;
		this.duracion = duracion;
		this.tipo = tipo;
		checkCodigo(codigo);
		checkDuracion(duracion);
		checkCuantia(cuantiaTotal);
	}
	
	public BecaImpl(String s) {
		String [] b = s.split(",");
		if(b.length != 4) {
			throw new IllegalArgumentException("El tamaño del constructor es erroneo");
		}
	
		String codigoA = b[0].trim();
		Double cuantiaTotalA = new Double(b[1].trim());
		Integer duracionA = Integer.parseInt(b[2].trim());
		TipoBeca tipoA = TipoBeca.valueOf(b[3].trim());
		
		checkCodigo(codigoA);
		checkDuracion(duracionA);
		checkCuantia(cuantiaTotalA);
		
		codigo = codigoA;
		cuantiaTotal = cuantiaTotalA;
		duracion = duracionA;
		tipo = tipoA;
	}

	public BecaImpl(String codigo, TipoBeca tipo) {
		
		this.codigo = codigo;
		this.tipo = tipo;
		cuantiaTotal = CUANTIATOTAL;
		duracion = 1;
		checkCodigo(codigo);
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
		Double cuantiaMensual;
		cuantiaMensual = cuantiaTotal / duracion;
		return cuantiaMensual;
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
	
	private void checkCuantia(Double cuantia2) {
		if(cuantia2 < CUANTIATOTAL){
					
				throw new ExcepcionBecaNoValida("La cuantia minima de la beca es 1500.");
		}
	}
	

	public void setCuantiaTotal(Double cuantiaTotal) {
		checkCuantia(cuantiaTotal);
		this.cuantiaTotal = cuantiaTotal;
	}

	public void setDuracion(Integer duracion) {
		checkDuracion(duracion);
		this.duracion = duracion;
	}
	
	
	public String toString() {
		return "[" + getCodigo() + ", " + getTipo() + "]";

	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		boolean res = false;
		if (o instanceof Beca) {
			Beca b = (Beca) o;
			res = this.getCodigo().equals(b.getCodigo())
					&& this.getTipo().equals(b.getTipo());
		}
		return res;
	}

	public int hashCode() {
		return getCodigo().hashCode() * 31 + getTipo().hashCode() * 31;
	}

	public int compareTo(Beca b) {
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
