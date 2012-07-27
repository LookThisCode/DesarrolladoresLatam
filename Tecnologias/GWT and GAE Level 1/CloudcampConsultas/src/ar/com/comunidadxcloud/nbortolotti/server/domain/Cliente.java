package ar.com.comunidadxcloud.nbortolotti.server.domain;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


import ar.com.comunidadxcloud.nbortolotti.server.PMF;
import ar.com.comunidadxcloud.nbortolotti.shared.ClienteDTO;



@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class Cliente 
{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;

	@Persistent
	private String nombre;
	@Persistent
	private String apellido;
	@Persistent
	private String email;
	
	
	public Cliente()
	{
		
	}
	
	/*public Cliente(String email, String nombre)
	{
		this.setEmail(email);
		this.setNombre(nombre);
	}*/
	
	public String getId() {
		return id;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getNombre() {
		return nombre;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public String getApellido() {
		return apellido;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}



	public String getEmail() {
		return email;
	}
	
	 public static ClienteDTO toDTO(Cliente c) {
		    if (c == null) {
		      return null;
		    }
		    return new ClienteDTO(c.getEmail(), c.getNombre());
		  }
	 
	 public static Cliente getClienteImportante() {
		    
		    String defaultEmail = "default@default.com";
		    
		    PersistenceManager pm = PMF.get().getPersistenceManager();
		    Cliente oneResult = null, detached = null;
		    Query q = pm.newQuery(Cliente.class, "emailAddress == :email");
		    q.setUnique(true);
		    try {
		      oneResult = (Cliente) q.execute(defaultEmail);
		      if (oneResult != null) {
		        detached = pm.detachCopy(oneResult);
		      }
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      pm.close();
		      q.closeAll();
		    }
		    return detached;
		  }
	

}
