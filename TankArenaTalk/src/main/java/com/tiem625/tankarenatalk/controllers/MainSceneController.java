/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.controllers;

import com.tiem625.tankarenatalk.constants.GUIScenes;
import com.tiem625.tankarenatalk.model.DialogueScene;
import com.tiem625.tankarenatalk.utils.Dialogs;
import com.tiem625.tankarenatalk.utils.ModelAdapter;
import com.tiem625.tankarenatalk.utils.ModelHolder;
import com.tiem625.tankarenatalk.utils.GUIScene;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Anatolij
 */
public class MainSceneController implements Initializable {

    private FileChooser fileChooser;
    @FXML
    private Pane rootPane;

    private GUIScene<DialogueMakerController> makerScene;
    private Stage makerStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a dialog file...");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TankArena JSON Dialog Files ", "*.json"),
                new FileChooser.ExtensionFilter("All Files ", "*.*")
        );
    }

    @FXML
    private void editDialogueFile() throws IOException {

        File dialogJson = fileChooser.showOpenDialog(rootPane.getScene().getWindow());
        //no file selected
        if (dialogJson == null) {
            return;
        }
        
        ModelHolder.CHOSEN_FILE_PATH = dialogJson.getParent();
        
        String json = new String(Files.readAllBytes(dialogJson.toPath()), StandardCharsets.UTF_8);
        System.out.println("Got json string: " + json);

        try {
            ModelHolder.model = ModelAdapter.fromFileString(json);

        } catch (Throwable ex) {
            ex.printStackTrace();
            Alert dialog = Dialogs.exceptionDialogue(rootPane.getScene().getWindow(), ex);
            dialog.showAndWait();
        }

        openDialogueMakerWindow(ModelHolder.model);
    }

    @FXML
    private void createDialogueFile() {
       
        ModelHolder.model = new DialogueScene();
        
        openDialogueMakerWindow(ModelHolder.model);
        
    }

    private void openDialogueMakerWindow(DialogueScene model) {
        try {
            //call next thing
            if (makerStage == null || makerScene == null) {
                makerStage = new Stage();
                makerScene = GUIScene.initScene(GUIScenes.DIALOGUE_MAKER);
                makerStage.setScene(makerScene.getScene());
            }
            makerScene.getController().setValue(model);
            makerStage.showAndWait();
        } catch (Throwable ex) {
            ex.printStackTrace();
            Alert dialog = Dialogs.exceptionDialogue(rootPane.getScene().getWindow(), ex);
            dialog.showAndWait();
        }
    }

}
