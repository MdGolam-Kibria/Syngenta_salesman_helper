package com.example.syngenta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataBaseHelper {
    Context context;
    MyDataBase myDataBase;

    public long insert(Product modelClass) {
        SQLiteDatabase sqLiteDatabase = myDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDataBase.NAME, modelClass.getName());
        contentValues.put(myDataBase.SIZE, modelClass.getSize());
        contentValues.put(myDataBase.PRICE, modelClass.getPrice());
        contentValues.put(myDataBase.CARTON_NUMBER, modelClass.getCartonNumber());
        long id = sqLiteDatabase.insert(myDataBase.TABLE_NAME, null, contentValues);
        return id;
    }

    public boolean update(String id, String name, String size, String price, String cartonNumber) {
        SQLiteDatabase sqLiteDatabase = myDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDataBase.UID, id);
        contentValues.put(myDataBase.NAME, name);
        contentValues.put(myDataBase.SIZE, size);
        contentValues.put(myDataBase.PRICE, price);
        contentValues.put(myDataBase.CARTON_NUMBER, cartonNumber);
        sqLiteDatabase.update(myDataBase.TABLE_NAME, contentValues, myDataBase.UID + " = ?", new String[]{id});
        return true;
    }

    public int delete(String id) {
        SQLiteDatabase sqLiteDatabase = myDataBase.getWritableDatabase();
        int value = sqLiteDatabase.delete(myDataBase.TABLE_NAME, myDataBase.UID + " = ?", new String[]{id});
        return value;
    }

    public Cursor showAll() {
        SQLiteDatabase sqLiteDatabase = myDataBase.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + MyDataBase.TABLE_NAME, null);
        return cursor;
    }

    public MyDataBaseHelper(Context context) {
        this.context = context;
        myDataBase = new MyDataBase(context);
    }


    class MyDataBase extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "kibriaDta.db";
        private static final String TABLE_NAME = "TABLE_NAME";
        private static final String UID = "_id";
        private static final String NAME = "NAME";
        private static final String SIZE = "SIZE";
        private static final String PRICE = "PRICE";
        private static final String CARTON_NUMBER = "CARTON_NUMBER";
        private static final int DATABASE_VERSION = 1;
        Context context;
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " VARCHAR(77) , "
                + SIZE + " VARCHAR(77) , "
                + PRICE + " VARCHAR(77) , "
                + CARTON_NUMBER + " VARCHAR(77));";

        private static final String DROP_TABLE = " DROP TABLE IF EXISTS " + TABLE_NAME;

        public MyDataBase(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
                Toast.makeText(context, "table created", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(context, "not create" + e, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
                Toast.makeText(context, "table upgreated", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(context, "table update pb" + e, Toast.LENGTH_LONG).show();
            }
        }
    }
}
