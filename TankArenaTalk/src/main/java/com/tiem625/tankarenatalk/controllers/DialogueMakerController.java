/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.javafx.collections.ObservableListWrapper;
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
import com.tiem625.tankarenatalk.utils.Dialogs;
import com.tiem625.tankarenatalk.utils.ModelFileAdapter;
import com.tiem625.tankarenatalk.utils.ModelHolder;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

/**
 *
 * @author Tiem625
 */
public class DialogueMakerController implements Initializable {

    private DialogueScene model;
    
    private FileChooser exportDialog;

    @FXML
    private Pane rootPane;
    @FXML
    private Button saveExportBtn;
    //GENERAL TAB
    @FXML
    private TextField sceneId;
    @FXML
    private TextArea sceneTitle;

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
       exportDialog = new FileChooser();
       exportDialog.setTitle("Save the dialog to...");
        
       beatsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
       beatsList.getSelectionModel().selectedItemProperty().addListener(
               (obs, beat, prevBeat) -> {
                   dialogueBeatInfo.setValue(beat);
               }
       );
       
       addBeatBtn.onActionProperty().addListener(click -> {
            beatsList.getItems().add(new DialogueBeat());
        });
        removeBeatBtn.onActionProperty().addListener(click -> {
            if (!beatsList.getSelectionModel().isEmpty()) {
                int selectedIdx = beatsList.getSelectionModel().getSelectedIndex();
                beatsList.getItems().remove(selectedIdx);
            }
        });
        
        //BIG EXPORT BUTTON
        saveExportBtn.onActionProperty().addListener(click -> {
            try {
                String exportedDialog = ModelFileAdapter.toFileString(
                        ModelHolder.model
                );
                exportDialog.setInitialFileName(sceneId.getText());
                exportDialog.showSaveDialog(rootPane.getScene().getWindow());
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
                Dialogs.exceptionDialogue(rootPane.getScene().getWindow(), ex)
                        .showAndWait();
            }
        });
       
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
        fadeInTime.setValue(model != null
                && model.getBackgroundInfo() != null ? model.getBackgroundInfo().getChangeTime() : DialogueBackgroundInfo.DEFAULT_START_TIME);
        fadeOutTime.setValue(model != null
                && model.getBackgroundInfo() != null ? model.getBackgroundInfo().getChangeTime() : DialogueBackgroundInfo.DEFAULT_END_TIME);
        actorLeftInfo.setValue(model != null
                && model.getBackgroundInfo() != null ? model.getBackgroundInfo().getLeftActor() : null);
        actorRightInfo.setValue(model != null
                && model.getBackgroundInfo() != null ? model.getBackgroundInfo().getRightActor(): null);
        
        //BEATS TAB
        beatsList.setItems(new ObservableListWrapper<>(
                model != null ? model.getDialogueBeats() : new ArrayList<>())
        );
        beatsList.getSelectionModel().selectFirst();        
    }

}
