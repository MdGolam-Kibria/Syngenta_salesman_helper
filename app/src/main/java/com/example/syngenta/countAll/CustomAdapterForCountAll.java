package com.example.syngenta.countAll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.syngenta.MainActivity;
import com.example.syngenta.R;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterForCountAll extends RecyclerView.Adapter<CountViewHolder> {
    Context context;
    List<String> listDataALL;
    List<String> price;
    List<String> cartonNumber;
    List<Integer> all = new ArrayList<>();


    public CustomAdapterForCountAll(MainActivity mainActivity, List<String> listDataALL, List<String> price, List<String> cartonNumber) {
        this.context = mainActivity;
        this.listDataALL = listDataALL;
        this.price = price;
        this.cartonNumber = cartonNumber;

    }

    @NonNull
    @Override
    public CountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.calculation_recycler_sample, parent, false);
        return new CountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountViewHolder holder, int position) {
        String allinfo = listDataALL.get(position);
        int productPrice = Integer.parseInt(price.get(position));
        int cartonNumbers = Integer.parseInt(cartonNumber.get(position));
        int calculationTotalAmount = cartonNumbers * productPrice;
        //for all total

        String totalAmount = String.valueOf(calculationTotalAmount);//for each item total

//        all.add(calculationTotalAmount);
//        if (all.size() == listDataALL.size()) {
//            for (int i = 0; i < all.size(); i++) {
//
//            }
//        }
        holder.allInfo.setText(allinfo);
        holder.sampleTotal.setText(totalAmount + " Tk");
    }

    @Override
    public int getItemCount() {
        return listDataALL.size();
    }
}
