package com.kameshi.framework.module.web.handler.exception.holder;

/**
 * Classe que armazena excecoes lancadas pelo sistema.
 */
public class ExceptionHolder {
	private static final String SEPARATOR = " - ";
	private static final int MILISECOND = 1000;
	private Throwable exception;
	private String url;
	private long insertTime;

	/**
	 * Construtor que recebe valores de inicializacao dos atributos
	 * 
	 * @param exception
	 *            excecao usada na inicializacao
	 * @param url
	 *            url usada na inicializacao
	 */
	public ExceptionHolder(Throwable exception, String url) {
		if (exception == null || url == null) {
			throw new IllegalArgumentException();
		}
		this.exception = exception;
		this.url = url;
		this.insertTime = System.currentTimeMillis();
	}

	/**
	 * @return Retorna o momento em que a excecao foi lancada
	 */
	public long getInsertTime() {
		return insertTime;
	}

	/**
	 * @return Retorna excecao lancada
	 */
	public Throwable getException() {
		return exception;
	}

	/**
	 * @return Retorna recurso acessado
	 */
	public String getResource() {
		return url;
	}

	/**
	 * Retorna um double que representa quanto tempo (em segundos) a excecao
	 * esta armazenada no ExceptionHolder
	 * 
	 * @return tempo que a excecao esta armazenada
	 */
	public double getAge() {
		return (System.currentTimeMillis() - insertTime) / MILISECOND;
	}

	/**
	 * Retorna o id gerado
	 * 
	 * @return id gerado
	 */
	public String getId() {
		return generateId(exception, url);
	}

	/**
	 * Gera id de uma exceptionHolder
	 * 
	 * @param exception
	 *            excecao para qual sera gerado o id
	 * @param url
	 *            url utilizada
	 * @return id da exceptionHolder
	 */
	public static String generateId(Throwable exception, String url) {
		if (exception == null || url == null) {
			throw new IllegalArgumentException();
		}
		return (url + SEPARATOR + (exception.getMessage() == null ? exception.toString() : exception.getMessage())).trim();
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ExceptionHolder other = (ExceptionHolder) obj;
		if (getId() == null) {
			if (getId() != null) {
				return false;
			}
		} else if (!getId().equals(other.getId())) {
			return false;
		}
		return true;
	}
}
