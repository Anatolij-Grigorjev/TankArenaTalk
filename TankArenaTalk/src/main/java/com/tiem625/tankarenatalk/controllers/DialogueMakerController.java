/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.controllers;

import com.tiem625.tankarenatalk.components.CustomVBoxControl;
import com.tiem625.tankarenatalk.components.EnumChoiceBox;
import com.tiem625.tankarenatalk.components.PositiveDecimalInputField;
import com.tiem625.tankarenatalk.constants.enums.DialogueCharacterId;
import com.tiem625.tankarenatalk.constants.enums.timing.DialogueArena;
import com.tiem625.tankarenatalk.constants.enums.timing.DialogueCharacter;
import com.tiem625.tankarenatalk.constants.enums.timing.DialoguePosition;
import com.tiem625.tankarenatalk.model.DialogueScene;
import com.tiem625.tankarenatalk.model.beat.DialogueBeat;
import com.tiem625.tankarenatalk.model.scene.DialogueBackgroundInfo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author Tiem625
 */
public class DialogueMakerController implements Initializable {

    private DialogueScene model;

    @FXML
    private Pane rootPane;
    @FXML
    private Button saveExportBtn;
    //GENERAL TAB
    @FXML
    private TextField sceneId;
    @FXML
    private TextField sceneTitle;

    @FXML
    private EnumChoiceBox<DialogueArena> sceneArenaChoice;
    @FXML
    private EnumChoiceBox<DialoguePosition> scenePositionChoice;
    @FXML
    private EnumChoiceBox<DialogueCharacter> sceneCharacterChoice;
    //-----------------------------------------------------------//

    //SCENE TAB
    @FXML
    private EnumChoiceBox<DialogueCharacterId> sceneBackgroundChoice;
    @FXML
    private PositiveDecimalInputField changeBackgroundTime;
    @FXML
    private PositiveDecimalInputField fadeInTime;
    @FXML
    private PositiveDecimalInputField fadeOutTime;
    @FXML
    private CustomVBoxControl.ActorInfo actorLeftInfo;
    @FXML
    private CustomVBoxControl.ActorInfo actorRightInfo;
    //-----------------------------------------------------------//

    //BEATS TAB
    @FXML
    private ListView<DialogueBeat> beatsList;
    @FXML
    private CustomVBoxControl.DialogueBeatInfo dialogueBeatInfo;
    @FXML
    private Button addBeatBtn;
    @FXML
    private Button removeBeatBtn;
    //-----------------------------------------------------------//

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setValue(DialogueScene model) {

        this.model = model;
        refreshView();
    }

    private void refreshView() {

        //GENERAL TAB
        sceneId.setText(model != null ? model.getId() : "");
        sceneTitle.setText(model != null ? model.getName() : "");
        sceneArenaChoice.setValue(
                model != null
                && model.getTiming() != null ? model.getTiming().getDialogueArena() : null);
        scenePositionChoice.setValue(
                model != null
                && model.getTiming() != null ? model.getTiming().getDialoguePosition() : null);
        sceneCharacterChoice.setValue(
                model != null
                && model.getTiming() != null ? model.getTiming().getDialogueCharacter() : null);

        //SCENE TAB
        sceneBackgroundChoice.setValue(
                model != null
                && model.getBackgroundInfo() != null ? model.getBackgroundInfo().getBackgroundImage() : null);
        changeBackgroundTime.setValue(model != null
                && model.getBackgroundInfo() != null ? model.getBackgroundInfo().getChangeTime() : DialogueBackgroundInfo.DEFAULT_CHANGE_TIME);

    }

}
