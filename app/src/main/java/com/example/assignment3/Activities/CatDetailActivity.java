package com.example.assignment3.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.example.assignment3.FakeDatabase;
import com.example.assignment3.Model.Cat;
import com.example.assignment3.Model.CatResponse;
import com.example.assignment3.Model.Favourite;
import com.example.assignment3.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class CatDetailActivity extends AppCompatActivity {

    private ImageView catImageIV;
    private TextView catNameTV;
    private TextView catDescriptionTV;
    private TextView wikipediaLinkTV;
    private TextView catWeightTV;
    private TextView catTemperamentTV;
    private TextView catOriginTV;
    private TextView catLifeSpanTV;
    private TextView catFriendLevelTV;
    private Button favButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_detail);

        Intent intent = getIntent();

        String catID = intent.getStringExtra("catID");
        String url = "https://api.thecatapi.com/v1/images/search?breed_id=" + catID;


        CatResponse cat = FakeDatabase.getBreedByID(catID);

        catImageIV = findViewById(R.id.catPhoto);
        catNameTV = findViewById(R.id.catName);
        catDescriptionTV = findViewById(R.id.catDescription);
        wikipediaLinkTV = findViewById(R.id.wikipediaLink);
        catWeightTV = findViewById(R.id.catWeight);
        catTemperamentTV = findViewById(R.id.catTemperament);
        catOriginTV = findViewById(R.id.catOrigin);
        catLifeSpanTV = findViewById(R.id.catLifeSpan);
        catFriendLevelTV = findViewById(R.id.catFriendLevel);

        catNameTV.setText(cat.getName());
        catDescriptionTV.setText(cat.getDescription());
        wikipediaLinkTV.setText(cat.getWikipedia_url());
        if(cat.getWeights() == null) {
            catWeightTV.setText("Weight Unavailable");
        }
        else {
            catWeightTV.setText(cat.getWeights());
        }

        final RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
        Response.Listener<String> responseListener1 = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    if (jsonObject.has("url")) {
                        String imageURL = jsonObject.getString("url");
                        if (!imageURL.equals("")) {
                            Glide.with(getApplicationContext()).load(imageURL).into(catImageIV);
                        }
                    }
                    else {
                        catImageIV.setBackgroundResource(R.drawable.error);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                requestQueue1.stop();
            }

        };

        Response.ErrorListener errorListener1 = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                requestQueue1.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener1, errorListener1);
        requestQueue1.add(stringRequest);

        catTemperamentTV.setText(cat.getTemperament());
        catOriginTV.setText(cat.getOrigin());
        catLifeSpanTV.setText(cat.getLife_span());
        catFriendLevelTV.setText(String.valueOf(cat.getDog_friendly()));

        favButton = findViewById(R.id.favButton);
        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favouriteAdd();
            }
        });



    }

    public void favouriteAdd() {
        String name = (String) catNameTV.getText();
        MainActivity.catFavouriteList.add(new Favourite(name));
        Toast.makeText(getApplicationContext(),"Cat Has Been Added to Favourites! ", Toast.LENGTH_SHORT).show();


    }
}
