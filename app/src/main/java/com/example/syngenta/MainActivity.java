package com.example.syngenta;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.syngenta.countAll.CustomAdapterForCountAll;
import com.example.syngenta.recyclerViewClickAndDeviderHundle.MyRecyclerViewDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MainActivity extends AppCompatActivity {
    SwipeRefreshLayout refreshLayout;
    TextView total;
    RecyclerView totalCalRecyccler;
    List<String> listDataALL = new ArrayList<>();
    List<String> price = new ArrayList<>();
    List<String> cartonNumber = new ArrayList<>();
    List<String> id = new ArrayList<>();//get id form database for delete an recycler item


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        refreshLayout.setRefreshing(true);
        refreshLayout = findViewById(R.id.refresh);
        refreshLayout.setEnabled(false);
        refreshLayout.setRefreshing(false);
        total = (TextView) findViewById(R.id.allCostTotal);
        totalCalRecyccler = findViewById(R.id.totalCalculationRecycclerView);
        totalCalRecyccler.setHasFixedSize(true);
        totalCalRecyccler.setLayoutManager(new LinearLayoutManager(MainActivity.this));//numberOfColumns
        final MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(getApplicationContext());
        final Cursor cursor = myDataBaseHelper.showAll();
        if (cursor.getCount() == 0) {
            Toast.makeText(MainActivity.this, "no data in your database", Toast.LENGTH_LONG).show();
        }
        while (cursor.moveToNext()) {
            listDataALL.add(cursor.getString(1) + " \n" + cursor.getString(2));
            price.add(cursor.getString(3));
            cartonNumber.add(cursor.getString(4));
            id.add(cursor.getString(0));

        }

        if (price.size() == cartonNumber.size()) {
            Double totalEach;
            Double totalValue = 0.0;
            for (int i = 0; i < price.size(); i++) {
                Double temp = Double.parseDouble(price.get(i));
                Double temp2 = Double.parseDouble(cartonNumber.get(i));
                totalEach = temp * temp2;
                totalValue = totalValue + totalEach;
            }
            total.setText(String.valueOf("Total = " + totalValue));
        }
        final CustomAdapterForCountAll customAdapter = new CustomAdapterForCountAll(MainActivity.this, listDataALL, price, cartonNumber);
        totalCalRecyccler.setAdapter(customAdapter);
        totalCalRecyccler.addItemDecoration(new MyRecyclerViewDividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL, 22));
        /**
         * start swipe action
         */
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                String pID = id.get(position);
                MyDataBaseHelper myDataBaseHelper1 = new MyDataBaseHelper(getApplicationContext());
                /***
                 * age id nam e arraylist e database er id ta save kore rekhesi tarpore ekhane sei id
                 * diya ekta item delete korechi
                 */
                int mainPosition = Integer.parseInt(pID);
                int responce = myDataBaseHelper1.delete(String.valueOf(mainPosition));
                if (responce > 0) {
                    Toast.makeText(MainActivity.this, "Delete Data", Toast.LENGTH_SHORT).show();
                    customAdapter.notifyDataSetChanged();
                    MyDataBaseHelper data = new MyDataBaseHelper(getApplicationContext());
                    Cursor cursor1 = data.showAll();
                    if (cursor1.getCount() == 0) {
                        startActivity(new Intent(MainActivity.this, FirstPage.class));
                    } else if (cursor1.getCount() > 0) {
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Data don't deleted" + position, Toast.LENGTH_SHORT).show();
                    customAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.red))
                        .addActionIcon(R.drawable.ic_delete_sweep_black_24dp)
                        .addSwipeLeftLabel("Delete")
                        .addSwipeRightLabel("Delete")
                        .create()
                        .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelpers = new ItemTouchHelper(callback);
        /**
         * end swipe action
         */
        itemTouchHelpers.attachToRecyclerView(totalCalRecyccler);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MainActivity.this, VerifyPage.class));
        super.onBackPressed();
    }
}

