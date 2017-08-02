/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author Anatolij
 */
public interface CodeAwareEnum {
    
    String getCode();
    
    @JsonValue
    default String toValue() {
        return getCode();
    }
    
    @JsonCreator
    default <T extends CodeAwareEnum> T forCode(String code) {
        return ConstantsUtils.getEnumValForCode((Class<T>)this.getClass(), code);
    }
    
}
