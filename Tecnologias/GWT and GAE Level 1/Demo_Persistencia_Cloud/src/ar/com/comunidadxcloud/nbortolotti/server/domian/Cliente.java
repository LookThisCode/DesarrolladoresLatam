package ar.com.comunidadxcloud.nbortolotti.server.domian;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.listener.StoreCallback;


import ar.com.comunidadxcloud.nbortolotti.shared.ClienteDTO;

@PersistenceCapable(identityType= IdentityType.APPLICATION,detachable="true")
public class Cliente implements StoreCallback 
{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id; 
	
	@Persistent
	private String nombre;
	@Persistent
	private String apellido;
	
	public Cliente()
	{
		
	}	
	
	public Cliente(ClienteDTO cliente)
	{
		this();
		this.nombre = cliente.getNombre();
		this.apellido = cliente.getApellido();
	}
	
	public Cliente(ClienteDTO cliente, PersistenceManager pm)
	{
		this();
		this.nombre = cliente.getNombre();
		this.apellido = cliente.getApellido();
		
		pm.makePersistent(this);
		
	}
	

	@Override
	public void jdoPreStore() {
		// TODO Auto-generated method stub
		
	}
	 
	
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

	public ClienteDTO toDTO() 
	{
	   ClienteDTO clienteDTO = new ClienteDTO(this.getNombre(),this.getApellido());
	    clienteDTO.setId(this.getId());

	    return clienteDTO;
	  }

	
	

}
