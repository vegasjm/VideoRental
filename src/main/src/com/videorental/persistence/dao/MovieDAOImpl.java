package com.videorental.persistence.dao;

import com.videorental.persistence.model.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vegasjm on 02/08/2016.
 */
@Service("movieDAOImpl")
public class MovieDAOImpl implements MovieDAO{
    private static Logger logger = Logger.getLogger(MovieDAOImpl.class.getName());

    @Autowired
    private DataSource dataSource;

    @Value("${SQL.GET.MOVIE.BY.ACCOUNTID}")
    private String getMovieByIdSQL;

    @Value("${SQL.GET.ALL.MOVIES}")
    private String getALlMoviesSQL;

    @Override
    public MovieDTO getMovieById(Long id) {
        logger.log(Level.INFO, "MovieDAOImpl - getMovieById id:"+id);
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(getMovieByIdSQL);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                MovieDTO movie = new MovieDTO();
                movie.setId(rs.getLong("ID"));
                movie.setDescription(rs.getString("DESCRIPTION"));
                movie.setMovieTypeId(rs.getLong("MOVIETYPEID"));
                movie.setTitle(rs.getString("TITLE"));
                connection.close();
                statement.close();
                rs.close();
                return movie;
            }
        }catch(Exception e){
            logger.log(Level.SEVERE, "MovieDAOImpl - getMovieById id:"+id+" : "+e.getMessage());
        }
        return null;
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        logger.log(Level.INFO, "MovieDAOImpl - getAllMovies ");
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(getALlMoviesSQL);
            ResultSet rs = statement.executeQuery();
            List<MovieDTO> movies = new ArrayList<MovieDTO>();
            while(rs.next()){
                MovieDTO movie = new MovieDTO();
                movie.setId(rs.getLong("ID"));
                movie.setDescription(rs.getString("DESCRIPTION"));
                movie.setMovieTypeId(rs.getLong("MOVIETYPEID"));
                movie.setTitle(rs.getString("TITLE"));
                movies.add(movie);
            }
            connection.close();
            statement.close();
            rs.close();
            return movies;
        }catch(Exception e){
            logger.log(Level.SEVERE, "MovieDAOImpl - getAllMovies :  "+e.getMessage());
        }
        return null;
    }
}
