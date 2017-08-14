/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.utils;

import com.tiem625.tankarenatalk.components.EnumChoiceBox;
import com.tiem625.tankarenatalk.constants.enums.DialogueBeatSignalModelActionTypes;
import com.tiem625.tankarenatalk.constants.enums.DialogueBeatSignalType;
import com.tiem625.tankarenatalk.model.beat.DialogueBeatSignal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import lombok.Getter;

/**
 *
 * @author Tiem625
 */
public class DialogueBeatSignalParamsHelper {

    private static DialogueBeatSignalParamsHelper instance;

    private final Map<DialogueBeatSignalType, DialogueBeatSignalParamsPane> signalParamsPanes;

    public static DialogueBeatSignalParamsHelper getInstance() {
        if (instance == null) {
            instance = new DialogueBeatSignalParamsHelper();
        }
        return instance;
    }

    private DialogueBeatSignalParamsHelper() {

        signalParamsPanes = new HashMap<>();
        Stream.of(DialogueBeatSignalType.LEFT_ACTION, DialogueBeatSignalType.RIGHT_ACTION).forEach(signalType -> {
            
        });
        Stream.of(DialogueBeatSignalType.LEFT_CHANGE_MODEL, DialogueBeatSignalType.RIGHT_CHANGE_MODEL).forEach(signalType -> {
            
        });
    }
    
    public static abstract class DialogueBeatSignalParamsPane {
        
        @Getter
        private final Node view;
        
        public DialogueBeatSignalParamsPane() {
            view = initializeView();
        }

        protected abstract Node initializeView();
        
        public abstract void setValue(DialogueBeatSignal signal);
    }
    
    
    public static class ModelActionSignalParamsPane extends DialogueBeatSignalParamsPane {
        
        private EnumChoiceBox<DialogueBeatSignalModelActionTypes> actionTypeChoice;
        private ChangeListener<DialogueBeatSignalModelActionTypes> actionTypesChoiceBinding;
        private CheckBox requireVisibleCheck;
        private ChangeListener<Boolean> requireVisibleCheckBinding;

        @Override
        protected Node initializeView() {
            
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setValue(DialogueBeatSignal signal) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }

}
