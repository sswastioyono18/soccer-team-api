package com.test.soccerapi.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "player_id", nullable = false)
    private Long playerId;

    @Column(name = "player_no", nullable = false)
    private String playerNo;

    @Column(name = "player_name", nullable = false)
    private String playerName;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
