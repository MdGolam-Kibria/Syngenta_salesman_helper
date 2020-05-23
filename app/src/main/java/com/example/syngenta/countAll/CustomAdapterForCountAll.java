package com.example.syngenta.countAll;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.syngenta.MainActivity;
import com.example.syngenta.R;

import java.util.List;

public class CustomAdapterForCountAll extends RecyclerView.Adapter<CountViewHolder> {
    Context context;
    List<String> listDataALL;
    List<String> price;
    List<String> cartonNumber;

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

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final CountViewHolder holder, final int position) {
        String allinfo = listDataALL.get(position);
        Double productPrice = Double.parseDouble(price.get(position));
        Double cartonNumbers = Double.parseDouble(cartonNumber.get(position));
        Double calculationTotalAmount = cartonNumbers * productPrice;
        //for all total

        String totalAmount = String.valueOf(calculationTotalAmount);//for each item total


        holder.allInfo.setText(allinfo);
        holder.sampleTotal.setText(totalAmount);
        holder.sampleTotaldesign.setText(price.get(position) + "x" + cartonNumber.get(position) + "  =");
    }

    @Override
    public int getItemCount() {
        return listDataALL.size();
    }
}
