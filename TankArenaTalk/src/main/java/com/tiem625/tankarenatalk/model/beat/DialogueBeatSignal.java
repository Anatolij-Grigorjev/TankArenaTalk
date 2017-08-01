/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.model.beat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tiem625.tankarenatalk.constants.DialogueSignalType;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Anatolij
 */
@Data
public class DialogueBeatSignal {
    @JsonProperty("signal_type")
    private DialogueSignalType signalType;
    @JsonProperty("signal_params")
    private List<Object> signalParams;
}
