/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiem625.tankarenatalk.model.DialogueScene;
import java.io.IOException;

/**
 *
 * @author Anatolij
 */
public class ModelFileAdapter {
    
    private static ObjectMapper mapper = new ObjectMapper();
    
    private ModelFileAdapter() {}
    
    public static DialogueScene fromFileString(String jsonString) throws IOException {
        return mapper.readValue(jsonString, DialogueScene.class);
    }
    
    public static String toFileString(DialogueScene scene) throws JsonProcessingException {
       return mapper.writeValueAsString(scene);
    }
    
}
