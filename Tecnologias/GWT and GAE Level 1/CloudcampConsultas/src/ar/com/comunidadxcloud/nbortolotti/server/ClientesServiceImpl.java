package ar.com.comunidadxcloud.nbortolotti.server;

import ar.com.comunidadxcloud.nbortolotti.client.ClientesService;
import ar.com.comunidadxcloud.nbortolotti.server.domain.Cliente;
import ar.com.comunidadxcloud.nbortolotti.shared.ClienteDTO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ClientesServiceImpl extends RemoteServiceServlet implements ClientesService 
{
	public ClienteDTO GetClienteImportante(String email) 
	{
	
	    return Cliente.toDTO(Cliente.getClienteImportante());
	  }
}
