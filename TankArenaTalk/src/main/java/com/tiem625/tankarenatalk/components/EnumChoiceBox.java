/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.components;

import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public EnumChoiceBox(@NamedArg("enumClass") String enumClazz) {  
        super();
        try {
            setEnumClass((Class<T>) Class.forName(enumClazz));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EnumChoiceBox.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
