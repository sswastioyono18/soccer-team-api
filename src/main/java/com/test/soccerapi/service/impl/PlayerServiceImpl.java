package com.test.soccerapi.service.impl;

import com.test.soccerapi.entity.Player;
import com.test.soccerapi.repository.PlayerRepository;
import com.test.soccerapi.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames={"teamName"})
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    @Cacheable
    public List<Player> findByTeamName(String teamName) {
        return playerRepository.findByTeam_TeamName(teamName);
    }


    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

}
