package fp.grados.excepciones;

public class ExcepcionExpedienteOperacionNoPermitida extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5025358890676712259L;

	public ExcepcionExpedienteOperacionNoPermitida() {
		super();
	}

	public ExcepcionExpedienteOperacionNoPermitida(String msg) {
		super(msg);
	}

}


