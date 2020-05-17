package com.example.syngenta;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
                if (position == 0) {
                    Toast.makeText(VerifyPage.this, "This is first list", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.list_dat, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_verify:

                actionVerifyClickHundle();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void actionVerifyClickHundle() {
        final AlertDialog alertDialog = new AlertDialog.Builder(VerifyPage.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Are you sure all your calculations are correct?");
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(VerifyPage.this, MainActivity.class));
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
                Toast.makeText(VerifyPage.this, "Check all calculations again", Toast.LENGTH_LONG).show();
            }
        });
        alertDialog.show();
    }

}
