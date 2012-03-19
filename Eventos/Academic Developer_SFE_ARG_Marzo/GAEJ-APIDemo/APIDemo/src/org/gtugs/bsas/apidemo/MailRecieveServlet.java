/**
 * 
 */
package org.gtugs.bsas.apidemo;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Juan M. Irungaray (juan@irungaray.com.ar)
 * 
 */
public class MailRecieveServlet extends HttpServlet {

	public static Logger logger = Logger.getAnonymousLogger();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props, null);
			MimeMessage message;
			message = new MimeMessage(session, req.getInputStream());

			String recipient = ((InternetAddress) message
					.getRecipients(RecipientType.TO)[0]).getAddress();
			String subject = message.getSubject();

			logger.warning("Mensaje nuevo para "
					+ recipient
					+ ":"
					+ subject
					+ "\n"
					+ ((Multipart) message.getContent()).getBodyPart(0)
							.getContent());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
