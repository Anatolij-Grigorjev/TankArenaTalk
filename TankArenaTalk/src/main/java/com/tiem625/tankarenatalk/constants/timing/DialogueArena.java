/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.constants.timing;

import com.tiem625.tankarenatalk.constants.CodeAwareEnum;
import lombok.Getter;

/**
 *
 * @author Anatolij
 */
public enum DialogueArena implements CodeAwareEnum {
    SANDY_GRAVEYARD("map_sandy_graveyard", "Sandy Graveyard"),
    GRASSY_KNOLL("map_grassy_knoll", "Grassy Knoll"),
    SANDY_ROCK("map_sandy_rock", "Sandy Rock");
    
    @Getter
    private final String code;
    @Getter
    private final String displayName;
    
    private DialogueArena(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
    
    
}
