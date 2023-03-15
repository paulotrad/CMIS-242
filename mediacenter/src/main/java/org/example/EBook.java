package org.example;

import java.io.Serializable;
/**
 * class of EBooks mainly used for type
 *
 * */
public class EBook extends Media implements Serializable {


    public EBook(String title, double length, String genre) {
        super(title, length, genre);
    }

    public EBook() {

    }


    @Override
    public String toString() {
        return "Ebook "+super.toString();
    }
}


