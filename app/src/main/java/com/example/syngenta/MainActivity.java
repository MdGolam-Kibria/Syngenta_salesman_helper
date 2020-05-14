package com.example.syngenta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String SHARD_PREF_NAME = "fcmToken";
    private static final String SHARD_PREF_KEY = "token";
    EditText name, size, price, cartonNumber;
    Button saveProduct;
    TextView resister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.productName);
        size = findViewById(R.id.size);
        price = findViewById(R.id.price);
        cartonNumber = findViewById(R.id.cartonNumber);
//        saveProduct = findViewById(R.id.addProduct);
//        saveProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String pName = name.getText().toString();
//                String pSize = size.getText().toString();
//                String pPrice = price.getText().toString();
//                String cartonNumbers = cartonNumber.getText().toString();
//                if (pName.isEmpty()) {
//                    name.setError("Name is empty");
//                    name.requestFocus();
//                    return;
//                }if (pName.length()<2){
//                    name.setError("name length should be 2 or above");
//                    name.requestFocus();
//                    return;
//                }
//                if (pSize.isEmpty()) {
//                    size.setError("size is empty");
//                    size.requestFocus();
//                    return;
//                }if (pSize.length()<2){
//                    size.setError("size length should be 2 or above");
//                    size.requestFocus();
//                    return;
//                }
//                if (pPrice.isEmpty()) {
//                    price.setError("Name is empty");
//                    price.requestFocus();
//                    return;
//                }if (pPrice.length()<2){
//                    price.setError("name length should be 2 or above");
//                    price.requestFocus();
//                    return;
//                }
//                if (cartonNumbers.isEmpty()) {
//                    cartonNumber.setError("cartonNumber is empty");
//                    cartonNumber.requestFocus();
//                    return;
//                }if (cartonNumbers.length()<1){
//                    cartonNumber.setError("cartonNumber length should be 1 or above");
//                    cartonNumber.requestFocus();
//                    return;
//                }
//            }
//        });

    }
}
