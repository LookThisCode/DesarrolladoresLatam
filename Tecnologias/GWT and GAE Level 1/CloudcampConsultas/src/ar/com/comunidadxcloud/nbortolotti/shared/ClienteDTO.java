package ar.com.comunidadxcloud.nbortolotti.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ClienteDTO implements Serializable
{
	private String id;
	  private String nombre;
	  private String email;
	  
	  public ClienteDTO()
	  {
		  
	  }
	  
	  public ClienteDTO(String email, String nombre)
	  {
		  this.setEmail(email);
		  this.setNombre(nombre);
	  }
	  
	public void setId(String id) {
		this.id = id;
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
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}

}
