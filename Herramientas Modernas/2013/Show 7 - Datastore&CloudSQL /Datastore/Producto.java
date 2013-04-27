package herramientasmodernas.nbortolotti;

import com.google.appengine.api.datastore.Entity;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


public class Producto {


  public static void createOrUpdateProduct(String nombre, String descripcion) {
    Entity product = getProduct(nombre);
  	if (product == null) 
  	{
  	  product = new Entity("Producto", nombre);
  	  product.setProperty("descripcion", descripcion);
  	} else {
  	  product.setProperty("descripcion", descripcion);
  	}
  	
  	Util.persistEntity(product);
  }


  public static Iterable<Entity> getAllProducts(String clase) {
    return Util.listEntities(clase, null, null);
  }


  public static Entity getProduct(String nombre) {
  	
	Key key = KeyFactory.createKey("Producto",nombre);
  	return Util.findEntity(key);
  }


  public static String deleteProduct(String productKey)
  {
	  Key key = KeyFactory.createKey("Producto",productKey);	   
	  	    
	  Util.deleteEntity(key);
	  return "Producto Borrado Correctamente";
	  
  }
}
