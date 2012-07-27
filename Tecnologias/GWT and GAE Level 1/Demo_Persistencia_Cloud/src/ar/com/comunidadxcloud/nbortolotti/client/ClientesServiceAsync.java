package ar.com.comunidadxcloud.nbortolotti.client;

import ar.com.comunidadxcloud.nbortolotti.shared.ClienteDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClientesServiceAsync 
{
	public void actualizarCliente(ClienteDTO cliente, AsyncCallback<ClienteDTO> callback);
}
