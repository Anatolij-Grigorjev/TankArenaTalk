/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.controllers;

import com.sun.javafx.collections.ObservableListWrapper;
import com.tiem625.tankarenatalk.components.CustomPaneControl;
import com.tiem625.tankarenatalk.components.EnumChoiceBox;
import com.tiem625.tankarenatalk.components.PositiveDecimalInputField;
import com.tiem625.tankarenatalk.constants.enums.DialogueCharacterId;
import com.tiem625.tankarenatalk.constants.enums.DialogueBeatSignalType;
import com.tiem625.tankarenatalk.constants.enums.DialogueSpeakerLocation;
import com.tiem625.tankarenatalk.constants.enums.timing.DialogueArena;
import com.tiem625.tankarenatalk.constants.enums.timing.DialogueCharacter;
import com.tiem625.tankarenatalk.constants.enums.timing.DialoguePosition;
import com.tiem625.tankarenatalk.model.DialogueScene;
import com.tiem625.tankarenatalk.model.beat.DialogueBeat;
import com.tiem625.tankarenatalk.model.beat.DialogueBeatSignal;
import com.tiem625.tankarenatalk.utils.Dialogs;
import com.tiem625.tankarenatalk.utils.ModelAdapter;
import com.tiem625.tankarenatalk.utils.ModelHolder;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
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
    private TextField speakerName;
    private ChangeListener<String> speakerNameBinding;
    @FXML
    private TextArea beatText;
    private ChangeListener<String> beatTextBinding;
    @FXML
    private EnumChoiceBox<DialogueSpeakerLocation> speakerChoice;
    private ChangeListener<DialogueSpeakerLocation> speakerChoiceBinding;
    @FXML
    private ListView<DialogueBeatSignal> beatSignalsList;
    @FXML
    private EnumChoiceBox<DialogueBeatSignalType> signalTypeChoice;
    private ChangeListener<DialogueBeatSignalType> signalTypeChoiceBinding;
    @FXML
    private GridPane signalParamsPane;
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
                    int selectedIdx = beatsList.getSelectionModel()
                            .getSelectedIndex();
                    DialogueBeat get = model.getDialogueBeats().get(selectedIdx);
                    refreshBeatView(get);
                    refreshSignalView(get.getSignals()
                            .stream().findFirst().orElse(null));
                }
        );

        addBeatBtn.setOnAction(click -> {
            beatsList.getItems().add(new DialogueBeat());

            model.setDialogueBeats(beatsList.getItems());

        });
        removeBeatBtn.setOnAction(click -> {
            if (!beatsList.getSelectionModel().isEmpty()) {
                int selectedIdx = beatsList.getSelectionModel().getSelectedIndex();
                beatsList.getItems().remove(selectedIdx);
                model.setDialogueBeats(beatsList.getItems());
            }
        });
        
        ModelAdapter.replaceListenerIfExists(speakerNameBinding, (obs, o, n) -> {
            int beatIdx = beatsList.getSelectionModel().getSelectedIndex();
            if (beatIdx >= 0) {
                model.getDialogueBeats().get(beatIdx)
                        .getSpeech().setBeatSpeakerName(n);
            }
        }, speakerName.textProperty());
        ModelAdapter.replaceListenerIfExists(speakerChoiceBinding, (obs, o, n) -> {
            int beatIdx = beatsList.getSelectionModel().getSelectedIndex();
            if (beatIdx >= 0) {
                model.getDialogueBeats().get(beatIdx)
                        .getSpeech().setSpeakerLocation(n);
            }
        }, speakerChoice.valueProperty());
        ModelAdapter.replaceListenerIfExists(beatTextBinding, (obs, o, n) -> {
            int beatIdx = beatsList.getSelectionModel().getSelectedIndex();
            if (beatIdx >= 0) {
                model.getDialogueBeats().get(beatIdx)
                        .getSpeech().setBeatText(n);
            }
        }, beatText.textProperty());
        
        beatSignalsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        beatSignalsList.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, signal, prevSignal) -> {
                    if (!beatsList.getSelectionModel().isEmpty()
                            && !beatSignalsList.getSelectionModel().isEmpty()) {
                        int selectedSignalIdx = beatSignalsList.getSelectionModel()
                                .getSelectedIndex();
                        int selectedBeatIdx = beatsList.getSelectionModel()
                                .getSelectedIndex();
                        refreshSignalView(
                                model.getDialogueBeats()
                                        .get(selectedBeatIdx)
                                        .getSignals().get(selectedSignalIdx));
                    }
                });
        removeSignalBtn.setOnAction(click -> {
            if (!beatSignalsList.getSelectionModel().isEmpty()) {
                int selectedIdx = beatSignalsList.getSelectionModel().getSelectedIndex();
                beatSignalsList.getItems().remove(selectedIdx);
                if (!beatsList.getSelectionModel().isEmpty()) {
                    int beatIdx = beatsList.getSelectionModel().getSelectedIndex();
                    model.getDialogueBeats().get(beatIdx).setSignals(beatSignalsList.getItems());
                }
            }
        });
        addSignalBtn.setOnAction(click -> {
            beatSignalsList.getItems().add(new DialogueBeatSignal());
            if (!beatsList.getSelectionModel().isEmpty()) {
                int selectedIdx = beatsList.getSelectionModel().getSelectedIndex();
                model.getDialogueBeats().get(selectedIdx).setSignals(beatSignalsList.getItems());
            }
        });
        ModelAdapter.replaceListenerIfExists(signalTypeChoiceBinding, (obs, o, n) -> {
            int beatIdx = beatsList.getSelectionModel().getSelectedIndex();
            int signalIdx = beatSignalsList.getSelectionModel().getSelectedIndex();
            if (beatIdx >= 0 && signalIdx >= 0) {
                model.getDialogueBeats().get(beatIdx)
                        .getSignals().get(signalIdx).setSignalType(n);
            }
        }, signalTypeChoice.valueProperty());

        //BIG EXPORT BUTTON
        saveExportBtn.setOnAction(click -> {
            //readjust actor info in model
            ModelHolder.model.getBackgroundInfo()
                    .setLeftActor(actorLeftInfo.getValue());
            ModelHolder.model.getBackgroundInfo()
                    .setRightActor(actorRightInfo.getValue());
            try {
                String exportedDialog = ModelAdapter.toFileString(
                        ModelHolder.model
                );
                exportDialog.setInitialFileName(sceneId.getText());
                File saveFile = exportDialog.showSaveDialog(rootPane.getScene().getWindow());
                Files.write(
                        saveFile.toPath(), 
                        exportedDialog.getBytes(StandardCharsets.UTF_8));
            } catch (IOException ex) {
                ex.printStackTrace();
                Dialogs.exceptionDialogue(rootPane.getScene().getWindow(), ex)
                        .showAndWait();
            }
        });

    }

    public void setValue(DialogueScene model) {

        this.model = model;
        setBindings();
        refreshView();
    }

    private void refreshView() {

        //SCENE TAB
        actorLeftInfo.setValue(model != null
                && model.getBackgroundInfo() != null ? model.getBackgroundInfo().getLeftActor() : null);
        actorRightInfo.setValue(model != null
                && model.getBackgroundInfo() != null ? model.getBackgroundInfo().getRightActor() : null);

        //BEATS TAB
        if (model != null && model.getDialogueBeats() != null) {

            model.getDialogueBeats().forEach(System.out::println);
            beatsList.setItems(
                    new ObservableListWrapper<>(model.getDialogueBeats()));
        }
//        beatsList.getSelectionModel().selectFirst();

    }

    private void refreshBeatView(DialogueBeat model) {

        beatText.setText(model != null
                && model.getSpeech() != null ? model.getSpeech().getBeatText() : "");
        speakerName.setText(model != null
                && model.getSpeech() != null ? model.getSpeech().getBeatSpeakerName() : "");
        speakerChoice.setValue(model != null
                && model.getSpeech() != null ? model.getSpeech().getSpeakerLocation() : null);
        if (model != null && model.getSignals() != null) {
            beatSignalsList.setItems(
                    new ObservableListWrapper<>(model.getSignals()));
        }
        
    }

    private void refreshSignalView(DialogueBeatSignal signal) {
        signalTypeChoice.setValue(signal != null ? signal.getSignalType() : null);
    }

    private void setBindings() {

        ModelAdapter.bindProperty(sceneId.textProperty(), model, "id");
        ModelAdapter.bindProperty(sceneTitle.textProperty(), model, "name");
        ModelAdapter.bindProperty(sceneArenaChoice.valueProperty(), model.getTiming(), "dialogueArena");
        ModelAdapter.bindProperty(scenePositionChoice.valueProperty(), model.getTiming(), "dialoguePosition");
        ModelAdapter.bindProperty(sceneCharacterChoice.valueProperty(), model.getTiming(), "dialogueCharacter");
        ModelAdapter.bindProperty(sceneBackgroundChoice.valueProperty(), model.getBackgroundInfo(), "backgroundImage");
        ModelAdapter.bindProperty(changeBackgroundTime.valueProperty(), model.getBackgroundInfo(), "changeTime");
        ModelAdapter.bindProperty(fadeInTime.valueProperty(), model.getBackgroundInfo(), "startTime");
        ModelAdapter.bindProperty(fadeOutTime.valueProperty(), model.getBackgroundInfo(), "endTime");

    }

}
