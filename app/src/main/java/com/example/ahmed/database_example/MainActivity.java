package com.example.ahmed.database_example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView listView ;

    EditText e1 ,e2, e3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);

        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        e3 = (EditText) findViewById(R.id.editText3);
    }

    public void add (View view){



        try {
            String name = e1.getText().toString();
            String num = e2.getText().toString();
            String email = e3.getText().toString();

            Contact obj = new Contact(name, num,email);

            DatabaseHelper database = new DatabaseHelper(this);

           database.insert( obj );
        }
        catch (Exception e){
            Log.d("test",e.toString());
        }


    }

    public void search (View view){


        DatabaseHelper database = new DatabaseHelper(this);

        ArrayList<Contact> list = database.getData();

        MyAdapter adapter = new MyAdapter(list);

        listView.setAdapter(adapter);

    }


    public class MyAdapter extends BaseAdapter{

        ArrayList<Contact> data = new ArrayList<Contact>();

        MyAdapter(ArrayList<Contact> list){

            data = list ;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Contact getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {

           LayoutInflater inflater = getLayoutInflater();

            View view = inflater.inflate(R.layout.listview_layout ,parent ,false );


            TextView t1 = (TextView) view.findViewById(R.id.textView);
            TextView t2 = (TextView) view.findViewById(R.id.textView2);
            TextView t3 = (TextView) view.findViewById(R.id.textView3);


            t1.setText( data.get(i).name);
            t2.setText( data.get(i).number);
            t3.setText( data.get(i).email);

            return  view;

        }
    }
}
