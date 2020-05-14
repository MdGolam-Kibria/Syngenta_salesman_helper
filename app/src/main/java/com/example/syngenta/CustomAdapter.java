package com.example.syngenta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<ViewHolderData> {

    Context activity;
    List<String> listData;
    public CustomAdapter(VerifyPage verifyPage, List<String> listData) {
        this.activity = verifyPage;
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.sample, parent, false);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderData holder, int position) {
        String currentItem = listData.get(position);

        holder.sampleText.setText(currentItem);

//        below logic is right logic
//        if (CustomSubCatagoriesModelResource.bagSubCatagoriesName[position].equals("Backpack")) {
//            holder.sampleImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    holder.sampleImage.setImageResource(R.drawable.amazon_bd_ecommarce);
//                }
//            });
//        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
