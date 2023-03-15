package org.example;

import java.io.Serializable;
/**
 * class of MovieDVD mainly used for type
 *
 * */
public class MovieDVD extends Media implements Serializable {


    public MovieDVD(String title, double length, String genre) {
        super(title, length, genre);
    }

    public MovieDVD() {

    }

    @Override
    public String toString() {
        return "MovieDVD "+super.toString();
    }
}
