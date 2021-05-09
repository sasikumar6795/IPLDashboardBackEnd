package com.sasi.ipldashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sasi.ipldashboard.model.Team;
import com.sasi.ipldashboard.repository.TeamRepository;

@RestController
public class TeamController {
    
    @Autowired
    private TeamRepository teamRepository;
    
    
    @GetMapping("/teams/{teamName}")
    public Team getTeam(@PathVariable String teamName)
    {
	return teamRepository.findByTeamName(teamName);
    }

}
