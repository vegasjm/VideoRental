package com.videorental.dao;

import com.videorental.model.MovieTypeDTO;

/**
 * Created by vegasjm on 05/11/2017.
 */
public interface MovieTypeDAO {
    MovieTypeDTO getMovieTypeById(Long id);
}
