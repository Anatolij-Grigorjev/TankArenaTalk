/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.constants.enums;

import com.tiem625.tankarenatalk.constants.CodeAwareEnum;
import lombok.Getter;

/**
 *
 * @author Tiem625
 */
public enum DialogueBeatSignalModelActionTypes implements CodeAwareEnum {
    
    ACTOR_ENTER("ActorEnter"),
    ACTOR_LEAVE("ActorLeave");
    
    @Getter
    private String code;
    
    private DialogueBeatSignalModelActionTypes(String code) {
        this.code = code;
    }
}
