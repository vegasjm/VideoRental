package com.videorental.dao;

import com.videorental.model.MovieDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * Created by vegasjm on 07/11/2017.
 */
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.videorental")
@SpringBootTest
public class MovieTypeDAOImplTest {

    @Autowired
    MovieTypeDAOImpl movieTypeDAO;

    @Autowired
    MovieDAOImpl movieDAO;

    @Test
    public void testGetMovieTypeFromMovie(){
        MovieDTO movie = movieDAO.getMovieById(1L);
        String movieTypeDesc = movieTypeDAO.getMovieTypeById(movie.getMovieTypeId()).getDescription();
        assertNotNull(movieTypeDesc);
        assertTrue(movie.getTitle().equals("The Godfather") && movieTypeDesc.equals("New release"));

        movie = movieDAO.getMovieById(2L);
        movieTypeDesc = movieTypeDAO.getMovieTypeById(movie.getMovieTypeId()).getDescription();
        assertNotNull(movieTypeDesc);
        assertTrue(movie.getTitle().equals("The Lord of The Rings") && movieTypeDesc.equals("Regular Film"));

        movie = movieDAO.getMovieById(3L);
        movieTypeDesc = movieTypeDAO.getMovieTypeById(movie.getMovieTypeId()).getDescription();
        assertNotNull(movieTypeDesc);
        assertTrue(movie.getTitle().equals("Forest Gump") && movieTypeDesc.equals("New release"));

        movie = movieDAO.getMovieById(4L);
        movieTypeDesc = movieTypeDAO.getMovieTypeById(movie.getMovieTypeId()).getDescription();
        assertNotNull(movieTypeDesc);
        assertTrue(movie.getTitle().equals("Pulp Fiction") && movieTypeDesc.equals("Regular Film"));

        movie = movieDAO.getMovieById(5L);
        movieTypeDesc = movieTypeDAO.getMovieTypeById(movie.getMovieTypeId()).getDescription();
        assertNotNull(movieTypeDesc);
        assertTrue(movie.getTitle().equals("Spartacus") && movieTypeDesc.equals("Old Film"));
    }
}
