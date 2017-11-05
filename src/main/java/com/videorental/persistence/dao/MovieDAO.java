package com.videorental.persistence.dao;

import com.videorental.persistence.model.MovieDTO;

import java.util.List;

/**
 * Created by vegasjm on 05/11/2017.
 */
public interface MovieDAO {
    MovieDTO getMovieById(Long id);
    List<MovieDTO> getAllMovies();
}
