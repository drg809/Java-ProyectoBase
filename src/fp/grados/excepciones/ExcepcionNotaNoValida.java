package fp.grados.excepciones;

public class ExcepcionNotaNoValida extends RuntimeException{


	/**
	 * 
	 */
	private static final long serialVersionUID = -8014566983823992943L;

	public ExcepcionNotaNoValida() {
		super();
	}

	public ExcepcionNotaNoValida(String msg) {
		super(msg);
	}

}