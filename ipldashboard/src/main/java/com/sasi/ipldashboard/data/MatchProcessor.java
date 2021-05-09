package com.sasi.ipldashboard.data;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.sasi.ipldashboard.model.Match;

public class MatchProcessor implements ItemProcessor<MatchInput, Match> {

    private static final Logger log = LoggerFactory.getLogger(MatchProcessor.class);

    @Override
    public Match process(final MatchInput MatchInput) throws Exception {

	Match match = new Match();

	match.setId(Long.parseLong(MatchInput.getId()));

	match.setCity(MatchInput.getCity());
	match.setDate(LocalDate.parse(MatchInput.getDate()));
	match.setPlayerOfMatch(MatchInput.getPlayerOfMatch());
	match.setVenue(MatchInput.getVenue());
	// set team 1 and team 2 depending on the innings order
	String firstInningsTeam;
	String secondInningsTeam;

	if (MatchInput.getToss_decision().equals("bat")) {
	    firstInningsTeam = MatchInput.getToss_winner();
	    // if team 1 won the toss then second innings is team 2, else second innings is
	    // team 1
	    secondInningsTeam = MatchInput.getToss_winner().equals(MatchInput.getTeam1()) ? MatchInput.getTeam2()
		    : MatchInput.getTeam1();
	} else {
	    secondInningsTeam = MatchInput.getToss_winner();
	    firstInningsTeam = MatchInput.getToss_winner().equals(MatchInput.getTeam1()) ? MatchInput.getTeam2()
		    : MatchInput.getTeam1();
	}
	
	

	match.setTeam1(firstInningsTeam);
	match.setTeam2(secondInningsTeam);

	match.setTossDecision(MatchInput.getToss_decision());
	match.setTossWinner(MatchInput.getToss_winner());
	match.setMatchWinner(MatchInput.getMatch_winner());
	match.setResult(MatchInput.getResult());
	match.setResultMargin(MatchInput.getResult_margin());
	match.setUmpire1(MatchInput.getUmpire1());
	match.setUmpire2(MatchInput.getUmpire2());

	return match;

    }

}
