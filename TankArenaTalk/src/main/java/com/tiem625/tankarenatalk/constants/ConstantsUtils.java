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
public class ConstantsUtils {

    private ConstantsUtils() {
    }

    /**
     * Fetch enum value by JSON code if nothing is found, an exception is thrown
     *
     * @param <T>
     * @param values - the enum values class to search through
     * @param code - the code to search for an enum value against
     * @return
     */
    public static <T extends CodeAwareEnum> T getEnumValForCode(Class<T> values, String code) {
        return Arrays.stream(values.getEnumConstants())
                .filter(pos -> pos.getCode().equals(code))
                .findFirst().orElse(null);
    }

    /**
     * Fetch enum value by GUI display text if nothing is found, an exception is
     * thrown
     *
     * @param <T>
     * @param values - the enum values class to search through
     * @param displayText - the dispalyText to search for an enum value against
     * @return
     */
    public static <T extends DisplayableEnum> T getEnumValForDisplayText(Class<T> values, String displayText) {
        return Arrays.stream(values.getEnumConstants())
                .filter(pos -> pos.getDisplayName().equals(displayText))
                .findFirst().orElse(null);
    }

}
