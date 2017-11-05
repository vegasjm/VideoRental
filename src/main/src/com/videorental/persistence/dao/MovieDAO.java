package com.videorental.persistence.dao;

import com.videorental.persistence.model.MovieDTO;

import java.util.List;

/**
 * Created by vegasjm on 01/08/2016.
 */
public interface MovieDAO {
    MovieDTO getMovieById(Long id);
    List<MovieDTO> getAllMovies();
}
