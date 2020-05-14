package com.example.syngenta;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.syngenta.recyclerViewClickAndDeviderHundle.MyRecyclerViewDividerItemDecoration;
import com.example.syngenta.recyclerViewClickAndDeviderHundle.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class VerifyPage extends AppCompatActivity {
    RecyclerView recyclerView;
    ListView listView;
    List<String> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_page);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(VerifyPage.this));//numberOfColumns
        MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(getApplicationContext());
        final Cursor cursor = myDataBaseHelper.showAll();
        if (cursor.getCount() == 0) {
            Toast.makeText(VerifyPage.this, "no data in your database", Toast.LENGTH_LONG).show();
        }
         while (cursor.moveToNext()) {
            listData.add(cursor.getString(0) + " \n" + cursor.getString(1) + " \n" + cursor.getString(2) + " \n" + cursor.getString(3) + "\n" + cursor.getString(4));
        }
        CustomAdapter customAdapter = new CustomAdapter(VerifyPage.this, listData);
        recyclerView.setAdapter(customAdapter);
        recyclerView.addItemDecoration(new MyRecyclerViewDividerItemDecoration(VerifyPage.this, LinearLayoutManager.HORIZONTAL, 16));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(VerifyPage.this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

}
