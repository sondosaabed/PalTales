package com.paltales.model;

public class Movie {
    String url; //"url": "https://www.imdb.com/title/tt6156350/"
    String name; //"name": "HyperNormalisation",
    String image; //"image": "https://m.media-amazon.com/images/M/MV5BNWFlNDlkYWMtODEyOC00MjY1LWIyM2EtMzVjNjQ2YmM4NzE4XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_.jpg",
    int ratingValue;

    public Movie(String url, String name, String image, int ratingValue) {
        setUrl(url);
        setName(name);
        setImage(image);
        setRatingValue(ratingValue);
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
    public int getRatingValue() {
        return ratingValue;
    }
    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }
}