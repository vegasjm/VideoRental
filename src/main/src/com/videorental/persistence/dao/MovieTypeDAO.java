package com.videorental.persistence.dao;

import com.videorental.persistence.model.MovieTypeDTO;

/**
 * Created by vegasjm on 01/08/2016.
 */
public interface MovieTypeDAO {
    MovieTypeDTO getMovieTypeById(Long id);
}
