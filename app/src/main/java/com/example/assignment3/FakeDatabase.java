package com.example.assignment3;

import com.example.assignment3.Model.CatResponse;

import java.util.HashMap;
import java.util.List;

public class FakeDatabase {

    public static HashMap<String, CatResponse> cats = new HashMap<>();

    public static CatResponse getBreedByID(String catID) {
        return cats.get(catID);
    }

    public static List<CatResponse> getAllCats() {
        return (List) cats.values();

    }

    public static void saveCatsToFakeDatabase(List<CatResponse> catsToSave) {
        for (int i = 0; i < catsToSave.size(); i++) {
            CatResponse cat = catsToSave.get(i);
            cats.put(cat.getId(), cat);
        }
    }
}
