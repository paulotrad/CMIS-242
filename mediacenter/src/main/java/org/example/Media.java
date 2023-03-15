package org.example;

import java.io.Serializable;

public class Media implements Serializable {

    private static final long serialVersionUID = -7571963854203098339L;
    private long id;
    private boolean available;
    private String title;
    private double length;
    private String genre;

    private String type;

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Media(String title, double length, String genre){

        this.title=title;

        this.length=length;

        this.genre=genre;

        this.available=true;

    }

    public Media() {

    }


    public void setId(long id) {
        this.id = id;
    }




    public void setTitle(String title) {
        this.title = title;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getGenre() {
        return genre;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", available=" + available +
                ", title=" + title +
                ", length=" + length +
                ", genre=" + genre+",type="+ type;
    }
}
