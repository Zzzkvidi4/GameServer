package com.zzzkvidi4.gameserver.service;

import com.zzzkvidi4.gameserver.model.GameCharacter;
import com.zzzkvidi4.gameserver.response.Error;

import java.util.List;

public interface CharacterService extends Service<GameCharacter, Integer> {
    public List<Error> createCharacter(int intellijence, int charysma, int strength, int leader, int success);
}
