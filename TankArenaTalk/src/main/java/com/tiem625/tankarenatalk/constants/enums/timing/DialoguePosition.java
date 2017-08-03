/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.constants.enums.timing;

import com.tiem625.tankarenatalk.constants.CodeAwareEnum;
import com.tiem625.tankarenatalk.constants.DisplayableEnum;
import lombok.Getter;

/**
 *
 * @author Anatolij
 */
public enum DialoguePosition implements CodeAwareEnum, DisplayableEnum {
    
    BEFORE_SCENE("before", "Before Arena"), AFTER_SCENE("after", "After Arena");
    
    @Getter
    private final String code;
    @Getter
    private final String displayName;
    
    private DialoguePosition(String code, String displayName) 
    {
        this.code = code;
        this.displayName = displayName;
    }
    
    
    
}
