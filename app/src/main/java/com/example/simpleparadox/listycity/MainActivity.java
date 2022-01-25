package com.example.simpleparadox.listycity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Declare the variables so that you will be able to reference it later.
    ListView cityList;
    EditText newName;
    LinearLayout nameField;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = findViewById(R.id.field_nameEntry);
        newName  = findViewById(R.id.editText_name);

        cityList = findViewById(R.id.city_list);


        dataList = new ArrayList<>();

        //dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);


        cityList.setAdapter(cityAdapter);

        final Button addButton = findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nameField.setVisibility(View.VISIBLE);
            }
        });




        // will use this to store the city name dynamically
        ArrayList<String> enteredCityName = new ArrayList<String>();

        final Button confirmButton = findViewById(R.id.button_confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String cityName = newName.getText().toString();
                cityAdapter.add(cityName);
                newName.getText().clear();
                nameField.setVisibility(View.INVISIBLE);

                // adding new cities to enteredCityName
                enteredCityName.add(cityName);
            }
        });

        final Button deleteButton = findViewById(R.id.button_clear);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cityAdapter.clear();
            }
        });




        String []cities ={"Edmonton", "Vancouver", "Moscow",
                "Sydney", "Berlin", "Vienna",
                "Tokyo", "Beijing", "Osaka",
                "New Delhi"};
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                Intent intent = new Intent(MainActivity.this, mysecondActivity.class);
                // intent.putExtra("clickedItem", cities[index]);

                //  this data will be send to viewDetails
                intent.putExtra("clickedItem", enteredCityName.get(index));
                startActivity(intent);
            }
        });
    }
}