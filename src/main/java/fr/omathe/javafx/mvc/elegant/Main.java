package fr.omathe.javafx.mvc.elegant;

import static fr.omathe.javafx.mvc.elegant.util.AppPaths.RESOURCE_BUNDLE;

import java.util.Locale;
import java.util.ResourceBundle;

import fr.omathe.javafx.mvc.elegant.handler.impl.AppViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(final Stage primaryStage) throws Exception {

		new AppViewHandler(primaryStage, ResourceBundle.getBundle(RESOURCE_BUNDLE, Locale.getDefault())).launchStartWindow();
	}

	public static void main(final String[] args) {
		launch(args);
	}
}
