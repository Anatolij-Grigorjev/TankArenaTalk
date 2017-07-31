/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.constants;

import java.util.Arrays;

/**
 *
 * @author Anatolij
 */
public class ConstantsUtil {
    
   private ConstantsUtil() {}
    
    /**
     * Fetch enum value by JSON code, relying on the enum to override the toString method
     * if nothing is found, an exception is thrown
     * @param <T>
     * @param values - the enum values class to search through
     * @param code - the code to search for an enum value against
     * @return
     */
    public static <T extends Enum<T>> T getEnumValForCode(Class<T> values, String code) {
       return Arrays.stream(values.getEnumConstants())
                .filter(pos -> pos.toString().equals(code))
                .findFirst().orElseThrow(() -> new RuntimeException(
                        "Cant find member of " + values.getName() + " for code " + code)
                        );
   }
    
}
