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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.Property;
import javafx.beans.property.adapter.JavaBeanObjectPropertyBuilder;
import javafx.beans.value.ChangeListener;

/**
 *
 * @author Anatolij
 */
public class ModelAdapter {

    private static ObjectMapper mapper = new ObjectMapper();

    private ModelAdapter() {
    }

    public static DialogueScene fromFileString(String jsonString) throws IOException {
        return mapper.readValue(jsonString, DialogueScene.class);
    }

    public static String toFileString(DialogueScene scene) throws JsonProcessingException {
        return mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(scene);
    }

    public static <T> void bindProperty(Property<T> prop, Object bean, String fieldName) {

        try {
            prop.bindBidirectional(
                    JavaBeanObjectPropertyBuilder
                            .create().bean(bean).name(fieldName)
                            .build());
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(ModelAdapter.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    public static <T> void replaceListenerIfExists(
            ChangeListener<T> listener,
            ChangeListener<T> replacement,
            Property<T> property) {
        if (listener != null) {
            property.removeListener(listener);
        }
        listener = replacement;
        property.addListener(listener);
    }
    
    public static <T> T nthOrNull(List<T> col, int n) {
        if (col == null || col.isEmpty()) {
            return null;
        }
        if (col.size() <= n) {
            return null;
        }
        
        return col.get(n);
    }

}
