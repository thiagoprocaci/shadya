package com.kameshi.framework.module.messenger;

import java.util.List;

import com.kameshi.framework.kernel.module.IKernelModule;

/**
 * Interface do modulo de mensageria
 */
public interface IMessengerModule extends IKernelModule {
	/**
	 * Adiciona mensagem de sucesso.
	 * 
	 * @param key
	 *            chave que referencia a mensagem
	 */
	void addSuccessMessage(String key);

	/**
	 * Adiciona mensagem de sucesso.
	 * 
	 * @param key
	 *            chave que referencia a mensagem
	 * @param params
	 *            parametros a serem utilizados na mensagem
	 */
	void addSuccessMessage(String key, Object... params);

	/**
	 * Adiciona mensagem de sucesso.
	 * 
	 * @param key
	 *            chave que referencia a mensagem
	 * @param bundleName
	 *            nome do bundle
	 * @param params
	 *            parametros a serem utilizados na mensagem
	 */
	void addSuccessMessage(String key, String bundleName, Object... params);

	/**
	 * Adiciona mensagem de erro.
	 * 
	 * @param key
	 *            chave que referencia a mensagem
	 */
	void addErrorMessage(String chave);

	/**
	 * Adiciona mensagem de erro.
	 * 
	 * @param key
	 *            chave que referencia a mensagem
	 * @param params
	 *            parametros a serem utilizados na mensagem
	 */
	void addErrorMessage(String key, Object... params);

	/**
	 * Adiciona mensagem de erro.
	 * 
	 * @param key
	 *            chave que referencia a mensagem
	 * @param bundleName
	 *            nome do bundle
	 * @param params
	 *            parametros a serem utilizados na mensagem
	 */
	void addErrorMessage(String key, String bundleName, Object... params);

	/**
	 * Recupera mensagens de erro.
	 * 
	 * @return mensagens de erro
	 */
	List<String> getErrorMessages();

	/**
	 * Recupera mensagens de sucesso.
	 * 
	 * @return mensagens de sucesso
	 */
	List<String> getSuccessMessages();

	/**
	 * Limpa mensagens de erros.
	 */
	void clearErrorMessages();

	/**
	 * Limpa mensagens de sucesso.
	 */
	void clearSuccessMessages();

	/**
	 * Limpa todas mensagens.
	 */
	void clearAllMessages();
}
