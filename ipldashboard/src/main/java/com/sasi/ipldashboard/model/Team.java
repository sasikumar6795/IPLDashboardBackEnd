package com.sasi.ipldashboard.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Team {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String teamName;
    private long totalMatches;
    private long totalWins;
    
    public Team()
    {
	
    }
    

    public Team(String teamName) {
	super();
	this.teamName = teamName;
    }


    public Team(long totalMatches) {
	super();
	this.totalMatches = totalMatches;
    }

    public Team(String teamName, long totalMatches) {
	super();
	this.teamName = teamName;
	this.totalMatches = totalMatches;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getTeamName() {
	return teamName;
    }

    public void setTeamName(String teamName) {
	this.teamName = teamName;
    }

    public long getTotalMatches() {
	return totalMatches;
    }

    public void setTotalMatches(long totalMatches) {
	this.totalMatches = totalMatches;
    }

    public long getTotalWins() {
	return totalWins;
    }

    public void setTotalWins(long totalWins) {
	this.totalWins = totalWins;
    }


    @Override
    public String toString() {
	return "Team [id=" + id + ", teamName=" + teamName + ", totalMatches=" + totalMatches + ", totalWins="
		+ totalWins + "]";
    }
    
    

}
