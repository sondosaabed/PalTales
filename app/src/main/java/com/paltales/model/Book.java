package com.paltales.model;

public class Book {
    private String title;
    private String cover; //https://covers.openlibrary.org/b/id/ + (899119)  + .jpg
    private String key;// https://openlibrary.org + key
    private String author;// https://openlibrary.org/authors/ + key

    public Book(String title, String cover, String key, String author) {
        setTitle(title);
        setCover("https://covers.openlibrary.org/b/id/"+cover+".jpg");
        setKey("https://openlibrary.org"+key);
        setAuthor("https://openlibrary.org/authors/"+author);
    }

    public Book() {

    }

    /*
       Getters & Setters
    */
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCover() {
        return cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
}