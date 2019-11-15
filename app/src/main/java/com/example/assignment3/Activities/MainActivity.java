package com.example.assignment3.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.assignment3.Fragments.fragmentFav;
import com.example.assignment3.Fragments.fragmentSearch;
import com.example.assignment3.Model.Favourite;
import com.example.assignment3.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView nav_menu;
    public static ArrayList<Favourite> catFavouriteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        catFavouriteList = new ArrayList<Favourite>();


        Fragment fragment = new fragmentSearch();
        swapFragment(fragment);


        nav_menu = findViewById(R.id.nav_menu);
        nav_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.nav_search) {
                    Fragment fragment1 = new fragmentSearch();
                    swapFragment(fragment1);
                }
                else if (menuItem.getItemId()== R.id.nav_fav) {
                    Fragment fragment2 = new fragmentFav();
                    swapFragment(fragment2);
                }

                return true;


            }


        });

        }
    private void swapFragment (Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, fragment);
        fragmentTransaction.commit();
    }
}
