package herramientasmodernas.nbortolotti;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;
//import com.google.appengine.codelab.Util;

@SuppressWarnings("serial")
public class ProductoServlet extends BaseServlet {

  private static final Logger logger = Logger.getLogger(ProductoServlet.class.getCanonicalName());
  /**
   * Get the entities in JSON format.
   */

  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
	super.doGet(req, resp);
	
    logger.log(Level.INFO, "Obteniendo la lista de Productos");
    
    String searchFor = req.getParameter("q");
    PrintWriter out = resp.getWriter();
    
    Iterable<Entity> entities = null;
    if (searchFor == null || searchFor.equals("") || searchFor == "*") {
      entities = Producto.getAllProducts("Producto");
      out.println(Util.writeJSON(entities));
    } else {
      Entity product = Producto.getProduct(searchFor);
      if (product != null) {
        Set<Entity> result = new HashSet<Entity>();
        result.add(product);
        out.println(Util.writeJSON(result));
      }
    }
  }


  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    //Registro de Control
	logger.log(Level.INFO, "Creando el Producto");
    
    PrintWriter out = resp.getWriter();

    String category = req.getParameter("nombre");
    String description = req.getParameter("descripcion");
    
    try {
    	//Creacion de la entidad
      Producto.createOrUpdateProduct(category, description);
    } 
    catch (Exception e) 
    {
    	//Mensaje del Manejo de Errores
      String msg = Util.getErrorMessage(e);
      out.print(msg);
    }
  }

  
  
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String productkey = req.getParameter("id");
    
    PrintWriter out = resp.getWriter();
    
    try{    	
    	out.println(Producto.deleteProduct(productkey));
    } catch(Exception e) {
    	out.println(Util.getErrorMessage(e));
    }    
  }


  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    //Accion generada
	  String action = req.getParameter("action");
    
    if (action.equalsIgnoreCase("delete")) {
      doDelete(req, resp);
      return;
    } else if (action.equalsIgnoreCase("put")) {
      doPut(req, resp);
      return;
    }
  }

}