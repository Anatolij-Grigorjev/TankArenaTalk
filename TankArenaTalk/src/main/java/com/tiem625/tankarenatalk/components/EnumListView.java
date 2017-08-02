/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.components;

import com.tiem625.tankarenatalk.constants.CodeAwareEnum;
import com.tiem625.tankarenatalk.constants.DisplayableEnum;
import javafx.beans.NamedArg;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 *
 * @author Anatolij
 */
public class EnumListView<T extends Enum<T>> extends ListView<T> {
    
    public EnumListView(@NamedArg("enumClass") Class<T> clazz) {
        super(new ObservableEnumList<T>(clazz));
        
        setCellFactory((param) -> {
            return new EnumListCell<T>();
        });
    }
    
    
    private static class EnumListCell<T extends Enum<T>> extends ListCell<T> {

        @Override
        protected void updateItem(T item, boolean empty) {
            super.updateItem(item, empty); 
            if (empty) {
                setText("Pick a value...");
            } else {
                setText(getValue(item));
            }
        }

        private String getValue(T item) {
            if (item instanceof DisplayableEnum) {
                return ((DisplayableEnum) item).getDisplayName();
            } 
            if (item instanceof CodeAwareEnum) {
                return ((CodeAwareEnum) item).getCode();
            }
            return item.toString();
        }
        
      
        
    }
    
}
