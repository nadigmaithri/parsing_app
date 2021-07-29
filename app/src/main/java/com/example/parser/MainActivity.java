package com.example.parser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button bxml,bjson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bxml=(Button)findViewById(R.id.button);
        bjson=(Button)findViewById(R.id.button2);
        bxml.setOnClickListener(this);
        bjson.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.equals(bjson)){
            Intent it = new Intent(this,display.class);
            it.putExtra("mode",1);
            startActivity(it);
        }
        else if(v.equals(bxml)){
            Intent it=new Intent(this,display.class);
            it.putExtra("mode",2);
            startActivity(it);
        }

    }
}