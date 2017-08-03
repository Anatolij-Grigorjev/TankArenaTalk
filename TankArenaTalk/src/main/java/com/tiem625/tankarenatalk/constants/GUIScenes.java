/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.constants;

import com.tiem625.tankarenatalk.controllers.MainSceneController;
import java.util.Collections;
import java.util.List;
import lombok.Getter;

/**
 *
 * @author Anatolij
 */
public enum GUIScenes {
    
    MAIN_MENU("/fxml/main_menu.fxml", MainSceneController.class, Collections.EMPTY_LIST);
    
    @Getter
    private final List<String> stylesheetPaths;
    @Getter
    private final Class<?> controllerClass;
    @Getter
    private final String scenePath;
    
    
    private GUIScenes(String scenePath, Class<?> controllerClass, List<String> stylesheetPaths) {
        this.scenePath = scenePath;
        this.controllerClass = controllerClass;
        this.stylesheetPaths = stylesheetPaths;
    }
    
}
