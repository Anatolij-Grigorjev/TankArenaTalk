/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.components;

import com.tiem625.tankarenatalk.controllers.ActorInfoController;
import java.io.IOException;
import javafx.beans.NamedArg;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/**
 *
 * @author Anatolij
 */
public abstract class CustomVBoxControl extends VBox {
    
    public CustomVBoxControl(String fxmlPath, Object controller) {
        super();
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(controller);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }     
    }
    
    
    public static class ActorInfo extends CustomVBoxControl {
        
        public ActorInfo(@NamedArg("title") String title) {
            super("/fxml/components/ActorInfo.fxml", new ActorInfoController(title));
        }
        
    }
    
}
