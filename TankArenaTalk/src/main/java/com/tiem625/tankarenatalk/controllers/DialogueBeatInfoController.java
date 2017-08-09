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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
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
    private ListView<DialogueBeatSignal> beatSignalsList;
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
