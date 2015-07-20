package fp.grados.excepciones;

public class ExcepcionCentroOperacionNoPermitida extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1301791730051958727L;

	public ExcepcionCentroOperacionNoPermitida() {
		super();
	}

	public ExcepcionCentroOperacionNoPermitida(String msg) {
		super(msg);
	}
}
