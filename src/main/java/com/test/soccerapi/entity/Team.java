package com.test.soccerapi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString
@Table(name = "team")
public class Team {

    @Id
    @Column(name = "team_id", nullable = false)
    private Long id;
    @Column(name = "team_name", nullable = false)
    private String teamName;

    @OneToMany(mappedBy = "team", fetch=FetchType.LAZY, cascade=CascadeType.ALL, targetEntity = Player.class)
    private List<Player> players;
}
