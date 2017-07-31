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
public enum DialogueArena {
    SANDY_GRAVEYARD("map_Sandy_graveyard"),
    GRASSY_KNOLL("map_grassy_knoll"),
    SANDY_ROCK("map_sandy_rock");
    
    @Getter
    private final String code;
    
    private DialogueArena(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
    
    
}
