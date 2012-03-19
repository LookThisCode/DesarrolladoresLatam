/**
 * 
 */
package org.gtugs.bsas.apidemo;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
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
public class QueueConsumerServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// hacer un proceso largo
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(
					"mailing@gaej-api-demo.appspotmail.com", "GAE Demo"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					req.getParameter("mail")));
			message.setSubject("Operación completa");
			message.setText(req.getParameter("nombre")
					+ ": Su operación numero " + req.getParameter("order")
					+ " fue completada con exito");
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
