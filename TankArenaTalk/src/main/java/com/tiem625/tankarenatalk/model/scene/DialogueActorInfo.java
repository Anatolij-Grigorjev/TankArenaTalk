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
public class DialogueActorInfo {
    
    public static final BigDecimal DEFAULT_CHANGE_MODEL_TIME = new BigDecimal("1.5");
    public static final BigDecimal DEFAULT_DIM_TIME = new BigDecimal("0.7");
    public static final BigDecimal DEFAULT_ACTOR_MOVE_TIME = new BigDecimal("1.5");
    
    
    @JsonProperty("char_model")
    private DialogueCharacterId characterModel;
    @JsonProperty("name")
    private String actorName;
    @JsonProperty("change_model_time")
    private BigDecimal changeModelTime = DEFAULT_CHANGE_MODEL_TIME;
    @JsonProperty("dim_time")
    private BigDecimal actorDimTime = DEFAULT_DIM_TIME;
    @JsonProperty("move_time")
    private BigDecimal actorMoveTime = DEFAULT_ACTOR_MOVE_TIME;
}
