package com.marvin.bundle.framework.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author cdi305
 */
public class FXController extends Controller implements Initializable {

//    @FXML
//    private MenuBar menuBar;

    /**
     * Handle action related to input (in this case specifically only responds
     * to keyboard event CTRL-A).
     *
     * @param event Input event.
     */
//    @FXML
    public void handleKeyInput(final InputEvent event) {
        if (event instanceof KeyEvent) {
            final KeyEvent keyEvent = (KeyEvent) event;
            if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.A) {
                System.out.println(" ctrl + A ");
            }
        }
    }

//    @FXML
    public void aboutAction() {
        System.out.println("aboutAction");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        menuBar.setFocusTraversable(true);
    }

}
