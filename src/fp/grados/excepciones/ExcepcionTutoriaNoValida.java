package fp.grados.excepciones;

public class ExcepcionTutoriaNoValida extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5430155244127041962L;

	public ExcepcionTutoriaNoValida() {
		super();
	}

	public ExcepcionTutoriaNoValida(String msg) {
		super(msg);
	}

}
