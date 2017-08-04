/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.constants.enums;

import com.tiem625.tankarenatalk.constants.CodeAwareEnum;
import com.tiem625.tankarenatalk.constants.ConstantsUtils;
import com.tiem625.tankarenatalk.constants.enums.timing.DialogueCharacter;
import lombok.Getter;

/**
 *
 * @author Anatolij
 */
public enum DialogueCharacterId implements CodeAwareEnum {
    
    CLETUS(getIdFromCharacter(DialogueCharacter.CLETUS)), 
    LUGNUT(getIdFromCharacter(DialogueCharacter.LUGNUT));
    
    private static final String ID_PREFIX = "!id;";
    @Getter
    private String code;
    
    private DialogueCharacterId(String code) {
        this.code = code;
    }
    
    public static DialogueCharacterId forCharacter(DialogueCharacter character) {
        return ConstantsUtils.getEnumValForCode(DialogueCharacterId.class,
                getIdFromCharacter(character));
    }

    private static String getIdFromCharacter(DialogueCharacter character) {
        return ID_PREFIX + character.getCode();
    }
    
}
