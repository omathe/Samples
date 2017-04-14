package fr.omathe.javafx.mvc.elegant.controller;

import java.net.URL;
import java.util.ResourceBundle;

import fr.omathe.javafx.mvc.elegant.handler.ViewHandler;
import javafx.fxml.Initializable;

public abstract class AbstractController implements Initializable {

    protected final ViewHandler viewHandler;

    public AbstractController(final ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }

    @Override
    public abstract void initialize(URL location, ResourceBundle bundle);

}
