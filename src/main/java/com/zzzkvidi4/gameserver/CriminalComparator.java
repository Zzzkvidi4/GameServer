package com.zzzkvidi4.gameserver;

import com.zzzkvidi4.gameserver.model.CharacterWithTargetValue;

import java.util.Comparator;

public class CriminalComparator implements Comparator<CharacterWithTargetValue> {
    @Override
    public int compare(CharacterWithTargetValue o1, CharacterWithTargetValue o2) {
        return o1.getTargetValue() - o2.getTargetValue();
    }
}
