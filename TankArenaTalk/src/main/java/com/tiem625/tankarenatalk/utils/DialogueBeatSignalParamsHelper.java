/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiem625.tankarenatalk.utils;

import com.tiem625.tankarenatalk.components.EnumChoiceBox;
import com.tiem625.tankarenatalk.components.PositiveDecimalInputField;
import com.tiem625.tankarenatalk.constants.enums.DialogueBeatSignalModelActionTypes;
import com.tiem625.tankarenatalk.constants.enums.DialogueBeatSignalType;
import com.tiem625.tankarenatalk.constants.enums.DialogueCharacterId;
import com.tiem625.tankarenatalk.model.beat.DialogueBeatSignal;
import com.tiem625.tankarenatalk.model.scene.DialogueBackgroundInfo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
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
    
    public Node paramsView(DialogueBeatSignalType signalType) {
        return signalParamsPanes.get(signalType).getView();
    }
    
    public void setValue(DialogueBeatSignal signal) {
        if (signal != null) {
            signalParamsPanes.get(signal.getSignalType()).setValue(signal);
        }
    }

    private DialogueBeatSignalParamsHelper() {

        signalParamsPanes = new HashMap<>();
        Stream.of(DialogueBeatSignalType.LEFT_ACTION, DialogueBeatSignalType.RIGHT_ACTION).forEach(signalType -> {
            
            signalParamsPanes.put(signalType, new ModelActionSignalParamsPane());
            
        });
        Stream.of(DialogueBeatSignalType.LEFT_CHANGE_MODEL, DialogueBeatSignalType.RIGHT_CHANGE_MODEL).forEach(signalType -> {

            signalParamsPanes.put(signalType, new ChangeModelSignalParamsPane());
            
        });
        
        signalParamsPanes.put(DialogueBeatSignalType.CHANGE_BACKGROUND, new ChangeBackgroundSignalParamsPane());
    }

    public static abstract class DialogueBeatSignalParamsPane {

        @Getter
        private final Node view;

        public DialogueBeatSignalParamsPane() {
            view = initializeView();
        }

        protected final  Node initializeView() {
            
            GridPane rootGrid = new GridPane();
            rootGrid.setHgap(5.0);
            rootGrid.setVgap(5.0);
            
            decorateGridView(rootGrid);
            
            return rootGrid;
        }

        protected abstract void decorateGridView(GridPane rootGrid);

        public abstract void setValue(DialogueBeatSignal signal);

    }

    public static class ModelActionSignalParamsPane extends DialogueBeatSignalParamsPane {

        private EnumChoiceBox<DialogueBeatSignalModelActionTypes> actionTypeChoice;
        private ChangeListener<DialogueBeatSignalModelActionTypes> actionTypesChoiceBinding;
        private CheckBox requireVisibleCheck;
        private ChangeListener<Boolean> requireVisibleCheckBinding;

        @Override
        protected void decorateGridView(GridPane rootGrid) {
            actionTypeChoice = new EnumChoiceBox<>(DialogueBeatSignalModelActionTypes.class);
            rootGrid.addRow(0, new Label("Action type: "), actionTypeChoice);
            requireVisibleCheck = new CheckBox();
            rootGrid.addRow(1, new Label("Actor must be visible: "), requireVisibleCheck);
        }

        @Override
        public void setValue(DialogueBeatSignal signal) {

            ModelAdapter.replaceListenerIfExists(actionTypesChoiceBinding, (obs, o, n) -> {
                
                if (signal != null && n != null) {
                    if (signal.getSignalParams() == null || signal.getSignalParams().isEmpty()) {
                        signal.setSignalParams(new ArrayList<>());
                        //this will require first object being 
                        //present for replacement
                        signal.getSignalParams().add("");
                    }
                    signal.getSignalParams().set(0, n.getCode());
                }
                
            }, actionTypeChoice.valueProperty());
            actionTypeChoice.setValue(signal != null? 
                    DialogueBeatSignalModelActionTypes.ofCode((String)ModelAdapter.nthOrNull(signal.getSignalParams(), 0)) 
                    : null);
            
            ModelAdapter.replaceListenerIfExists(requireVisibleCheckBinding, (obs, o, n) -> {
                
                if (signal != null && n != null) {
                    if (signal.getSignalParams() == null || signal.getSignalParams().isEmpty()) {
                        signal.setSignalParams(new ArrayList<>());
                        //this will require first object being 
                        //present for replacement
                        signal.getSignalParams().add("");
                    }
                    if (signal.getSignalParams().size() < 2) {
                        signal.getSignalParams().add(true);
                    }
                    signal.getSignalParams().set(1, n);
                }
                
            }, requireVisibleCheck.selectedProperty());
            Object paramO = signal != null? 
                    ModelAdapter.nthOrNull(signal.getSignalParams(), 1) : false;
            boolean param = paramO != null? 
                    (boolean)paramO : false;
            requireVisibleCheck.setSelected(param);
 
        }

    }
    
    
    
    public static class ChangeModelSignalParamsPane extends DialogueBeatSignalParamsPane {
        
        private EnumChoiceBox<DialogueCharacterId> characterModelChoice;
        private ChangeListener<DialogueCharacterId> characterModelChoiceBinding;

        @Override
        protected void decorateGridView(GridPane rootGrid) {
            
            characterModelChoice = new EnumChoiceBox<>(DialogueCharacterId.class);            
            rootGrid.addRow(0, new Label("New character model: "), characterModelChoice);
            
            
            
        }

        @Override
        public void setValue(DialogueBeatSignal signal) {
            
            ModelAdapter.replaceListenerIfExists(characterModelChoiceBinding, (obs, o, n) -> {
                
                if (signal != null && n != null) {
                    if (signal.getSignalParams() == null || signal.getSignalParams().isEmpty()) {
                        signal.setSignalParams(new ArrayList<>());
                        //this will require first object being 
                        //present for replacement
                        signal.getSignalParams().add("");
                    }
                    signal.getSignalParams().set(0, n.getCode());
                }
                
            }, characterModelChoice.valueProperty());
            
            characterModelChoice.setValue(signal != null? 
                    DialogueCharacterId.ofCode((String)ModelAdapter.nthOrNull(signal.getSignalParams(), 0))
                    : null);
            
        }
        
    }
    
    
    public static class ChangeBackgroundSignalParamsPane extends DialogueBeatSignalParamsPane {
        
        private EnumChoiceBox<DialogueCharacterId> characterBackgroundChoice;
        private ChangeListener<DialogueCharacterId> characterBackgroundChoiceBinding;
        private PositiveDecimalInputField backgroundChangeTime;
        private ChangeListener<BigDecimal> backgroundChangeTimeBinding;

        @Override
        protected void decorateGridView(GridPane rootGrid) {
          
            characterBackgroundChoice = new EnumChoiceBox<>(DialogueCharacterId.class);            
            rootGrid.addRow(0, new Label("New background model: "), characterBackgroundChoice);
            backgroundChangeTime = new PositiveDecimalInputField(DialogueBackgroundInfo.DEFAULT_CHANGE_TIME);
            rootGrid.addRow(1, new Label("Background change time: "), backgroundChangeTime);
            
        }

        @Override
        public void setValue(DialogueBeatSignal signal) {
            
            ModelAdapter.replaceListenerIfExists(characterBackgroundChoiceBinding, (obs, o, n) -> {
                    
                if (signal != null && n != null) {
                    if (signal.getSignalParams() == null || signal.getSignalParams().isEmpty()) {
                        signal.setSignalParams(new ArrayList<>());
                        //this will require first object being 
                        //present for replacement
                        signal.getSignalParams().add("");
                    }
                    signal.getSignalParams().set(0, n.getCode());
                }
                
            }, characterBackgroundChoice.valueProperty());
            characterBackgroundChoice.setValue(signal != null? 
                    DialogueCharacterId.ofCode((String)ModelAdapter.nthOrNull(signal.getSignalParams(), 0))
                    : null);
            
            ModelAdapter.replaceListenerIfExists(backgroundChangeTimeBinding, (obs, o, n) -> {
            
                if (signal != null && n != null) {
                    if (signal.getSignalParams() == null || signal.getSignalParams().isEmpty()) {
                        signal.setSignalParams(new ArrayList<>());
                        //this will require first object being 
                        //present for replacement
                        signal.getSignalParams().add("");
                    }
                    if (signal.getSignalParams().size() < 2) {
                        signal.getSignalParams().add(BigDecimal.ZERO);
                    }
                    signal.getSignalParams().set(1, n);
                }
                
            }, backgroundChangeTime.valueProperty());
            backgroundChangeTime.setValue(signal != null? 
                    (BigDecimal)ModelAdapter.nthOrNull(signal.getSignalParams(), 1) : null);
        }
        
    }

}
