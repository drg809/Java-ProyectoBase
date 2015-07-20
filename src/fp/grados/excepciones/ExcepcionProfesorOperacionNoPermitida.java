package fp.grados.excepciones;

public class ExcepcionProfesorOperacionNoPermitida extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3504911383152364066L;

	public ExcepcionProfesorOperacionNoPermitida() {
		super();
	}

	public ExcepcionProfesorOperacionNoPermitida(String msg) {
		super(msg);
	}

}