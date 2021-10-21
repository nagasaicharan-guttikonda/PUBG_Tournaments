package com.stackroute.favouriteservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.domain.Match;
import com.stackroute.favouriteservice.exception.MatchAlreadyExistsException;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;
import com.stackroute.favouriteservice.service.MatchService;

import io.jsonwebtoken.Jwts;

@RestController
@CrossOrigin
@RequestMapping("/matchservice")
public class MatchController {

	private MatchService matchService;

	@Autowired
	public MatchController(MatchService matchService) {

		this.matchService = matchService;
	}

	@PostMapping("/match")
	public ResponseEntity<?> saveNewMatch(@RequestBody final Match match,final HttpServletRequest request,
			final HttpServletResponse response)  {
		ResponseEntity<?> responseEntity;
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		try {
			match.setUserId(userId);
			matchService.saveMatch(match);
			responseEntity = new ResponseEntity<Match>(match, HttpStatus.CREATED);
		} catch (MatchAlreadyExistsException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}",
					HttpStatus.CONFLICT);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;

	}

	@DeleteMapping(path="/match/{id}/{userId}")
	public ResponseEntity<?> deleteMatchById(@PathVariable("id") final String id,@PathVariable("userId") final String userId){
		ResponseEntity<?> responseEntity;
		Match match=matchService.getMatchbyIdAndUser(id,userId);
		try {
			matchService.deleteMatchById(match.getmId());
		}catch (MatchNotFoundException e) {
			responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		responseEntity=new ResponseEntity<String>("movie deleted successfully",HttpStatus.OK);
		return responseEntity;
		
		}


	@PutMapping(path = "/match/{id}")
	public ResponseEntity<?> updateMatch(@PathVariable("id") final Integer id, @RequestBody Match match) {
		ResponseEntity<?> responseEntity;
		try {
			final Match fetchedMatch = matchService.updateMatch(match);
			responseEntity = new ResponseEntity<Match>(fetchedMatch, HttpStatus.OK);
		} catch (MatchNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}",
					HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@GetMapping(path = "/match/{id}")
	public ResponseEntity<?> getMatchById(@PathVariable("id") final int id) {
		ResponseEntity<?> responseEntity;
		try {
			Match match = matchService.getMatchById(id);
			responseEntity = new ResponseEntity<Match>(match, HttpStatus.OK);
		} catch (MatchNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}",
					HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@GetMapping("/match")
	public ResponseEntity<?> getAllMatches(final HttpServletRequest request, final HttpServletResponse response) {
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		List<Match> matches = matchService.getAllMatches(userId);

		return new ResponseEntity<List<Match>>(matches, HttpStatus.OK);

	}

}
