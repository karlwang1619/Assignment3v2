package com.example.assignment3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment3.Activities.CatDetailActivity;
import com.example.assignment3.Model.CatResponse;

import java.util.List;

public class CatAdaptor extends RecyclerView.Adapter<CatAdaptor.CatViewHolder> {

    private List<CatResponse> catsToAdapt;

    public void setData(List<CatResponse> catsToAdapt) {
        this.catsToAdapt= catsToAdapt;
    }


    @NonNull
    @Override
    public CatAdaptor.CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat, parent, false);
        CatViewHolder catViewHolder = new CatViewHolder(view);
        return catViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        final CatResponse catAtPosition = catsToAdapt.get(position);

        holder.catName.setText(catAtPosition.getName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {
                Context context = view.getContext();
                Intent intent = new Intent(context, CatDetailActivity.class);
                intent.putExtra("catID", catAtPosition.getId());
                context.startActivity(intent);
            }
        }) ;


    }

    @Override
    public int getItemCount() {
        return catsToAdapt.size();
    }



    public static class CatViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView catName;

        public CatViewHolder(View v) {
            super(v);
            view = v;
            catName = v.findViewById(R.id.catName);
        }
    }
}
