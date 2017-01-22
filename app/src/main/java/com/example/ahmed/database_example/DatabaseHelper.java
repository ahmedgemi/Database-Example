package com.example.ahmed.database_example;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ahmed on 18/01/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public final static String database_name = "database" ;

    public final static String table_name = "contacts" ;

    public final static String key_name = "name" ;
    public final static String key_number = "number" ;
    public final static String key_email = "email" ;



    public DatabaseHelper(Context context) {


        super(context, database_name, null, 1);

        Log.d("test","kook");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String query = "CREATE TABLE " + table_name + "(" +  key_name + " TEXT," +  key_number + " TEXT," +  key_email + " TEXT)";

        db.execSQL(query);



        // "CREATE TABLE contacts(name TEXT, number TEXT, email TEXT)"
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + table_name);

        // Create tables again
        onCreate(db);

    }


    public void insert ( Contact contact){


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(key_name , contact.name );
        values.put(key_number , contact.number );
        values.put(key_email , contact.email );

        db.insert(table_name ,null , values) ;

        db.close();

    }

    public ArrayList<Contact>  getData(){

        ArrayList<Contact> list = new ArrayList<Contact>();


        String query = "SELECT * FROM " + table_name ;


        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){


            do{

                String name = cursor.getString(0);
                String num = cursor.getString(1);
                String email = cursor.getString(2);

                list.add( new Contact(name , num , email));


            }while ( cursor.moveToNext() );
        }


        return  list ;
    }
}
