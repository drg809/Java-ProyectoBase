package fp.grados.excepciones;

public class ExcepcionEspacioNoValido extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3506161091515017638L;

	public ExcepcionEspacioNoValido() {
		super();
	}

	public ExcepcionEspacioNoValido(String msg) {
		super(msg);
	}

}
