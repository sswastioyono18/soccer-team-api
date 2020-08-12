package com.test.soccerapi.repository;

import com.test.soccerapi.entity.Player;
import com.test.soccerapi.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TeamRepository extends JpaRepository<Team, String> {
    List<Player> findByTeamId(String teamId);
}
