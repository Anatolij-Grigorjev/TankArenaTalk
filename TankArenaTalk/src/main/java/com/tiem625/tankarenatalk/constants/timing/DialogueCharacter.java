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

    CLETUS("cletus"),
    LUGNUT("lugnut");
    
    @Getter
    private final String code;

    private DialogueCharacter(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

    
}
