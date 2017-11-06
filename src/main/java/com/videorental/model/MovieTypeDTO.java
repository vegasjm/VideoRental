package com.videorental.model;

import javax.persistence.Column;

/**
 * Created by vegasjm on 05/11/2017.
 */
public class MovieTypeDTO {

    @Column(name="ID")
    private Long id;

    @Column(name="DESCRIPTION")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MovieTypeDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
