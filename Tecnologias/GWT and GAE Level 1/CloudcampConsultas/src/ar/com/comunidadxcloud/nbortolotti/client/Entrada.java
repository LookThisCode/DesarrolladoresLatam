package ar.com.comunidadxcloud.nbortolotti.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class Entrada implements EntryPoint {

	@Override
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.EM);
		rootPanel.add(dockLayoutPanel, 10, 10);
		dockLayoutPanel.setSize("259px", "280px");
				
		ConsultarCliente obj = new ConsultarCliente();
		dockLayoutPanel.addWest(obj, 100);
	}

}
