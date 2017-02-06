package com.example.sizebook;

import java.util.Date;

/**
 * This class is the model class for storing the record entered by the user
 * Created by Shivansh on 2017-01-31.
 */
public class Person {
    private String name;
    private String date;
    private Dimensions dimensions;
    private String comment;

    /**
     * Instantiates a new Person.
     *
     * @param name       the name
     * @param date       the date
     * @param dimensions the dimensions
     * @param comment    the comment
     */
    public Person(String name, String date, Dimensions dimensions, String comment) {
        this.name = name;
        this.date = date;
        this.dimensions = dimensions;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", dimensions=" + dimensions +
                ", comment='" + comment + '\'' +
                '}';
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets dimensions.
     *
     * @return the dimensions
     */
    public Dimensions getDimensions() {
        return dimensions;
    }

    /**
     * Sets dimensions.
     *
     * @param dimensions the dimensions
     */
    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
