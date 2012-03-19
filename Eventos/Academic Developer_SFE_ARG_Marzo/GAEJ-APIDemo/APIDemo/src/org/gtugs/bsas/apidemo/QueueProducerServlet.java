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

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

/**
 * @author Juan M. Irungaray (juan@irungaray.com.ar)
 * 
 */
public class QueueProducerServlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getAnonymousLogger();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Queue queue = QueueFactory.getDefaultQueue();
		TaskOptions taskOptions = TaskOptions.Builder.withDefaults();
		taskOptions.param("nombre", req.getParameter("nameField")).param(
				"mail", req.getParameter("mailField"));
		int amount = Integer.parseInt(req.getParameter("amountField"));
		for (int i = 0; i < amount; i++) {
			queue.add(taskOptions.param("order", ""+i));
		}

		LOGGER.fine("Se encolaron " + amount + " tareas");
		req.getRequestDispatcher("QueueSucess.jsp").forward(req, resp);
	}
}
