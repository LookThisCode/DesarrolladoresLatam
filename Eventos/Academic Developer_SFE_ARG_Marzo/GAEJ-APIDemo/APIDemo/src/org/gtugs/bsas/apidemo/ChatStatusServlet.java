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
import com.google.appengine.api.xmpp.MessageType;
import com.google.appengine.api.xmpp.Presence;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

/**
 * @author Juan M. Irungaray (juan@irungaray.com.ar)
 *
 */
public class ChatStatusServlet extends HttpServlet {

	public static final Logger logger = Logger.getAnonymousLogger();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		
		
		try {
			XMPPService xmpp = XMPPServiceFactory.getXMPPService();
			Presence presence = xmpp.parsePresence(req);

			String from = presence.getFromJid().getId().split("/")[0];

			logger.warning("chat: el usuario "+ from +" cambio su estado a "+ presence.getPresenceType());
					
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
