package com.videorental.persistence.dao;

import com.videorental.persistence.model.MovieTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vegasjm on 05/11/2017.
 */
@Service("movieTypeDAOImpl")
public class MovieTypeDAOImpl implements  MovieTypeDAO{
    private static Logger logger = Logger.getLogger(MovieTypeDAOImpl.class.getName());

    @Autowired
    private DataSource dataSource;

    @Value("${SQL.GET.MOVIETYPE.BY.ID}")
    private String getMovieTypeByIdSQL;

    @Override
    public MovieTypeDTO getMovieTypeById(Long id) {
        logger.log(Level.INFO, "MovieTypeDAOImpl - getMovieTypeById id:"+id);
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(getMovieTypeByIdSQL);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                MovieTypeDTO mtype = new MovieTypeDTO();
                mtype.setId(rs.getLong("ID"));
                mtype.setDescription(rs.getString("DESCRIPTION"));
                connection.close();
                statement.close();
                rs.close();
                return mtype;
            }
        }catch(Exception e){
            logger.log(Level.SEVERE, "MovieTypeDAOImpl - getMovieTypeById id:"+id+" : "+e.getMessage());
        }
        return null;
    }
}
