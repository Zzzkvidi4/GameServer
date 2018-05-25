package com.zzzkvidi4.gameserver.model;

public class CharacterWithTargetValue {
    private GameCharacter character;
    private int targetValue;

    public CharacterWithTargetValue(GameCharacter character, int targetValue){
        this.character = character;
        this.targetValue = targetValue;
    }

    public GameCharacter getCharacter() {
        return character;
    }

    public int getTargetValue() {
        return targetValue;
    }
}
