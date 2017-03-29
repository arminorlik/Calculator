package com.example.armin.calculator;

/**
 * Created by Armin on 2017-03-28.
 */

public enum Operation {
    NONE(""), ADD("+"), SUBSTRACT("-");

    private final String key;

    private Operation(String key){
        this.key = key;
    }

    public static Operation operationFromKey (String key){
        for (Operation operation : values()) {
            if(operation.key.equals(key))
                return operation;
        }
        return NONE;
    }

}

