package nickbortolotti.engineering;

import com.google.appengine.api.rdbms.AppEngineDriver;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class enviar extends HttpServlet {
	  
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	  throws IOException {
	   
	  PrintWriter out = resp.getWriter();
	  Connection c = null;
	    try {
	      DriverManager.registerDriver(new AppEngineDriver());
	      c = DriverManager.getConnection("jdbc:google:rdbms://nbortolotticloudsqldatabase:example1/demoClientes");
	      String fname = req.getParameter("nombre");
	      String content = req.getParameter("descripcion");
	      if (fname == "" || content == "") {
	        out.println("<html><head></head><body>Inconvenientes con los datos! Vuelva a intentar! Redirigiendo en 3 seg...</body></html>");
	      } else {
	      String statement ="INSERT INTO entries (nombreCliente, descripcionCliente) VALUES( ? , ? )";
	      PreparedStatement stmt = c.prepareStatement(statement);
	      stmt.setString(1, fname);
	      stmt.setString(2, content);
	      int success = 2;
	      success = stmt.executeUpdate();
	      if(success == 1) {
	        out.println("<html><head></head><body>Exitoso! Redirigiendo en 3 seg...</body></html>");
	      } else if (success == 0) {
	        out.println("<html><head></head><body>Fallo! Volva a intentar! Redirigiendo en 3 seg...</body></html>");
	      }
	     }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (c != null) 
	          try {
	            c.close();
	            } catch (SQLException ignore) {
	         }
	      } resp.setHeader("Refresh","3; url=/EnviarDatos.jsp");
	  }

}
