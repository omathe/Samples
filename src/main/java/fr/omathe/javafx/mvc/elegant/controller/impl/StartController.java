package fr.omathe.javafx.mvc.elegant.controller.impl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import fr.omathe.javafx.mvc.elegant.controller.AbstractController;
import fr.omathe.javafx.mvc.elegant.handler.ViewHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController extends AbstractController {

	@FXML private Button aboutButton;

	public StartController(final ViewHandler viewHandler) {
		super(viewHandler);
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		aboutButton.setOnAction(aboutAction());
	}

	private EventHandler<ActionEvent> aboutAction() {
		return e -> {
			try {
				viewHandler.launchAboutWindow();
			} catch (final IOException ex) {
				/* implementation of alert dialog */
			}
		};
	}

}
