package fr.omathe.javafx.mvc.elegant.view.window.impl;

import java.util.ResourceBundle;

import fr.omathe.javafx.mvc.elegant.controller.AbstractController;
import fr.omathe.javafx.mvc.elegant.view.window.AbstractWindow;

public class AboutWindow extends AbstractWindow {

	public AboutWindow(final AbstractController controller, final ResourceBundle bundle) {
		super(controller, bundle);
	}

	@Override
	protected String iconFileName() {
		return "aboutIcon.png";
	}

	@Override
	protected String fxmlFileName() {
		return "about.fxml";
	}

	@Override
	public String titleBundleKey() {
		return "about.title";
	}
}
