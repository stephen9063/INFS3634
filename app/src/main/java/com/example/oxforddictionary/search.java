package com.example.oxforddictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.oxforddictionary.DictionarySearchRequest;
import com.example.oxforddictionary.R;

public class search extends AppCompatActivity {

    String url;
    private TextView definition;
    private TextView enterWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        definition = findViewById(R.id.definition);
        enterWord = findViewById(R.id.enterWord);
    }

    public void requestApiButtonClick (View v){
        DictionarySearchRequest myDictionaryRequest = new DictionarySearchRequest(this, definition) ;
        url = dictionaryEntries();
        myDictionaryRequest.execute(url);

    }

    private String dictionaryEntries() {
        final String language = "en-gb";
        final String word = enterWord.getText().toString();
        final String fields = "definitions";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }

    /*public void sendRequestOnClick(View v) {
        MyDictionaryRequest dr = new MyDictionaryRequest();
        url = dictionaryEntries();
        dr.execute(url);
    }*/
}
