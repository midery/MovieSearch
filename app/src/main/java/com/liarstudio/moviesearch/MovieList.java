package com.liarstudio.moviesearch;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс-обертка над обычным списком из фильмов, нужен для того, чтобы получать список
 * из JSON-ответа сервера.
 */
public class MovieList {

    //Поля и конструкторы
    @SerializedName("results")
    private List<Movie> items;

    public MovieList() {
        items = new ArrayList<>();
    }
    public MovieList(List<Movie> items) {
        this.items = new ArrayList<>(items);
    }

    //Геттеры и сеттеры
    public int getSize() {
        return items.size();
    }
    public Movie get(int position) {
        return items.get(position);
    }
    public void set(List<Movie> items) {
        this.items = items;
    }
    public List<Movie> getItems() {
        return items;
    }

    //Дополнитльные методы списка
    public void clear() {
        items.clear();
    }
    public void add(Movie item) {
        items.add(item);
    }
}
