package com.test.soccerapi.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
}
