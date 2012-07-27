package ar.com.comunidadxcloud.nbortolotti.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;

public class Noticias extends Composite {

	private static final Binder binder = GWT.create(Binder.class);
	@UiField FlowPanel Noti;

	interface Binder extends UiBinder<Widget, Noticias> {
	}

	public Noticias() {
		initWidget(binder.createAndBindUi(this));
	}

}
