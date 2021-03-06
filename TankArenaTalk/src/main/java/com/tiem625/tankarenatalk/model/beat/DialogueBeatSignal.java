/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.model.beat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tiem625.tankarenatalk.constants.enums.DialogueBeatSignalType;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Anatolij
 */
@Data
public class DialogueBeatSignal {
    @JsonProperty("signal_type")
    private DialogueBeatSignalType signalType;
    @JsonProperty("signal_params")
    private List<Object> signalParams = new ArrayList<>();
}
