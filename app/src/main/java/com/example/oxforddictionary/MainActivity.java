package com.example.oxforddictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button searchBtn;
    private Button synBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBtn = (Button) findViewById(R.id.toSearch);
        synBtn = (Button) findViewById(R.id.toSynonyms);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensearch();
            }
        });

        synBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensyn();
            }
        }));
    }
    public void opensearch(){
        Intent intent = new Intent(this, search.class);
        startActivity(intent);
    }

    public void opensyn(){
        Intent intent = new Intent(this, synonyms.class);
        startActivity(intent);
    }
}