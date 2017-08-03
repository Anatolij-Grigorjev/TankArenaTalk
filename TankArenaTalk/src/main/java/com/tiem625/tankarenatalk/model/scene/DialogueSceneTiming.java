/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.model.scene;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tiem625.tankarenatalk.constants.enums.timing.DialogueArena;
import com.tiem625.tankarenatalk.constants.enums.timing.DialogueCharacter;
import com.tiem625.tankarenatalk.constants.enums.timing.DialoguePosition;
import lombok.Data;

/**
 *
 * @author Anatolij
 */
@Data
public class DialogueSceneTiming {
    @JsonProperty("level")
    private DialogueArena dialogueArena;
    @JsonProperty("character")
    private DialogueCharacter dialogueCharacter;
    @JsonProperty("position")
    private DialoguePosition dialoguePosition;
}
