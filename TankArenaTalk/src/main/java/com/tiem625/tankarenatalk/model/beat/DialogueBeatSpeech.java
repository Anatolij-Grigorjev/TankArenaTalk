/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.model.beat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tiem625.tankarenatalk.constants.enums.DialogueSpeakerLocation;
import lombok.Data;

/**
 *
 * @author Anatolij
 */
@Data
public class DialogueBeatSpeech {
    @JsonProperty("speaker")
    private DialogueSpeakerLocation speakerLocation;
    @JsonProperty("text")
    private String beatText;
    @JsonProperty("name")
    private String beatSpeakerName;
}
