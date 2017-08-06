/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.model.beat;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Anatolij
 */
@Data
public class DialogueBeat {
    private DialogueBeatSpeech speech = new DialogueBeatSpeech();
    private List<DialogueBeatSignal> signals = new ArrayList<>();
}
