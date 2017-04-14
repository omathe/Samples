package fr.omathe.javafx.mvc.elegant.view.window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import fr.omathe.javafx.mvc.elegant.controller.AbstractController;
import fr.omathe.javafx.mvc.elegant.util.AppPaths;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public abstract class AbstractWindow {

	private final AbstractController controller;
	private final ResourceBundle bundle;

	public AbstractWindow(final AbstractController controller, final ResourceBundle bundle) {
		this.controller = controller;
		this.bundle = bundle;
	}

	public Parent root() throws IOException {
		final FXMLLoader loader = new FXMLLoader(url(), bundle);
		loader.setController(controller);
		return loader.load();
	}

	private URL url() {
		return getClass().getClassLoader().getResource(AppPaths.FXML_PATH + fxmlFileName());
	}

	public String iconFilePath() {
		return AppPaths.IMG_PATH + iconFileName();
	}

	public boolean resizable() {
		return false;
	}

	protected abstract String iconFileName();
	protected abstract String fxmlFileName();
	public abstract String titleBundleKey();

}
