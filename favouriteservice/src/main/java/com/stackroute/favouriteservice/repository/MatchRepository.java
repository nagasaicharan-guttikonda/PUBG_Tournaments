package com.stackroute.favouriteservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.favouriteservice.domain.Match;

public interface MatchRepository extends JpaRepository<Match, Integer>{
	
	List<Match> findByUserId(String userId);
	
	Match findByIdAndUserId(String id,String userId);

	Match findById(String id);
}
