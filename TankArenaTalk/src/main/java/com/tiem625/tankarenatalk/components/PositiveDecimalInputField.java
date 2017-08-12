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

/**
 *
 * @author Tiem625
 */
public class PositiveDecimalInputField extends TextField {

    private final String DECIMAL_REGEX = "^{0,1}\\d+\\.?\\d*$";

    private BigDecimal value;
    private final ObjectProperty<BigDecimal> valueProperty
            = new SimpleObjectProperty<>(this, "value");

    public PositiveDecimalInputField() {
        super();

        this.textProperty().addListener((value, oldVal, newVal) -> {
            if (!newVal.matches(DECIMAL_REGEX)) {
                if (oldVal.matches(DECIMAL_REGEX)) {
                    this.setValue(new BigDecimal(oldVal));
                } else {
                    this.setValue(BigDecimal.ZERO);
                }
            } else {
                this.setValue(new BigDecimal(newVal));
            }
        });

        valueProperty.addListener((obs, oldVal, newVal) -> {
            this.value = newVal;
            setText(value != null ? value.toString() : "");
        });
    }

    public PositiveDecimalInputField(BigDecimal value) {
        this();
        setValue(value);
    }

    public BigDecimal getValue() {
        return valueProperty.get();
    }

    public void setValue(BigDecimal value) {
        valueProperty.setValue(value);
    }

    public ObjectProperty<BigDecimal> valueProperty() {
        return valueProperty;
    }

}
