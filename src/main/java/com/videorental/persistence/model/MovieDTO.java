package com.videorental.persistence.model;

import javax.persistence.Column;

/**
 * Created by vegasjm on 05/11/2017.
 */
public class MovieDTO {

    @Column(name="ID")
    private Long id;

    @Column(name="MOVIETYPEID")
    private Long movieTypeId;

    @Column(name="TITLE")
    private String title;

    @Column(name="DESCRIPTION")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMovieTypeId() {
        return movieTypeId;
    }

    public void setMovieTypeId(Long movieTypeId) {
        this.movieTypeId = movieTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "id=" + id +
                ", movieTypeId=" + movieTypeId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
