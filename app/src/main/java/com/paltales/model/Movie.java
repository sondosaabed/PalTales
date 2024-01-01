package com.paltales.model;

public class Movie {
    private String name; //"name": "HyperNormalisation",
    private String image; //"image": "https://m.media-amazon.com/images/M/MV5BNWFlNDlkYWMtODEyOC00MjY1LWIyM2EtMzVjNjQ2YmM4NzE4XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_.jpg",
    private String url; //"url": "https://www.imdb.com/title/ + id"
    private String plot;
    private int year;

    public Movie(String id, String name, String image, int year, String plot) {
        setUrl("https://www.imdb.com/title/"+id);
        setName(name);
        setImage(image);
        setYear(year);
        setPlot(plot);
    }
    /*
       Getters & Setters
    */
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getPlot() {
        return plot;
    }
    public void setPlot(String plot) {
        this.plot = plot;
    }
}