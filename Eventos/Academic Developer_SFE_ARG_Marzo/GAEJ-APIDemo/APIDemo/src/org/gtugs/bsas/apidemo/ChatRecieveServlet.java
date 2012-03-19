/**
 * 
 */
package org.gtugs.bsas.apidemo;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

/**
 * @author Juan M. Irungaray (juan@irungaray.com.ar)
 *
 */
public class ChatRecieveServlet extends HttpServlet {

	public static Logger logger = Logger.getAnonymousLogger();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			XMPPService xmpp = XMPPServiceFactory.getXMPPService();
			Message message = xmpp.parseMessage(req);

			String recipient = message.getRecipientJids()[0].getId();
					
			logger.warning("chat nuevo para "+recipient+":\n"+message.getBody() );
					
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
