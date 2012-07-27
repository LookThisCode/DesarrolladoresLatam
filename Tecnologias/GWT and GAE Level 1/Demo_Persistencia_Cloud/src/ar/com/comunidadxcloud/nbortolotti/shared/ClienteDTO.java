package ar.com.comunidadxcloud.nbortolotti.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ClienteDTO implements Serializable  
{
	
	private String id;
	private String nombre;
	private String apellido;
	
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
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getApellido() {
		return apellido;
	}
	
	public ClienteDTO()
	{
		
	}
	
	public ClienteDTO(String nombre, String apellido)
	{
		this();
		this.nombre = nombre;
		this.apellido = apellido;
		
	}

}
