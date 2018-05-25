package com.zzzkvidi4.gameserver.model;

import javax.persistence.*;

@Entity
@Table(name = "game_character_status", schema = "game_server")
public class GameCharacterStatus {
    @Id
    @Column(name = "ID_Status")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Status_Name")
    private String name;

    @Column(name = "Status_Display_Name")
    private String displayName;
}
