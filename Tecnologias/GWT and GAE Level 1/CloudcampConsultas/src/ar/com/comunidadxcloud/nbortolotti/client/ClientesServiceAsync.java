package ar.com.comunidadxcloud.nbortolotti.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import ar.com.comunidadxcloud.nbortolotti.shared.ClienteDTO;

public interface ClientesServiceAsync 
{
	void GetClienteImportante(String email, AsyncCallback<ClienteDTO> callback); 
}
