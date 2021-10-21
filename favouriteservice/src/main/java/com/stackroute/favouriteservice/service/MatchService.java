package com.stackroute.favouriteservice.service;

import java.util.List;

import com.stackroute.favouriteservice.domain.Match;
import com.stackroute.favouriteservice.exception.MatchAlreadyExistsException;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;

public interface MatchService {

	

	public boolean saveMatch(Match match) throws MatchAlreadyExistsException;
	
	boolean deleteMatchById(int mId) throws MatchNotFoundException;
	
	List<Match> getAllMatches(String userId);	

	Match getMatchbyIdAndUser(String id,String userId);

	Match updateMatch(Match match) throws MatchNotFoundException;
	
	Match getMatchById(int Id) throws MatchNotFoundException;

}
