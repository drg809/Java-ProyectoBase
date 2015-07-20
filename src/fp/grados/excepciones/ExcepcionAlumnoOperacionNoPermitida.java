package fp.grados.excepciones;

public class ExcepcionAlumnoOperacionNoPermitida extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3891856477025273036L;

	public ExcepcionAlumnoOperacionNoPermitida() {
		super();
	}

	public ExcepcionAlumnoOperacionNoPermitida(String msg) {
		super(msg);
	}

}
