package com.videorental.dao;

import com.videorental.model.MovieDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * Created by vegasjm on 07/11/2017.
 */
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.videorental")
@SpringBootTest
public class MovieDAOImplTest {

    @Autowired
    MovieDAOImpl movieDAO;

    @Test
    public void testGetMovieById(){
        MovieDTO movie = movieDAO.getMovieById(1L);
        assertNotNull(movie);
        assertTrue(movie.getTitle().equals("The Godfather"));

        movie = movieDAO.getMovieById(2L);
        assertNotNull(movie);
        assertTrue(movie.getTitle().equals("The Lord of The Rings"));

        movie = movieDAO.getMovieById(3L);
        assertNotNull(movie);
        assertTrue(movie.getTitle().equals("Forest Gump"));

        movie = movieDAO.getMovieById(4L);
        assertNotNull(movie);
        assertTrue(movie.getTitle().equals("Pulp Fiction"));

        movie = movieDAO.getMovieById(5L);
        assertNotNull(movie);
        assertTrue(movie.getTitle().equals("Spartacus"));
    }
    
    @Test
    public void testGetAllMovies(){
        List<MovieDTO> movies = movieDAO.getAllMovies();
        assertTrue(movies.size()==5);
    }
}
