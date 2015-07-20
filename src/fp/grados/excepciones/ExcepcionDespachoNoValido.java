package fp.grados.excepciones;

public class ExcepcionDespachoNoValido extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5083681642799771098L;

	public ExcepcionDespachoNoValido() {
		super();
	}

	public ExcepcionDespachoNoValido(String msg) {
		super(msg);
	}

}
