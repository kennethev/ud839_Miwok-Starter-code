package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.word_list );

    // Create an arraylist of words
    ArrayList <Word> words = new ArrayList <Word> ();
        words.add(new Word("Father","ede"));
            words.add(new Word("Mother","eta"));
            words.add(new Word("Son","Angsi"));
            words.add(new Word("Daughter","Tune"));
            words.add(new Word("Older Brother","Taachi"));
            words.add(new Word("Younger Brother","Challiti"));
            words.add(new Word("Older Sister","Tete"));
            words.add(new Word("Younger Sister","Kolliti"));
            words.add(new Word("Grandmother","Ama"));
            words.add(new  Word("Grandfather","Paapa"));

    WordAdapter adapter = new WordAdapter ( this, words );

    ListView listView = (ListView) findViewById ( R.id.list );

        listView.setAdapter(adapter);

}

}





