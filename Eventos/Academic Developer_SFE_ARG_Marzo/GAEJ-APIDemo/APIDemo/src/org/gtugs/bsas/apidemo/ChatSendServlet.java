/**
 * 
 */
package org.gtugs.bsas.apidemo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

/**
 * @author Juan M. Irungaray (juan@irungaray.com.ar)
 * 
 */
public class ChatSendServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		XMPPService xmpp = XMPPServiceFactory.getXMPPService();
		
		JID recipient = new JID(req.getParameter("toField"));
		
		Message message = new MessageBuilder().withRecipientJids(recipient)
				.withBody(req.getParameter("messageField")).build();
		
		SendResponse success = xmpp.sendMessage(message);
		req.getRequestDispatcher("ChatForm.jsp").forward(req, resp);
	}
}
