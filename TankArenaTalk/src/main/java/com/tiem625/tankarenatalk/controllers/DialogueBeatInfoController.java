/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.controllers;

import com.sun.javafx.collections.ObservableListWrapper;
import com.tiem625.tankarenatalk.components.EnumChoiceBox;
import com.tiem625.tankarenatalk.constants.enums.DialogueSignalType;
import com.tiem625.tankarenatalk.constants.enums.DialogueSpeakerLocation;
import com.tiem625.tankarenatalk.model.beat.DialogueBeat;
import com.tiem625.tankarenatalk.model.beat.DialogueBeatSignal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Tiem625
 */
public class DialogueBeatInfoController implements Initializable {

    private DialogueBeat model;
    @FXML
    private TextField speakerName;
    @FXML
    private TextArea beatText;
    @FXML
    private EnumChoiceBox<DialogueSpeakerLocation> speakerChoice;
    @FXML
    private ListView<DialogueBeatSignal> beatSignals;
    @FXML
    private EnumChoiceBox<DialogueSignalType> signalTypeChoice;
    @FXML
    private GridPane signalParamsGridPane;
    @FXML
    private Button removeSignalBtn;
    @FXML
    private Button addSignalBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setValue(DialogueBeat beatInfo) {
        this.model = beatInfo;
        refreshView();
    }

    private void refreshView() {

        beatText.setText(model != null
                && model.getSpeech() != null ? model.getSpeech().getBeatText() : "");
        speakerName.setText(model != null
                && model.getSpeech() != null ? model.getSpeech().getBeatSpeakerName() : "");
        speakerChoice.setValue(model != null 
            && model.getSpeech() != null? model.getSpeech().getSpeakerLocation() : null);
        beatSignals.setItems(new ObservableListWrapper<>(
                model != null? model.getSignals() : new ArrayList<>())
        );
        DialogueBeatSignal currentSignal = beatSignals.getItems().isEmpty()?
                null : beatSignals.getItems().get(0);
        signalTypeChoice.setValue(currentSignal != null? currentSignal.getSignalType() : null);
        
        removeSignalBtn.onActionProperty().addListener(click -> {
            if (!beatSignals.getSelectionModel().isEmpty()) {
                int selectedIdx = beatSignals.getSelectionModel().getSelectedIndex();
                beatSignals.getItems().remove(selectedIdx);
            }
        });
        addSignalBtn.onActionProperty().addListener(click -> {
            beatSignals.getItems().add(new DialogueBeatSignal());
        });
        
    }

}
