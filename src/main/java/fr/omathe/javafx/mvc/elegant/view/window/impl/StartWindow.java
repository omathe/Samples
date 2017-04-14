package fr.omathe.javafx.mvc.elegant.view.window.impl;

import java.util.ResourceBundle;

import fr.omathe.javafx.mvc.elegant.controller.AbstractController;
import fr.omathe.javafx.mvc.elegant.view.window.AbstractWindow;

public class StartWindow extends AbstractWindow {

	public StartWindow(final AbstractController controller, final ResourceBundle bundle) {
		super(controller, bundle);
	}

	@Override
	protected String iconFileName() {
		return "startIcon.png";
	}

	@Override
	protected String fxmlFileName() {
		return "start.fxml";
	}

	@Override
	public String titleBundleKey() {
		return "start.title";
	}

}
