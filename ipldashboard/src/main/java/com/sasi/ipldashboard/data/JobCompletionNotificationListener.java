package com.sasi.ipldashboard.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sasi.ipldashboard.model.Team;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    // private final JdbcTemplate jdbcTemplate;

    private final EntityManager entityManager;

    @Autowired
    public JobCompletionNotificationListener(EntityManager entityManager) {
	this.entityManager = entityManager;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
	if (jobExecution.getStatus() == BatchStatus.COMPLETED) {

	    log.info("!!! JOB FINISHED! Time to verify the results");

	    Map<String, Team> teamData = new HashMap<>();

	    // need to store team name and instance of every team from each row

	    // create a query in order to fetch distinct team 1 and team 2
	    /*
	     * entityManager.
	     * createNativeQuery("select team1 from Match UNION select team2 from Match").
	     * getResultList() .stream() .map(e -> new Team((String) e)) .forEach(team ->
	     * teamData.put(((Team) team).getTeamName(), (Team) team));
	     * 
	     * System.out.println(teamData);
	     */

	    // need to get distinct team and new instances for each team

	    entityManager.createQuery("select m.team1, count(*) from Match m group by m.team1", Object[].class)
		    .getResultList()
		    .stream()
		    .map(e -> new Team((String) e[0], (long) e[1]))
		    .forEach(team -> teamData.put(team.getTeamName(), team));
	    
	    System.out.println(teamData);
	    
	    entityManager.createQuery("select m.team2, count(*) from Match m group by m.team2", Object[].class)
	    .getResultList()
	    .stream()
	    .forEach(e -> {
		Team team = teamData.get((String)e[0]);
		team.setTotalMatches(team.getTotalMatches() + (long) e[1]);
	    });
	    
	    
	    
	    entityManager.createQuery("select m.matchWinner, count(*) from Match m group by m.matchWinner", Object[].class)
	    .getResultList()
	    .stream()
	    .forEach(e -> {
		Team team = teamData.get((String)e[0]);
		if(team!=null)
		{
		    team.setTotalWins((long) e[1]);
		}
		
	    });
	    
	    
	    teamData.values().forEach(team -> entityManager.persist(team));
	    teamData.values().forEach(team -> System.out.println((team)));

	}
    }
}