package fp.grados.excepciones;

public class ExcepcioProfesorOperacionNoPermitida extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6351320844263608431L;

	public ExcepcioProfesorOperacionNoPermitida() {
		super();
	}

	public ExcepcioProfesorOperacionNoPermitida(String msg) {
		super(msg);
	}

}

