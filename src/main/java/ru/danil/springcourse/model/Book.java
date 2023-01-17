package ru.danil.springcourse.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;

    @NotEmpty(message = "Название книги не должно быть пустым")
    @Size(min = 1, max = 100, message = "Название книги должно быть от 1 до 100 символов длиной")
    private String title;

    @Min(value = 1500, message = "Год должен быть больше, чем 1500")
    private int year;

    @NotEmpty(message = "Автор не может быть без имени")
    private String author;

    public Book(String nameBook,int year, String author) {
        this.title = nameBook;
        this.year = year;
        this.author = author;
    }



    public Book() {}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
