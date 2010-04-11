package com.framework.mail.core;

import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.framework.kernel.module.core.KernelModule;
import com.framework.mail.IMailModule;
import com.framework.mail.exception.MailException;
import com.framework.mail.message.MailMessage;

/**
 * 
 *Modulo de envio de email do framework
 * 
 */
public class MailModule extends KernelModule implements IMailModule {
	private static final long serialVersionUID = -8630649505962234930L;
	private static final Logger LOG = Logger.getLogger(MailModule.class.getName());
	private static final String MESSAGE = "To and from parameters must be specified";
	private Session session;

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
			throw new IllegalArgumentException(MESSAGE);
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
			throw new IllegalArgumentException(MESSAGE);
		}
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			for (String address : to) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(address));
			}
			message.setSubject(subject);
			message.setText(msg);
			Transport.send(message);
		} catch (AddressException e) {
			throw new MailException(e);
		} catch (MessagingException e) {
			throw new MailException(e);
		}
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
		Properties props = new Properties();
		session = Session.getDefaultInstance(props, null);
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
