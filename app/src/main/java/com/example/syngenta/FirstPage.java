package com.example.syngenta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FirstPage extends AppCompatActivity {
    EditText name, size, price, carton;
    Button addPro, verify, countAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        this.setTitle("Add product window");
        name = findViewById(R.id.proName);
        size = findViewById(R.id.proSize);
        price = findViewById(R.id.proPrice);
        carton = findViewById(R.id.proCartunNum);
        addPro = findViewById(R.id.addPro);
        verify = findViewById(R.id.verify);
        countAll = findViewById(R.id.countAllProduct);
        addPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pName = name.getText().toString();
                String pSize = size.getText().toString();
                String pPrice = price.getText().toString();
                String pCarton = carton.getText().toString();
                if (pName.isEmpty()) {
                    name.setError("Please Enter Your Name");
                    name.requestFocus();
                    return;
                }
                if (pSize.isEmpty()) {
                    size.setError("Please Enter Your Size");
                    size.requestFocus();
                    return;
                }
                if (pPrice.isEmpty()) {
                    price.setError("Please Enter Your Price");
                    price.requestFocus();
                    return;
                }
                if (pCarton.isEmpty()) {
                    carton.setError("Please Enter Your Carton Number");
                    carton.requestFocus();
                    return;
                }
                /**
                 * for save data
                 */
                Product product = new Product();
                MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(getApplicationContext());
                product.setName(pName);
                product.setSize(pSize);
                product.setPrice(pPrice);
                product.setCartonNumber(pCarton);

                long id = myDataBaseHelper.insert(product);//for insert data to database
                if (id == -1) {
                    Toast.makeText(FirstPage.this, "not save", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(FirstPage.this, "Save Product Info", Toast.LENGTH_LONG).show();
                    name.setText("");
                    size.setText("");
                    price.setText("");
                    carton.setText("");
                    carton.onEditorAction(EditorInfo.IME_ACTION_DONE);//for hide keyboard
                }
            }
        });
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(getApplicationContext());
                Cursor cursor = myDataBaseHelper.showAll();
                if (cursor.getCount() == 0) {
                    Toast.makeText(FirstPage.this, "No Any Data in your database please add some data", Toast.LENGTH_LONG).show();
                } else if (cursor.getCount() > 0) {
                    startActivity(new Intent(FirstPage.this, VerifyPage.class));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.update:
                actionUpdateHandle();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void actionUpdateHandle() {
        Toast.makeText(this, "cilck update", Toast.LENGTH_SHORT).show();

        final androidx.appcompat.app.AlertDialog alertDialog = new androidx.appcompat.app.AlertDialog.Builder(FirstPage.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Are you sure all your calculations are correct?");
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE, "Add",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       // startActivity(new Intent(FirstPage.this, MainActivity.class));
                        Toast.makeText(FirstPage.this, "Add", Toast.LENGTH_LONG).show();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 Toast.makeText(FirstPage.this, "update product", Toast.LENGTH_LONG).show();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(FirstPage.this, "Cancel", Toast.LENGTH_LONG).show();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }
}
