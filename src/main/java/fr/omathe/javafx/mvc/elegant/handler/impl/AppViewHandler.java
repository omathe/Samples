package fr.omathe.javafx.mvc.elegant.handler.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

import fr.omathe.javafx.mvc.elegant.handler.ViewHandler;
import fr.omathe.javafx.mvc.elegant.view.WindowFactory;
import fr.omathe.javafx.mvc.elegant.view.window.AbstractWindow;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AppViewHandler implements ViewHandler {

	private final Stage primaryStage;
	private final ResourceBundle bundle;

	public AppViewHandler(final Stage primaryStage, final ResourceBundle bundle) {
		this.primaryStage = primaryStage;
		this.bundle = bundle;
	}

	@Override
	public void launchStartWindow() throws IOException {
		buildAndShowScene(primaryStage, WindowFactory.START.createWindow(this, bundle));
	}

	@Override
	public void launchAboutWindow() throws IOException {
		final Stage aboutStage = new Stage();
		aboutStage.initModality(Modality.WINDOW_MODAL);
		buildAndShowScene(aboutStage, WindowFactory.ABOUT.createWindow(this, bundle));
	}

	private void buildAndShowScene(final Stage stage, final AbstractWindow window) throws IOException {
		try (InputStream is = getClass().getClassLoader().getResourceAsStream(window.iconFilePath())) {
			stage.getIcons().add(new Image(is));
		}
		stage.setTitle(bundle.getString(window.titleBundleKey()));
		stage.setResizable(window.resizable());
		stage.setScene(new Scene(window.root()));
		stage.show();
	}

}
