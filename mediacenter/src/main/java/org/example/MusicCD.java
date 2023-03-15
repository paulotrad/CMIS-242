package org.example;

import java.io.Serializable;
/**
 * class of MusicCD mainly used for type
 *
 * */
public class MusicCD extends Media implements Serializable {


    public MusicCD(String title, double length, String genre) {
        super(title, length, genre);
    }
    MusicCD(){
        super();

    }

    @Override
    public String toString() {
        return "MusicCD "+super.toString();
    }
}
