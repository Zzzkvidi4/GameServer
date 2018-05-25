package com.zzzkvidi4.gameserver.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "criminal", schema = "game_server")
@IdClass(CriminalPK.class)
public class Criminal {
    public Criminal(){

    }

    public Criminal(Criminal criminal){
        this.id = criminal.id;
        this.birthday = criminal.birthday;
        this.bossId = criminal.bossId;
        this.gameCharacterId = criminal.gameCharacterId;
        this.name = criminal.name;
        this.surname = criminal.surname;
        this.statusId = criminal.statusId;
    }

    @Id
    @Column(name = "ID_Criminal")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Surname")
    private String surname;

    @Column(name = "Birthday")
    private Date birthday;

    @Column(name = "ID_Boss_Criminal")
    private int bossId;

    @Id
    @Column(name = "ID_Game_Character")
    private int gameCharacterId;

    @ManyToOne
    @JoinColumn(name = "ID_Game_Character", nullable = false, updatable = false, insertable = false)
    private GameCharacter gameCharacter;

    @Column(name = "ID_Status")
    private int statusId;

    @ManyToOne
    @JoinColumn(name = "ID_Status", nullable = false, updatable = false, insertable = false)
    private GameCharacterStatus status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getBossId() {
        return bossId;
    }

    public void setBossId(int bossId) {
        this.bossId = bossId;
    }

    public int getGameCharacterId() {
        return gameCharacterId;
    }

    public void setGameCharacterId(int gameCharacterId) {
        this.gameCharacterId = gameCharacterId;
    }

    public GameCharacter getGameCharacter() {
        return gameCharacter;
    }

    public void setGameCharacter(GameCharacter gameCharacter) {
        this.gameCharacter = gameCharacter;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public GameCharacterStatus getStatus() {
        return status;
    }

    public void setStatus(GameCharacterStatus status) {
        this.status = status;
    }
}
