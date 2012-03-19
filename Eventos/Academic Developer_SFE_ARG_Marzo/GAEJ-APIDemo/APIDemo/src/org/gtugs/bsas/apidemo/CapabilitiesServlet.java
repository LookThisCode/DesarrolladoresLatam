/**
 * 
 */
package org.gtugs.bsas.apidemo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.capabilities.CapabilitiesService;
import com.google.appengine.api.capabilities.CapabilitiesServiceFactory;
import com.google.appengine.api.capabilities.Capability;
import com.google.appengine.api.capabilities.CapabilityStatus;

/**
 * @author Juan M. Irungaray (juan@irungaray.com.ar)
 * 
 */
public class CapabilitiesServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		CapabilitiesService service = CapabilitiesServiceFactory
				.getCapabilitiesService();

		req.setAttribute("imagesWork", service.getStatus(Capability.IMAGES)
				.getStatus() == CapabilityStatus.ENABLED);
		req.setAttribute(
				"dataReadWork",
				service.getStatus(Capability.DATASTORE).getStatus() == CapabilityStatus.ENABLED);
		req.setAttribute(
				"dataWriteWork",
				service.getStatus(Capability.DATASTORE_WRITE).getStatus() == CapabilityStatus.ENABLED);
		req.setAttribute("mailWork", service.getStatus(Capability.MAIL)
				.getStatus() == CapabilityStatus.ENABLED);
		req.setAttribute("xmppWork", service.getStatus(Capability.XMPP)
				.getStatus() == CapabilityStatus.ENABLED);
		req.setAttribute("queuesWork", service.getStatus(Capability.TASKQUEUE)
				.getStatus() == CapabilityStatus.ENABLED);

		req.getRequestDispatcher("CapabilitiesDashBoard.jsp")
				.forward(req, resp);
	}

}
