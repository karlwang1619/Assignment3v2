package com.example.assignment3.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.assignment3.Activities.MainActivity;
import com.example.assignment3.R;



public class fragmentFav extends Fragment {
    private TextView favText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        favText = view.findViewById(R.id.catFavourite);

        for(int i = 0; i < MainActivity.catFavouriteList.size(); i++) {

            favText.setText(favText.getText()+ ""+ MainActivity.catFavouriteList.get(i));
        }
        return view;
    }


}

