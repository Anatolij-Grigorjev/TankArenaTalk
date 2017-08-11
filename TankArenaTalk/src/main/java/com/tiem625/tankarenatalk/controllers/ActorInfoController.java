/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.controllers;

import com.tiem625.tankarenatalk.components.EnumChoiceBox;
import com.tiem625.tankarenatalk.components.PositiveDecimalInputField;
import com.tiem625.tankarenatalk.constants.enums.DialogueCharacterId;
import com.tiem625.tankarenatalk.model.scene.DialogueActorInfo;
import com.tiem625.tankarenatalk.utils.ModelAdapter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

/**
 * FXML Controller class
 *
 * @author Tiem625
 */
public class ActorInfoController implements Initializable {

    private final String title;
    private DialogueActorInfo model;
    
    @FXML
    private TitledPane actorPane;
    @FXML 
    private PositiveDecimalInputField actorChangeTime;
    @FXML 
    private PositiveDecimalInputField actorMoveTime;
    @FXML 
    private PositiveDecimalInputField actorDimTime;
    @FXML 
    private TextField actorCharName;
    @FXML
    private EnumChoiceBox<DialogueCharacterId> actorModelChoice;
    
    
    
    
    
    
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

    public void setValue(DialogueActorInfo actorInfo) {
        this.model = actorInfo;
        setBindings();
//        refreshView();
    }

    private void refreshView() {
        
        actorChangeTime.setValue(model != null? model.getChangeModelTime(): null);
        actorMoveTime.setValue(model != null? model.getActorMoveTime(): null);
        actorDimTime.setValue(model != null? model.getActorDimTime() : null);
        actorCharName.setText(model != null? model.getActorName(): "");
        actorModelChoice.setValue(model != null? model.getCharacterModel() : null);
        
    }

    private void setBindings() {
        
        ModelAdapter.bindProperty(actorChangeTime.valueProperty(), model, "changeModelTime");
        ModelAdapter.bindProperty(actorMoveTime.valueProperty(), model, "actorMoveTime");
        ModelAdapter.bindProperty(actorDimTime.valueProperty(), model, "actorDimTime");
        ModelAdapter.bindProperty(actorCharName.textProperty(), model, "actorName");
        ModelAdapter.bindProperty(actorModelChoice.valueProperty(), model, "characterModel");
    }

}
