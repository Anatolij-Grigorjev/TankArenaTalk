/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.components;

import javafx.scene.control.ChoiceBox;

/**
 *
 * @author Anatolij
 */
public class EnumChoiceBox<T extends Enum<T>> extends ChoiceBox<T> {
    
    public EnumChoiceBox(Class<T> enumClazz) {
        
        super();
        this.setItems(new ObservableEnumList<>(enumClazz));
        setConverter(new ObservableEnumList.Converter<>(enumClazz));
    }
    
}
