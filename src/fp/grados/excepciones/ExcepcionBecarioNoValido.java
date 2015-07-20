package fp.grados.excepciones;

public class ExcepcionBecarioNoValido extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8772075406318117941L;

	public ExcepcionBecarioNoValido() {
		super();
	}

	public ExcepcionBecarioNoValido(String msg) {
		super(msg);
	}

}
