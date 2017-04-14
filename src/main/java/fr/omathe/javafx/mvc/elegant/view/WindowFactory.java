package fr.omathe.javafx.mvc.elegant.view;

import java.util.ResourceBundle;

import fr.omathe.javafx.mvc.elegant.controller.impl.AboutController;
import fr.omathe.javafx.mvc.elegant.controller.impl.StartController;
import fr.omathe.javafx.mvc.elegant.handler.ViewHandler;
import fr.omathe.javafx.mvc.elegant.view.window.AbstractWindow;
import fr.omathe.javafx.mvc.elegant.view.window.impl.AboutWindow;
import fr.omathe.javafx.mvc.elegant.view.window.impl.StartWindow;

public enum WindowFactory {

	START {
		@Override
		public AbstractWindow createWindow(final ViewHandler viewHandler, final ResourceBundle bundle) {
			return new StartWindow(new StartController(viewHandler), bundle);
		}
	},

	ABOUT {
		@Override
		public AbstractWindow createWindow(final ViewHandler viewHandler, final ResourceBundle bundle) {
			return new AboutWindow(new AboutController(viewHandler), bundle);
		}
	};

	public abstract AbstractWindow createWindow(ViewHandler viewHandler, ResourceBundle bundle);

}
