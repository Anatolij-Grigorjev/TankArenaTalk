/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.constants.timing;

import lombok.Getter;

/**
 *
 * @author Anatolij
 */
public enum DialogueCharacter {

    CLETUS("cletus", "Cletus"),
    LUGNUT("lugnut", "Lugnut");
    
    @Getter
    private final String code;
    @Getter
    private final String displayName;

    private DialogueCharacter(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return code;
    }

    
}
