package com.zzzkvidi4.gameserver.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class CriminalPK implements Serializable {
    @Id
    @Column(name = "ID_Criminal")
    private int id;

    @Id
    @Column(name = "ID_Game_Character")
    private int gameCharacterId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGameCharacterId() {
        return gameCharacterId;
    }

    public void setGameCharacterId(int gameCharacterId) {
        this.gameCharacterId = gameCharacterId;
    }
}
