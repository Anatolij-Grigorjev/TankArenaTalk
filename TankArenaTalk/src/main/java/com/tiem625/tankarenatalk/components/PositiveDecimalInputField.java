/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.components;

import java.math.BigDecimal;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Tiem625
 */
public class PositiveDecimalInputField extends TextField {
    
    private final String DECIMAL_REGEX = "^{0,1}\\d+\\.?\\d*$";
    
    private BigDecimal value;
    private ObjectProperty<BigDecimal> valueProperty = 
            new SimpleObjectProperty<>(this, "value");
    
    
    public PositiveDecimalInputField() {
        super();
        this.textProperty().addListener((value, oldVal, newVal) -> {
            if (!newVal.matches(DECIMAL_REGEX)) {
                if (oldVal.matches(DECIMAL_REGEX)) {
                   this.setText(oldVal); 
                } else {
                    this.setValue(BigDecimal.ZERO);
                }
            }
        });
    }
    
    public PositiveDecimalInputField(BigDecimal value) {
        this();
        setValue(value);
    }
   
    public BigDecimal getValue() {
        if (!StringUtils.isEmpty(getText())) {
            return this.value;
        } else {
            return BigDecimal.ZERO;
        }
    }
    
    public void setValue(BigDecimal value) {
        this.value = value;
        setText(value != null? value.toString(): "");
    }
    
    public ObjectProperty<BigDecimal> valueProperty() {
        return valueProperty;
    }
    
}
