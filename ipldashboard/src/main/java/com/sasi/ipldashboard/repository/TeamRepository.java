package com.sasi.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository;

import com.sasi.ipldashboard.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
    
    Team findByTeamName(String teamName);

}
