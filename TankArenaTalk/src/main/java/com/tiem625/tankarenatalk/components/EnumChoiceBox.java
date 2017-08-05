/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.components;

import javafx.beans.NamedArg;
import javafx.scene.control.ChoiceBox;

/**
 *
 * @author Anatolij
 */
public class EnumChoiceBox<T extends Enum<T>> extends ChoiceBox<T> {
    
    public void setEnumClass(Class<T> enumClazz) {
        this.setItems(new ObservableEnumList<>(enumClazz));
        setConverter(new ObservableEnumList.Converter<>(enumClazz));
    }
    
    public EnumChoiceBox(@NamedArg("enumClass") String enumClazz) throws ClassNotFoundException {  
        super();
        setEnumClass((Class<T>) Class.forName(enumClazz));
    }
    
}
