/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.utils;

import com.tiem625.tankarenatalk.constants.GUIScenes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import lombok.Getter;

/**
 *
 * @author Anatolij
 */
public class GUIScene<T> {

    @Getter
    private final Scene scene;
    @Getter
    private final T controller;

    private final List<String> styleSheets;

    private GUIScene(String fxmlPath,
            Class<T> controllerClazz,
            List<String> stylesPaths) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));

        scene = new Scene(loader.load());
        controller = (T) loader.getController();
        styleSheets = new ArrayList<>();
        styleSheets.add("/styles/Common.css");
        styleSheets.addAll(stylesPaths);
        scene.getStylesheets().addAll(styleSheets);
    }

    public static <T> GUIScene<T> initScene(GUIScenes scene) throws IOException {
        return new GUIScene<>(
                scene.getScenePath(), 
                (Class<T>) scene.getControllerClass(),
                scene.getStylesheetPaths());
    }

}
