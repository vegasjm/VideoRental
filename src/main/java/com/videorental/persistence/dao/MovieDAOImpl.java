package com.videorental.persistence.dao;

import com.videorental.persistence.model.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vegasjm on 05/11/2017.
 */
@Service("movieDAOImpl")
public class MovieDAOImpl implements MovieDAO{
    private static Logger logger = Logger.getLogger(MovieDAOImpl.class.getName());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${SQL.GET.MOVIE.BY.ACCOUNTID}")
    private String getMovieByIdSQL;

    @Value("${SQL.GET.ALL.MOVIES}")
    private String getALlMoviesSQL;

    @Override
    public MovieDTO getMovieById(Long id) {
        logger.log(Level.INFO, "MovieDAOImpl - getMovieById id:"+id);
        try {
            Object[] parameters = new Object[] {new Long(id)};
            MovieDTO movie = jdbcTemplate.queryForObject(getMovieByIdSQL, parameters, new BeanPropertyRowMapper<MovieDTO>(MovieDTO.class));
            logger.log(Level.INFO, "MovieDTO:"+movie.toString());
            return movie;
        }catch(Exception e){
            logger.log(Level.SEVERE, "MovieDAOImpl - getMovieById id:"+id+" : "+e.getMessage());
        }
        return null;
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        logger.log(Level.INFO, "MovieDAOImpl - getAllMovies ");
        try {
            Object[] parameters = new Object[] {};
            List<MovieDTO> movies = jdbcTemplate.query(getALlMoviesSQL, parameters, new BeanPropertyRowMapper<MovieDTO>(MovieDTO.class));
            return movies;
        }catch(Exception e){
            logger.log(Level.SEVERE, "MovieDAOImpl - getAllMovies :  "+e.getMessage());
        }
        return null;
    }
}
