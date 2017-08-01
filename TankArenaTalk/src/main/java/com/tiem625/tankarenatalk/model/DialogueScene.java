/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.model;

import com.tiem625.tankarenatalk.model.scene.DialogueBackgroundInfo;
import com.tiem625.tankarenatalk.model.scene.DialogueSceneTiming;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *
 * @author Anatolij
 */
@Data
public class DialogueScene {
    private String id;
    private String name;
    private DialogueSceneTiming timing;
    @JsonProperty("scene")
    private DialogueBackgroundInfo backgroundInfo;
    
}
