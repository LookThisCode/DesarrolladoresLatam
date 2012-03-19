/**
 * 
 */
package org.gtugs.bsas.apidemo;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

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
 * Nota: La direccion del remitentte debe ser alguna de las siguientes:
 * 1) Un administrador de la aplicacion GAE
 * 2) La cuenta Google del usuario logueado actualmente (determinado via User API)
 * 3) Una direccion valida del formato xxx@{APP-ID}.appspotmail.com
 * 
 */
public class MailSendServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
	
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("mailing@gaej-api-demo.appspotmail.com","GAE Demo"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					req.getParameter("toField")));
			message.setSubject(req.getParameter("subjectField"));
			message.setText(req.getParameter("messageField"));
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("MailForm.jsp").forward(req, resp);
	}
}
