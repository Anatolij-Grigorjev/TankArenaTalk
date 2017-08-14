/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.components;

import com.tiem625.tankarenatalk.controllers.ActorInfoController;
import com.tiem625.tankarenatalk.model.scene.DialogueActorInfo;
import java.io.IOException;
import javafx.beans.NamedArg;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 *
 * @author Anatolij
 */
public abstract class CustomPaneControl extends Pane {
    
    protected Object controller;
    
    public CustomPaneControl(String fxmlPath, Object controller) {
        this(fxmlPath, true, controller);
    }
    
    public CustomPaneControl(String fxmlPath, boolean doRoot, Object controller) {
        super();
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        if (doRoot) {
            fxmlLoader.setRoot(this);
        }
        fxmlLoader.setController(controller);
        this.controller = controller;
       
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }     
    }
    
    
    public static class ActorInfo extends CustomPaneControl {
        
        public ActorInfo(@NamedArg("title") String title) {
            super("/fxml/components/ActorInfo.fxml", new ActorInfoController(title));
        }
        
        public void setValue(DialogueActorInfo actorInfo) {
            ((ActorInfoController)controller).setValue(actorInfo);
        }
        
        public DialogueActorInfo getValue() {
            return ((ActorInfoController)controller).getModel();
        }
        
    }
    
//    public static class DialogueBeatInfo extends CustomPaneControl {
//        
//        public DialogueBeatInfo() {
//            super("/fxml/components/DialogueBeatInfo.fxml", true, new DialogueBeatInfoController());
//        }
//        
//        public void setValue(DialogueBeat beatInfo) {
//            ((DialogueBeatInfoController)controller).setValue(beatInfo);
//        }
//        
//    }
    
}
