/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.constants.enums;

import com.tiem625.tankarenatalk.constants.CodeAwareEnum;
import com.tiem625.tankarenatalk.constants.ConstantsUtils;
import com.tiem625.tankarenatalk.constants.DisplayableEnum;
import com.tiem625.tankarenatalk.constants.enums.timing.DialogueCharacter;
import java.util.stream.Stream;
import lombok.Getter;

/**
 *
 * @author Anatolij
 */
public enum DialogueCharacterId implements CodeAwareEnum, DisplayableEnum {
    
    CLETUS(getIdFromCharacter(DialogueCharacter.CLETUS), "Cletus"), 
    LUGNUT(getIdFromCharacter(DialogueCharacter.LUGNUT), "Lugnut");
    
    private static final String ID_PREFIX = "!id;";
    @Getter
    private String code;
    @Getter
    private String displayName;
    
    private DialogueCharacterId(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
    
    public static DialogueCharacterId forCharacter(DialogueCharacter character) {
        return ConstantsUtils.getEnumValForCode(DialogueCharacterId.class,
                getIdFromCharacter(character));
    }

    private static String getIdFromCharacter(DialogueCharacter character) {
        return ID_PREFIX + character.getCode();
    }
    
    public static DialogueCharacterId ofCode(String code) {
        return Stream.of(values()).filter(id -> id.code.equalsIgnoreCase(code))
                .findFirst().orElse(null);
    }
    
}
