package com.example.syngenta;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.syngenta.countAll.CustomAdapterForCountAll;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView total;
    RecyclerView totalCalRecyccler;
    List<String> listDataALL = new ArrayList<>();
    List<String> price = new ArrayList<>();
    List<String> cartonNumber = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        total = (TextView) findViewById(R.id.allCostTotal);
        totalCalRecyccler = findViewById(R.id.totalCalculationRecycclerView);
        totalCalRecyccler.setHasFixedSize(true);
        totalCalRecyccler.setLayoutManager(new LinearLayoutManager(MainActivity.this));//numberOfColumns

        MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(getApplicationContext());
        final Cursor cursor = myDataBaseHelper.showAll();
        if (cursor.getCount() == 0) {
            Toast.makeText(MainActivity.this, "no data in your database", Toast.LENGTH_LONG).show();
        }
        while (cursor.moveToNext()) {
            listDataALL.add(cursor.getString(0) + " \n" + cursor.getString(1) + " \n" + cursor.getString(2) + " \n" + cursor.getString(3) + "\n" + cursor.getString(4));
            price.add(cursor.getString(3));
            cartonNumber.add(cursor.getString(4));

        }

        if (price.size() == cartonNumber.size()) {
            Integer totalEach = 1;
            for (int i = 0; i < price.size(); i++) {
                Integer temp = Integer.parseInt(price.get(i));
                Integer temp2 = Integer.parseInt(cartonNumber.get(i));
                totalEach *= temp * temp2;
            }
            total.setText(String.valueOf("In total = "+totalEach));
        }
        CustomAdapterForCountAll customAdapter = new CustomAdapterForCountAll(MainActivity.this, listDataALL, price, cartonNumber);
        totalCalRecyccler.setAdapter(customAdapter);
    }
}
