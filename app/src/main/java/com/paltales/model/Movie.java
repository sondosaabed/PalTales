package com.paltales.model;

public class Movie {
    String url; //"url": "https://www.imdb.com/title/tt6156350/"
    String name; //"name": "HyperNormalisation",
    String image; //"image": "https://m.media-amazon.com/images/M/MV5BNWFlNDlkYWMtODEyOC00MjY1LWIyM2EtMzVjNjQ2YmM4NzE4XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_.jpg",
    String description; //"description": "Adam Curtis explains how, at a time of confusing and inexplicable world events, politicians and the people they represent have retreated in to a damaging over-simplified version of what is happening.",
    int ratingValue;
    String genre; //"Documentary"
    String datePublished; //"2016-10-16"
    String director; //"name": "Adam Curtis"

    public Movie(String url, String name, String image, String description, int ratingValue, String genre, String datePublished, String director) {
        setUrl(url);
        setName(name);
        setImage(image);
        setDescription(description);
        setRatingValue(ratingValue);
        setGenre(genre);
        setDatePublished(datePublished);
        setDirector(director);
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getRatingValue() {
        return ratingValue;
    }
    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getDatePublished() {
        return datePublished;
    }
    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
}
