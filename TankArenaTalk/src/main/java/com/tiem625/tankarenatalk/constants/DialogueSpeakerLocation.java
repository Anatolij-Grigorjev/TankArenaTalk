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
public enum DialogueSpeakerLocation implements CodeAwareEnum {
    
    LEFT_ACTOR("left"), RIGHT_ACTOR("right");
    
    @Getter
    private final String code;
    
    private DialogueSpeakerLocation(String code) {
        this.code = code;
    }
}
