package fp.grados.excepciones;

public class ExcepcionGradoNoValido extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2135911711647729587L;

	public ExcepcionGradoNoValido() {
		super();
	}

	public ExcepcionGradoNoValido(String msg) {
		super(msg);
	}

}


