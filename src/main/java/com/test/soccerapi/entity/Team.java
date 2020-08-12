package com.test.soccerapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString
@Table(name = "team")
public class Team {

    @Id
    @Column(name = "team_id", nullable = false)
    private Long teamId;
    @Column(name = "team_name", nullable = false)
    private String teamName;

    @OneToMany(mappedBy = "team", cascade=CascadeType.ALL)
    @JsonIgnore
    private List<Player> players;
}
