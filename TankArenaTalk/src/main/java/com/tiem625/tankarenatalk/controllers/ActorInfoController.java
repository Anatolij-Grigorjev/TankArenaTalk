/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;

/**
 * FXML Controller class
 *
 * @author Tiem625
 */
public class ActorInfoController implements Initializable {

    private final String title;
    @FXML
    private TitledPane actorPane;
    
    public ActorInfoController(String title) {
        this.title = title;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actorPane.setText(title);
    }

}
