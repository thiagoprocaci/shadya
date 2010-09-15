package com.kameshi.framework.module.mail.core;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.kameshi.framework.kernel.module.core.KernelModule;
import com.kameshi.framework.module.mail.IMailModule;
import com.kameshi.framework.module.mail.exception.MailException;
import com.kameshi.framework.module.mail.message.MailMessage;

/**
 * 
 *Modulo de envio de email do framework
 * 
 */
public class MailModule extends KernelModule implements IMailModule {
	private static final long serialVersionUID = -8630649505962234930L;
	private static final Logger LOG = LoggerFactory.getLogger(MailModule.class);
	// mensagens de erro do framework
	private static final String MESSAGE_PARAMETER = "To and from parameters must be specified";
	private static final String MESSAGE_CONFIG = "The mail module is not configured. Please see the framework documentation";
	private static final String MESSAGE_ERROR = "Mail error exception";
	private JavaMailSender mailSender;
	private String jndiName;

	/**
	 * Set de jndiName. Essa propriedade deve ser injetada atraves do spring.
	 * 
	 * @param jndiName
	 */
	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 */
	@Override
	public void sendMail(String msg, String subject, String to, String from) {
		if (to == null || from == null) {
			throw new IllegalArgumentException(MESSAGE_PARAMETER);
		}
		sendMail(msg, subject, formatTo(to), from);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 */
	@Override
	public void sendMail(String msg, String subject, String[] to, String from) {
		if (to == null || from == null) {
			throw new IllegalArgumentException(MESSAGE_PARAMETER);
		}
		if (!isMailSenderOn()) {
			throw new MailException(MESSAGE_CONFIG);
		}
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(msg, true);
			helper.setFrom(from);
		} catch (MessagingException e) {
			throw new MailException(MESSAGE_ERROR);
		}
		mailSender.send(message);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 */
	@Override
	public void sendMail(MailMessage mailMessage) {
		sendMail(mailMessage.getText(), mailMessage.getSubject(), mailMessage.getTo(), mailMessage.getFrom());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize() {
		LOG.info("starting mail module");
	}

	/**
	 * Verifica se o mailSender esta corretamente configurado
	 * 
	 * @return Retorna true caso esteja
	 */
	private boolean isMailSenderOn() {
		if (mailSender == null) {
			try {
				InitialContext ic = new InitialContext();
				mailSender = new JavaMailSenderImpl();
				((JavaMailSenderImpl) mailSender).setSession((Session) ic.lookup(jndiName));				
			} catch (NamingException e) {
				mailSender = null;
			}
		}
		return mailSender != null;
	}

	/**
	 * Separa os destinatarios em um array
	 * 
	 * @param to
	 *            destinatarios numa unica string
	 * @return array com destinatarios separados
	 */
	private String[] formatTo(String to) {
		final String SEPARADOR_ANTIGO = ";";
		final String SEPARADOR_NOVO = ",";
		to = to.replaceAll(SEPARADOR_ANTIGO, SEPARADOR_NOVO);
		String[] strings = to.split(SEPARADOR_NOVO);
		return strings;
	}
}
