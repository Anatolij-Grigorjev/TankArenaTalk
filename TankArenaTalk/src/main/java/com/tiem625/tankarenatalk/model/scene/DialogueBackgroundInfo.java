/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.model.scene;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tiem625.tankarenatalk.constants.enums.DialogueCharacterId;
import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author Anatolij
 */
@Data
public class DialogueBackgroundInfo {
    
    public static final BigDecimal DEFAULT_CHANGE_TIME = BigDecimal.ONE;
    public static final BigDecimal DEFAULT_START_TIME = new BigDecimal("1.5");
    public static final BigDecimal DEFAULT_END_TIME = new BigDecimal("1.5");
    
    @JsonProperty("background")
    private DialogueCharacterId backgroundImage;
    @JsonProperty("start_time")
    private BigDecimal startTime = DEFAULT_START_TIME;
    @JsonProperty("end_time")
    private BigDecimal endTime = DEFAULT_END_TIME;
    @JsonProperty("change_bg_time")
    private BigDecimal changeTime = DEFAULT_CHANGE_TIME;
    @JsonProperty("actor_left")
    private DialogueActorInfo leftActor = new DialogueActorInfo();
    @JsonProperty("actor_right")
    private DialogueActorInfo rightActor = new DialogueActorInfo();
}
