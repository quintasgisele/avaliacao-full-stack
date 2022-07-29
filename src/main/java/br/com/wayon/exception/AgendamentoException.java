package br.com.wayon.exception;

public class AgendamentoException extends Exception {

	private static final long serialVersionUID = 1L;
	private Object[]			parametros;

	public AgendamentoException(final String pMessage) {
		super(pMessage);
	}

	public AgendamentoException(final String pMessage, final Throwable pThrowlable) {
		super(pMessage, pThrowlable);
	}

	public AgendamentoException(final String pMessage, final Object[] params) {
		super(pMessage);
		this.parametros = params;
	}

	public AgendamentoException(final String pMessage, final Throwable pThrowlable,
			final Object[] params) {
		super(pMessage, pThrowlable);
		this.parametros = params;
	}

	public Object[] getParametros() {
		return this.parametros;
	}

	public void setParametros(final Object[] parametros) {
		this.parametros = parametros;
	}
	
}
