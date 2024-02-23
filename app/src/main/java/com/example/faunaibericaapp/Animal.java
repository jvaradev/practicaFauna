package com.example.faunaibericaapp;

public class Animal {
    private int imageResource;
    private String name;

    public Animal(int imageResource, String name) {
        this.imageResource = imageResource;
        this.name = name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getName() {
        return name;
    }
}
