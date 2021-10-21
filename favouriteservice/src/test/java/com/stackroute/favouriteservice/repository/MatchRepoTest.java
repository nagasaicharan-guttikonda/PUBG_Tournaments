package com.stackroute.favouriteservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.favouriteservice.domain.Match;
import com.stackroute.favouriteservice.repository.MatchRepository;

/**
 * 
 * class to test MovieRepository
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Transactional
public class MatchRepoTest {
	
	/**
	 * referencing MatchRepository object
	 */
	@Autowired
	private transient MatchRepository repo;
	
	public void setRepo(final MatchRepository repo) {
		this.repo=repo;
		}
	
	 @After public void dropdatabase() { repo.deleteAllInBatch(); }
	
	@Test
	public void testSaveMatch() throws Exception{
		repo.save(new Match(1,"2341","Pubg-bluehole","normal","erangel","2015-03-23","John123"));
		final Match movie=repo.findById("2341");
		assertThat(movie.getId()).isEqualTo("2341");
	}
	
	@Test
	public void testDeleteMatch() throws Exception{
		repo.save(new Match(1,"2341","Pubg-bluehole","normal","erangel","2015-03-23","John123"));
		final Match match=repo.findById("2341");
		assertEquals(match.getTitleId(),"Pubg-bluehole");
		
		repo.delete(match);
		
		assertEquals(null,repo.findById("2341"));
		
	}
	
	
	   @Test
       public void testUpdateMovie() throws Exception
       {
       	Match match1=new Match(1, "12", "1234", "duo", "india", "erangal", "new");
                       Match match2=repo.save(match1);
                       final Match match = repo.getOne(match2.getmId());
                       assertEquals(match.getGameMode(),"duo");
                       match.setGameMode("squad");
                       Match match3=repo.save(match);
                       final Match tempMovie = repo.getOne(match3.getmId());
                       assertEquals("squad",tempMovie.getGameMode());
       
       }              

       @Test
       public void testGetMovie() throws Exception
       {
                       Match match1=repo.save(new Match(1, "12", "1234", "duo", "erangal", "erangal", "new"));
                       final Match match = repo.getOne(match1.getmId());
                       assertEquals(match.getMapName(),"erangal");
       }

	@Test
	public void testGetAllMatches() throws Exception{
		repo.save(new Match(1,"2341","Pubg-bluehole1","normal","erangel","2015-03-23","John123"));
		//repo.save(new Match(2,"5432","Pubg-bluehole2","normal","erangel","2015-03-23","John321"));
		repo.save(new Match(2,"2347","Pubg-bluehole3","normal","erangel","2015-03-23","John123"));
		//repo.save(new Match(4,"9876","Pubg-bluehole4","normal","erangel","2015-03-23","John123"));
		
		List<Match> myMovies=repo.findByUserId("John123");
		assertEquals(myMovies.get(0).getTitleId(),"Pubg-bluehole1");
		
	}
	

}
