package com.zzzkvidi4.gameserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

/**
 * Created by Роман on 24.05.2018.
 */
public class HelloWorld {
    private int value;

    public int getValue(){
        return value;
    }

    public HelloWorld(int value){
        this.value = value;
    }
}
