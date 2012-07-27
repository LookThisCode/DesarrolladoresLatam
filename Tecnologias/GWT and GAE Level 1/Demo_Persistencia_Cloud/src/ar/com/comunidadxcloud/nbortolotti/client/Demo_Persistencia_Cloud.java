package ar.com.comunidadxcloud.nbortolotti.client;

import ar.com.comunidadxcloud.nbortolotti.shared.ClienteDTO;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Demo_Persistencia_Cloud implements EntryPoint {
	
	ClienteDTO c;
	
	
	private final ClientesServiceAsync clienteService = GWT.create(ClientesService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Grabar");
		final TextBox nameField = new TextBox();
		final TextBox apellidoField = new TextBox();
		nameField.setText("usuario");
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("apellidoFieldContainer").add(apellidoField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();
		
		

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {

				GrabarCliente();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {

				}
			}
			private void GrabarCliente()
			{
				c = new ClienteDTO(nameField.getText(), apellidoField.getText());
				clienteService.actualizarCliente(c, new AsyncCallback<ClienteDTO>() 
						{
					public void onFailure(Throwable caught)
					{
						Window.alert("Error");
					}
					public void onSuccess(ClienteDTO result)
					{
						Window.alert("Clirente creado correctamente");
					}
				
				});
			}


		}


		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
	}
}
