package com.example.syngenta.countAll;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.syngenta.R;

public class CountViewHolder extends RecyclerView.ViewHolder {
    TextView allInfo;
    TextView sampleTotal;
    TextView sampleTotaldesign;

    public CountViewHolder(@NonNull View itemView) {
        super(itemView);
        allInfo = itemView.findViewById(R.id.allInfo);
        sampleTotal = itemView.findViewById(R.id.totalPriceForAproduct);
        sampleTotaldesign = itemView.findViewById(R.id.totalPriceForAproductDesign);
    }
}
