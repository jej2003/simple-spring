package org.superbiz.injection.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Movie implements Serializable {

    @Id @GeneratedValue
    private long id;

    private String director;
    private String title;
    private int year;

    public Movie() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Movie(String director, String title, int year) {
        this.director = director;
        this.title = title;
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString(){
    	StringBuilder builder = new StringBuilder();
    	return builder.append("director: ").append(this.director)
    			.append("title: ").append(this.title)
    			.append("year: ").append(this.year).toString();
    }
}