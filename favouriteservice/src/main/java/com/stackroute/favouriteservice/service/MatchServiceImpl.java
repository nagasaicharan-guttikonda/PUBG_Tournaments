package com.stackroute.favouriteservice.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.domain.Match;
import com.stackroute.favouriteservice.exception.MatchAlreadyExistsException;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;
import com.stackroute.favouriteservice.repository.MatchRepository;

@Service
public class MatchServiceImpl implements MatchService {

	/**
	 * reference of matchRepo object
	 */
	private final transient MatchRepository matchRepo;
	
	/**
	 * initializing the matchRepo
	 * 
	 * @param matchRepo
	 */
	@Autowired
	public MatchServiceImpl(final MatchRepository matchRepo){
		super();
		this.matchRepo=matchRepo;
	}
	
	@Override
	public boolean saveMatch(Match match) throws MatchAlreadyExistsException {
		final Optional<Match> object=matchRepo.findById(match.getmId());
		if(object.isPresent()){
			throw new MatchAlreadyExistsException("Could not save match, Match already present");
			
		}
		matchRepo.save(match);
		return true;
	}

	@Override
	public boolean deleteMatchById(int mId) throws MatchNotFoundException {
		final Match match=matchRepo.findById(mId).orElse(null);
		if(match==null){
			throw new MatchNotFoundException("Could not delete, Match not found!");
		}
		matchRepo.delete(match);
		return true;
	}

	@Override
	public List<Match> getAllMatches(String userId) {
		return matchRepo.findByUserId(userId);

	}

	@Override
	public Match getMatchbyIdAndUser(String id, String userId) {
		return matchRepo.findByIdAndUserId(id, userId);

	}

	@Override
	public Match updateMatch(final Match updateMatch) throws MatchNotFoundException {
		
		final Match match = matchRepo.findById(updateMatch.getmId()).orElse(null);

		if (match == null) {
			throw new MatchNotFoundException("Couldn't update Match . Match not found");
		}
		match.setMapName(updateMatch.getMapName());
		matchRepo.save(match);
		return match;
	}
@Override
	public Match getMatchById(int id) throws MatchNotFoundException {
		final Match Match = matchRepo.findById(id).orElse(null);
		if (Match == null) {
			throw new MatchNotFoundException("Match could not be found");
		}

		return Match;
	}

}
