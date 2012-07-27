package ar.com.comunidadxcloud.nbortolotti.client;

import ar.com.comunidadxcloud.nbortolotti.shared.ClienteDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("clientesService")
public interface ClientesService extends RemoteService 
{

	ClienteDTO actualizarCliente(ClienteDTO cliente);
}
