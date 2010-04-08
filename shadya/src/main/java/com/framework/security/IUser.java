package com.framework.security;

/**
 * 
 * Usuario do sistema
 * 
 */
public interface IUser {
	/**
	 * 
	 * @return Retorna username do usuario
	 */
	String getUsername();

	/**
	 * 
	 * @return Retorna nome do usuario
	 */
	String getName();

	/**
	 * Seta username
	 * 
	 * @param username
	 */
	void setUsername(String username);

	/**
	 * Seta nome do usuario
	 * 
	 * @param name
	 */
	void setName(String name);
}