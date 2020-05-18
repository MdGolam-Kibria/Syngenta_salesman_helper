package com.example.syngenta;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderData extends RecyclerView.ViewHolder {
    TextView sampleText;

    public ViewHolderData(@NonNull View itemView) {
        super(itemView);
        sampleText = itemView.findViewById(R.id.sample);
    }
}
