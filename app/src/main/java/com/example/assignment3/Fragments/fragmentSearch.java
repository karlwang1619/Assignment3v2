package com.example.assignment3.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.assignment3.CatAdaptor;
import com.example.assignment3.FakeDatabase;
import com.example.assignment3.Model.CatResponse;
import com.example.assignment3.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.Arrays;
import java.util.List;

public class fragmentSearch extends Fragment {

    private RecyclerView rv1;
    private Button searchButton;
    private EditText query1;

    public fragmentSearch(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        final View view = inflater.inflate(R.layout.fragment_search,  container, false);
        searchButton = view.findViewById(R.id.searchButton);
        query1 = view.findViewById(R.id.searchQuery);
        rv1 = view.findViewById(R.id.rv1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        rv1.setLayoutManager(layoutManager);

        final CatAdaptor catAdaptor = new CatAdaptor();
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Searching Now", Toast.LENGTH_SHORT).show();


                final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                String query = query1.getText().toString();
                String url = "https://api.thecatapi.com/v1/breeds/search?q=" + query;

// change volley request to probably Json Array
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        CatResponse[] catArray = gson.fromJson(response, CatResponse[].class);
                        List<CatResponse> catList = Arrays.asList(catArray);
                        FakeDatabase.saveCatsToFakeDatabase(catList);
                        catAdaptor.setData(catList);
                        rv1.setAdapter(catAdaptor);

                        requestQueue.stop();


                        Toast.makeText(getContext(), "Response Received", Toast.LENGTH_SHORT).show();


                    }
                };

                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),"The Request Failed " +error.getMessage(), Toast.LENGTH_SHORT).show();
                        requestQueue.stop();
                    }
                };


                StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, url, responseListener, errorListener);
                requestQueue.add(stringRequest);




            }
        });

        return view;
    }
}
