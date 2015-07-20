package fp.grados.excepciones;

public class ExcepcionPersonaNoValida extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5318124827114821769L;

	public ExcepcionPersonaNoValida() {
		super();
	}

	public ExcepcionPersonaNoValida(String msg) {
		super(msg);
	}

}