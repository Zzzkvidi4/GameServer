package com.zzzkvidi4.gameserver.utils;

import com.zzzkvidi4.gameserver.model.Criminal;

public class UnproxyUtils {
    public static Criminal unproxy(Criminal criminal, boolean withCharacter, boolean withStatus){
        Criminal tmpCriminal = new Criminal(criminal);

        if (withCharacter){
            tmpCriminal.setGameCharacter(criminal.getGameCharacter());
        }

        if (withStatus){
            tmpCriminal.setStatus((criminal.getStatus()));
        }
        return tmpCriminal;
    }
}
