/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.components;

import com.tiem625.tankarenatalk.constants.CodeAwareEnum;
import com.tiem625.tankarenatalk.constants.ConstantsUtils;
import com.tiem625.tankarenatalk.constants.DisplayableEnum;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableListBase;
import javafx.util.StringConverter;

/**
 *
 * @author Anatolij
 */
public class ObservableEnumList<T extends Enum<T>> extends ObservableListBase<T> {

    private T[] values;

    public ObservableEnumList(Class<T> values) {
        super();
        this.values = values.getEnumConstants();
    }

    @Override
    public T get(int index) {
        if (index < size()) {
            return values[index];
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        return values.length;
    }

    public static class Converter<T extends Enum<T>> extends StringConverter<T> {

        //quikc access mapping
        private Map<String, T> enumCache;
        private Class<T> clazz;

        public Converter(Class<T> clazz) {
            enumCache = new HashMap<>();
            this.clazz = clazz;
        }

        @Override
        public String toString(T object) {

            String value = "";
            if (object instanceof DisplayableEnum) {
                value = ((DisplayableEnum) object).getDisplayName();
            } else if (object instanceof CodeAwareEnum) {
                value = ((CodeAwareEnum) object).getCode();
            } else {
                value = object.name();
            }
            enumCache.put(value, object);

            return value;
        }

        @Override
        public T fromString(String string) {
            if (enumCache.containsKey(string)) {
                return enumCache.get(string);
            } else {
                T value = null;
                //try display string
                if (clazz.isAssignableFrom(DisplayableEnum.class)) {
                    value = (T) ConstantsUtils.getEnumValForDisplayText((Class<? extends DisplayableEnum>) clazz, string);
                } else if (clazz.isAssignableFrom(CodeAwareEnum.class)) {
                    value = (T) ConstantsUtils.getEnumValForCode((Class<? extends CodeAwareEnum>) clazz, string);
                } else {
                    value = Arrays.stream(clazz.getEnumConstants()).filter(constant -> constant.name().equals(string)).findFirst().orElse(null);
                }
                if (value == null) {
                    throw new RuntimeException("Unable to find enum constant of class " + clazz.getCanonicalName() + " for string " + string);
                }

                enumCache.put(string, value);
                return value;
            }
        }

    }

}
