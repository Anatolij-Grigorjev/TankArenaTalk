/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.components;

import com.tiem625.tankarenatalk.controllers.ActorInfoController;
import com.tiem625.tankarenatalk.controllers.DialogueBeatInfoController;
import com.tiem625.tankarenatalk.model.beat.DialogueBeat;
import com.tiem625.tankarenatalk.model.scene.DialogueActorInfo;
import java.io.IOException;
import javafx.beans.NamedArg;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/**
 *
 * @author Anatolij
 */
public abstract class CustomVBoxControl extends VBox {
    
    protected Object controller;
    
    public CustomVBoxControl(String fxmlPath, Object controller) {
        super();
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(controller);
        this.controller = controller;
        
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
        
        public void setValue(DialogueActorInfo actorInfo) {
            ((ActorInfoController)controller).setValue(actorInfo);
        }
        
    }
    
    public static class DialogueBeatInfo extends CustomVBoxControl {
        
        public DialogueBeatInfo() {
            super("/fxml/components/DialogueBeatInfo.fxml", new DialogueBeatInfoController());
        }
        
        public void setValue(DialogueBeat beatInfo) {
            ((DialogueBeatInfoController)controller).setValue(beatInfo);
        }
        
    }
    
}
