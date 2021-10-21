package com.stackroute.favouriteservice.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.stackroute.favouriteservice.controller.MatchController;
import com.stackroute.favouriteservice.domain.Match;
import com.stackroute.favouriteservice.service.MatchService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest(MatchController.class)
public class MatchControllerTest
{
	
	@Autowired
	private transient MockMvc mvc;
	
	@InjectMocks
	private MatchController matchController;
	
	@MockBean
	private transient MatchService service;
	
	
	private transient Match match;
	
	static List<Match> matches;
	
	
	@Before
	public void setUp() throws Exception{
		
		MockitoAnnotations.initMocks(this);
		mvc=MockMvcBuilders.standaloneSetup(matchController).build();
		
		matches=new ArrayList<>();
		match=new Match(1,"2341","Pubg-bluehole","normal","erangel","2015-03-23","John123");
		matches.add(match);
		match=new Match(2,"5641","Pubg-bluehole","custom","erangel","2015-03-29","John123");
		matches.add(match);

	}
	
	@Test
	public void testSaveNewMatchSuccess() throws Exception{
		String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJtYW5pc2hqaSIsImlhdCI6MTU1Mzc1Mjk1MX0.qHCCxKTXReMs-st4-FFqlV0ND26R-umxajceCoPDmb2Kexw_2LIb2h0VrgWc4Hig";
		when(service.saveMatch(match)).thenReturn(true);
		mvc.perform(post("/matchservice/match").header("authorization", "Bearer " + token).
				contentType(MediaType.APPLICATION_JSON).content(jsonToString(match)))
				.andExpect(status().isCreated());
		verify(service,times(1)).saveMatch(Mockito.any(Match.class));
		verifyNoMoreInteractions(service);
		
	}
	
	
	@Test
	public void testGetAllMatches() throws Exception {
		String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJtYW5pc2hqaSIsImlhdCI6MTU1Mzc1Mjk1MX0.qHCCxKTXReMs-st4-FFqlV0ND26R-umxajceCoPDmb2Kexw_2LIb2h0VrgWc4Hig";
		
		when(service.getAllMatches("1")).thenReturn(matches);
		mvc.perform(get("/matchservice/match").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		verify(service, times(1)).getAllMatches("manishji");
		verifyNoMoreInteractions(service);
	}
	
	@Test
	public void testUpdateMatchSuccess() throws Exception {
		match.setGameMode("duo");
		when(service.updateMatch(match)).thenReturn(matches.get(0));
		mvc.perform(put("/matchservice/match/{id}", match.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(match))).andExpect(status().isOk());
		verify(service, times(1)).updateMatch(Mockito.any(Match.class));
		verifyNoMoreInteractions(service);
	}

@Test
	public void testGetMatchById() throws Exception {
		when(service.getMatchById(1)).thenReturn(matches.get(0));
		mvc.perform(get("/matchservice/match/{id}", 1)).andExpect(status().isOk());
		verify(service, times(1)).getMatchById(1);
		verifyNoMoreInteractions(service);
	}

	
	//Parsing String format data to JSON
	private static String jsonToString(final Object obj) throws JsonProcessingException {
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent=mapper.writeValueAsString(obj);
			result = jsonContent;
		} catch (JsonProcessingException e) {
			result = "Json processing error";
		}
		return result;
	}

	
	

}
