package com.framework.messenger.core;

import java.util.ArrayList;
import java.util.List;

import com.framework.kernel.module.core.KernelModule;
import com.framework.messenger.IMessengerModule;
import com.framework.messenger.support.MessageBundleUtils;

/**
 * 
 * Modulo de mensageria do framework
 * 
 */
public class MessengerModule extends KernelModule implements IMessengerModule {
	private static final long serialVersionUID = 5231933843984594668L;
	private List<String> bundleList;
	private List<String> errorMessages = new ArrayList<String>();
	private List<String> successMessages = new ArrayList<String>();

	/**
	 * 
	 * @return Retorna bundle default
	 */
	private String getDefaultBundle() {
		// o bundle default eh o primeiro definido
		if (bundleList != null && !bundleList.isEmpty()) {
			return bundleList.get(0);
		}
		return null;
	}

	public void setBundleList(List<String> bundleList) {
		this.bundleList = bundleList;
	}

	@Override
	public void initialize() {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 */
	@Override
	public void addErrorMessage(String key) {
		addErrorMessage(key, getDefaultBundle());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 */
	@Override
	public void addErrorMessage(String key, Object... params) {
		addErrorMessage(key, getDefaultBundle(), params);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 * 
	 */
	@Override
	public void addSuccessMessage(String key) {
		addSuccessMessage(key, getDefaultBundle());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 */
	@Override
	public void addSuccessMessage(String key, Object... params) {
		addSuccessMessage(key, getDefaultBundle(), params);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearAllMessages() {
		errorMessages.clear();
		successMessages.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearErrorMessages() {
		errorMessages.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearSuccessMessages() {
		successMessages.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getErrorMessages() {
		List<String> returnList = new ArrayList<String>(errorMessages);
		errorMessages.clear();
		return returnList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getSuccessMessages() {
		List<String> returnList = new ArrayList<String>(successMessages);
		successMessages.clear();
		return returnList;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 */
	@Override
	public void addErrorMessage(String key, String bundleName, Object... params) {
		if (bundleName == null) {
			bundleName = getDefaultBundle();
		}
		String message = MessageBundleUtils.getMessageResourceString(key, bundleName, params);
		if (message != null && !errorMessages.contains(message)) {
			errorMessages.add(message);
		} else if (key != null && !errorMessages.contains(key)) {
			// adiciona chave caso nao encontre a mensagem
			errorMessages.add(key);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 */
	@Override
	public void addSuccessMessage(String key, String bundleName, Object... params) {
		if (bundleName == null) {
			bundleName = getDefaultBundle();
		}
		String message = MessageBundleUtils.getMessageResourceString(key, bundleName, params);
		if (message != null && !successMessages.contains(message)) {
			successMessages.add(message);
		} else if (key != null && !successMessages.contains(key)) {
			// adiciona chave caso nao encontre a mensagem
			successMessages.add(key);
		}
	}
}
