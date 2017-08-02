/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.components;

import javafx.collections.ObservableListBase;
import javafx.scene.control.ListView;

/**
 *
 * @author Anatolij
 */
public class ObservableEnumList<T extends Enum<T>> extends ObservableListBase<T> {
    
    private T[] values;
    
    public ObservableEnumList(Class<T> values) {
        super();
        this.values = values.getEnumConstants();
    }

    @Override
    public T get(int index) {
        if (index < size()) {
            return values[index];
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        return values.length;
    }
    
}
