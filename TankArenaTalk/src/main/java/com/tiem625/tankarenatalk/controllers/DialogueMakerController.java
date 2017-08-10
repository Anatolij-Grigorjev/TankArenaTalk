/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tiem625.tankarenatalk.components.CustomPaneControl;
import com.tiem625.tankarenatalk.components.EnumChoiceBox;
import com.tiem625.tankarenatalk.components.PositiveDecimalInputField;
import com.tiem625.tankarenatalk.constants.enums.DialogueCharacterId;
import com.tiem625.tankarenatalk.constants.enums.DialogueSignalType;
import com.tiem625.tankarenatalk.constants.enums.DialogueSpeakerLocation;
import com.tiem625.tankarenatalk.constants.enums.timing.DialogueArena;
import com.tiem625.tankarenatalk.constants.enums.timing.DialogueCharacter;
import com.tiem625.tankarenatalk.constants.enums.timing.DialoguePosition;
import com.tiem625.tankarenatalk.model.DialogueScene;
import com.tiem625.tankarenatalk.model.beat.DialogueBeat;
import com.tiem625.tankarenatalk.model.beat.DialogueBeatSignal;
import com.tiem625.tankarenatalk.model.scene.DialogueBackgroundInfo;
import com.tiem625.tankarenatalk.utils.Dialogs;
import com.tiem625.tankarenatalk.utils.ModelFileAdapter;
import com.tiem625.tankarenatalk.utils.ModelHolder;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
    private CustomPaneControl.ActorInfo actorLeftInfo;
    @FXML
    private CustomPaneControl.ActorInfo actorRightInfo;
    //-----------------------------------------------------------//

    //BEATS TAB
    @FXML
    private ListView<DialogueBeat> beatsList;
    @FXML
    private GridPane beatPropsGrid;
    @FXML
    private TextField speakerName;
    @FXML
    private TextArea beatText;
    @FXML
    private EnumChoiceBox<DialogueSpeakerLocation> speakerChoice;
    @FXML
    private ListView<DialogueBeatSignal> beatSignalsList;
    @FXML
    private EnumChoiceBox<DialogueSignalType> signalTypeChoice;
    @FXML
    private GridPane signalParamsGridPane;
    @FXML
    private Button removeSignalBtn;
    @FXML
    private Button addSignalBtn;
    @FXML
    private Button addBeatBtn;
    @FXML
    private Button removeBeatBtn;
    //-----------------------------------------------------------//

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exportDialog = new FileChooser();
        exportDialog.setTitle("Save the dialog to...");
        exportDialog.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TankArena JSON Dialog Files ", "*.json"),
                new FileChooser.ExtensionFilter("All Files ", "*.*"));
        beatsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        beatsList.getSelectionModel().selectedItemProperty().addListener(
                (obs, beat, prevBeat) -> {
                    refreshBeatView(beat);
                }
        );

        addBeatBtn.setOnAction(click -> {
            beatsList.getItems().add(new DialogueBeat());
            //poke grid to awaken layout
            beatPropsGrid.setLayoutX(beatPropsGrid.getLayoutX() + 0.1);
        });
        removeBeatBtn.setOnAction(click -> {
            if (!beatsList.getSelectionModel().isEmpty()) {
                int selectedIdx = beatsList.getSelectionModel().getSelectedIndex();
                beatsList.getItems().remove(selectedIdx);
            }
        });

        beatSignalsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        beatSignalsList.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, signal, prevSignal) -> {
                    refreshSignalView(signal);
                });
        removeSignalBtn.setOnAction(click -> {
            if (!beatSignalsList.getSelectionModel().isEmpty()) {
                int selectedIdx = beatSignalsList.getSelectionModel().getSelectedIndex();
                beatSignalsList.getItems().remove(selectedIdx);
            }
        });
        addSignalBtn.setOnAction(click -> {
            beatSignalsList.getItems().add(new DialogueBeatSignal());
        });

        //BIG EXPORT BUTTON
        saveExportBtn.setOnAction(click -> {
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
                && model.getBackgroundInfo() != null ? model.getBackgroundInfo().getRightActor() : null);

        //BEATS TAB
        if (model != null && model.getDialogueBeats() != null) {
            beatsList.getItems().addAll(model.getDialogueBeats());
        }
        beatsList.getSelectionModel().selectFirst();
    }

    private void refreshBeatView(DialogueBeat model) {

        beatText.setText(model != null
                && model.getSpeech() != null ? model.getSpeech().getBeatText() : "");
        speakerName.setText(model != null
                && model.getSpeech() != null ? model.getSpeech().getBeatSpeakerName() : "");
        speakerChoice.setValue(model != null
                && model.getSpeech() != null ? model.getSpeech().getSpeakerLocation() : null);
        if (model != null && model.getSignals() != null) {
            beatSignalsList.getItems().addAll(model.getSignals());
        }
        beatSignalsList.getSelectionModel().selectFirst();
    }

    private void refreshSignalView(DialogueBeatSignal signal) {
        signalTypeChoice.setValue(signal != null ? signal.getSignalType() : null);
    }

}
