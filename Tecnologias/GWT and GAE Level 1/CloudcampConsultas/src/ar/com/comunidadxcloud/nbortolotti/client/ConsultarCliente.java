package ar.com.comunidadxcloud.nbortolotti.client;

import ar.com.comunidadxcloud.nbortolotti.shared.ClienteDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class ConsultarCliente extends Composite {

	private final ClientesServiceAsync clienteService = GWT.create(ClientesService.class);
	
	private static ConsultarClienteUiBinder uiBinder = GWT
			.create(ConsultarClienteUiBinder.class);
	@UiField Label lblMensaje;
	@UiField Button btnConsultar;
	@UiField TextBox txtEmail;

	interface ConsultarClienteUiBinder extends
			UiBinder<Widget, ConsultarCliente> {
	}

	public ConsultarCliente() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("btnConsultar")
	void onBtnConsultarClick(ClickEvent event) 
	{
		clienteService.GetClienteImportante(txtEmail.getText(), new AsyncCallback<ClienteDTO>() {
			
			@Override
			public void onSuccess(ClienteDTO result) {
				
				lblMensaje.setText(result.getNombre());
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error");
				
			}
		});

	}
}
