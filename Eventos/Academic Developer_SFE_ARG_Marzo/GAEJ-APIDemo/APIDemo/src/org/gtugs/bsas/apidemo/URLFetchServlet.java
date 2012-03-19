/**
 * 
 */
package org.gtugs.bsas.apidemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Juan M. Irungaray (juan@irungaray.com.ar)
 * 
 */
@SuppressWarnings("serial")
public class URLFetchServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			URL url = new URL(
					"http://weather.weatherbug.com/Argentina/Buenos%20Aires-weather.html?zcode=z6286");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String line;
			String imageHTML = null;
			while ((line = reader.readLine()) != null) {
				if (line.contains("<img id=\"thermobar\"")) {
					imageHTML = line;
				}
				if (line.contains("<span id=\"divTemp\" class=\"entry-title\">")) {
					break;
				}
			}
			String temp = line
					.split("<span id=\"divTemp\" class=\"entry-title\">")[1]
					.split("&deg")[0];
			req.setAttribute("temp", temp);
			req.setAttribute("imageHTML", imageHTML);
			reader.close();
			req.getRequestDispatcher("Temp.jsp").forward(req, resp);
		} catch (Exception e) {

		}

	}
}
