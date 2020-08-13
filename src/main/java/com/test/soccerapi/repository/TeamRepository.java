package com.test.soccerapi.repository;

import com.test.soccerapi.entity.Player;
import com.test.soccerapi.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}
