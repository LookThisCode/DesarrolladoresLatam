package ar.com.comunidadxcloud.nbortolotti.server;

import javax.jdo.PersistenceManager;
import ar.com.comunidadxcloud.nbortolotti.client.ClientesService;
import ar.com.comunidadxcloud.nbortolotti.server.domian.Cliente;
import ar.com.comunidadxcloud.nbortolotti.shared.ClienteDTO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
@SuppressWarnings("serial")
public class ClientesServiceImpl extends RemoteServiceServlet implements ClientesService 
{

	public ClienteDTO actualizarCliente(ClienteDTO cliente)
	{
		ClienteDTO obj = new ClienteDTO();
		
		 if (cliente.getId() == null)
		 {
			 Cliente newCliente = addCliente(cliente);
		     obj =  newCliente.toDTO();     
		 }
		 
	      return obj;		 
	}
	
	private Cliente addCliente(ClienteDTO cliente) {

	    PersistenceManager pm = PMF.get().getPersistenceManager();
	    Cliente c = null;
	    try {
	    	      
	      c = new Cliente(cliente);
	      pm.makePersistent(c);
	      
	    } 
	    finally 
	    {
	      pm.close();
	    }
	    
	    return c;
	  }
}
