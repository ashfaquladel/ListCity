package com.example.simpleparadox.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class mysecondActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);


        TextView showCityName;
        Button back_btn;
        showCityName = findViewById(R.id.showText);
        back_btn =  findViewById(R.id.backBtn);

        // data that have been send from another class
        // will store as a map(maybe)
        Bundle data = getIntent().getExtras();
        if(data.getString("clickedItem") != null) {
            showCityName.setText(data.getString("clickedItem"));
        }
        else {
            showCityName.setText("there is nothing to display");
        }

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mysecondActivity.this.finish();
            }
        });
    }
}
