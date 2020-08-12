package com.test.soccerapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "team")
public class Team {

    @Id
    @Column(name = "team_id", nullable = false)
    private Long teamId;
    @Column(name = "team_name", nullable = false)
    private String teamName;

    @OneToMany(mappedBy = "team")
    private Set<Player> players = new HashSet<>();
}
