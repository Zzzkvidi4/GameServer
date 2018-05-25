package com.zzzkvidi4.gameserver.model;

import com.zzzkvidi4.gameserver.evolution.GreyCode;

import javax.persistence.*;

@Entity
@Table(name = "game_character", schema = "game_server")
public class GameCharacter {
    @Id
    @Column(name = "ID_Game_Character")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Intellijence")
    private int intellijence;

    @Column(name = "Charysma")
    private int charysma;

    @Column(name = "Strength")
    private int strength;

    @Column(name = "Leader")
    private int leader;

    @Column(name = "Success")
    private int success;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIntellijence() {
        return intellijence;
    }

    public void setIntellijence(int intellijence) {
        this.intellijence = intellijence;
    }

    public int getCharysma() {
        return charysma;
    }

    public void setCharysma(int charysma) {
        this.charysma = charysma;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getLeader() {
        return leader;
    }

    public void setLeader(int leader) {
        this.leader = leader;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    @Transient
    public StringBuilder getGene(){
        StringBuilder gene = new StringBuilder();
        gene
                .append(GreyCode.toGrey(getIntellijence()))
                .append(GreyCode.toGrey(getCharysma()))
                .append(GreyCode.toGrey(getStrength()))
                .append(GreyCode.toGrey(getLeader()))
                .append(GreyCode.toGrey(getSuccess()));
        return gene;
    }
}
