package com.kameshi.framework.module.mail.message;

/**
 * 
 * Classe que representa uma mensagem de email
 * 
 */
public class MailMessage {
	private String to;
	private String from;
	private String subject;
	private String text;

	public MailMessage(String to, String from, String subject, String text) {
		this.to = to;
		this.from = from;
		this.subject = subject;
		this.text = text;
	}

	public MailMessage() {
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
