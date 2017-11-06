package com.videorental.dao;

import com.videorental.model.MovieTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vegasjm on 05/11/2017.
 */
@Service("movieTypeDAOImpl")
public class MovieTypeDAOImpl implements  MovieTypeDAO{
    private static Logger logger = Logger.getLogger(MovieTypeDAOImpl.class.getName());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${SQL.GET.MOVIETYPE.BY.ID}")
    private String getMovieTypeByIdSQL;

    @Override
    public MovieTypeDTO getMovieTypeById(Long id) {
        logger.log(Level.INFO, "MovieTypeDAOImpl - getMovieTypeById id:"+id);
        try {
            Object[] parameters = new Object[] {new Long(id)};
            MovieTypeDTO mtype = jdbcTemplate.queryForObject(getMovieTypeByIdSQL, parameters, new BeanPropertyRowMapper<MovieTypeDTO>(MovieTypeDTO.class));
            logger.log(Level.INFO, "MovieTypeDTO:"+mtype.toString());
            return mtype;
        }catch(Exception e){
            logger.log(Level.SEVERE, "MovieTypeDAOImpl - getMovieTypeById id:"+id+" : "+e.getMessage());
        }
        return null;
    }
}
