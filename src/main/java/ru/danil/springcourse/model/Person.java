package ru.danil.springcourse.model;

import javax.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 3, max = 150, message = "Имя должно быть от 3 до 150 символов длиной")
    @Pattern(regexp= "[А-Я][а-я]{1,33}\\s[А-Я][а-я]{1,33}\\s[А-Я][а-я]{1,33}", message="Имя должно выглядить следующем оброзом: Иванов Иван Иванович")
    private String name;

    @Min(value = 1900, message = "Год рождения должен быть больше, чем 1900")
    @Max(value=2023, message = "Год рождения не может быть больше текущего года")
    private int age;

    public Person () {}
    public Person(String name,int year) {
        this.name = name;
        this.age = year;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}

}
