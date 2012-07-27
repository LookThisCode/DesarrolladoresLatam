package ar.com.comunidadxcloud.nbortolotti.client;

import ar.com.comunidadxcloud.nbortolotti.shared.ClienteDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("cliente")
public interface ClientesService extends RemoteService 
{
	ClienteDTO GetClienteImportante(String email); 
	
}