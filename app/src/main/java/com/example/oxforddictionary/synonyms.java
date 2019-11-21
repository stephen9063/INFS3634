package com.example.oxforddictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.request.DictionarySearchRequest;
import com.example.request.DictionarySynonymsRequest;

public class synonyms extends AppCompatActivity {

    String url;
    private TextView synonyms;
    private TextView enterWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synonyms);

        synonyms = findViewById(R.id.synonyms);
        enterWord = findViewById(R.id.enterWord);
    }

    public void requestApiButtonClickSyn (View v){
        DictionarySynonymsRequest myDictionarysynonymsRequest = new DictionarySynonymsRequest(this, synonyms) ;
        url = dictionaryEntries();
        myDictionarysynonymsRequest.execute(url);
    }

    private String dictionaryEntries() {
        final String language = "en";
        final String word = enterWord.getText().toString();
        final String fields = "synonyms";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/thesaurus/" + language + "/" + word_id + "?fields=" + fields + "&strictMatch=" + strictMatch;
    }
}
