/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.constants;

import lombok.Getter;

/**
 *
 * @author Anatolij
 */
public enum DialogueSignalType implements CodeAwareEnum {
    
    LEFT_ACTION("left_action"),
    LEFT_CHANGE_MODEL("left_change_model"),
    RIGHT_ACTION("right_action"),
    RIGHT_CHANGE_MODEL("right_change_model"),
    CHANGE_BACKGROUND("change_bg");
    
    @Getter
    private String code;
    
    private DialogueSignalType(String code) {
        this.code = code;
    }
}
