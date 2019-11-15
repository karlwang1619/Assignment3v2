package com.example.assignment3.Model;

public class Favourite {
    String catName;

    public Favourite(String catName) {
        this.catName = catName;
    }

    public String getCatName() {
        return catName;
    }

    @Override
    public String toString () {
        return "Cat Breed: " + getCatName() + "\n" + "\n";
    }
}
