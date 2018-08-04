package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    setContentView(R.layout.word_list);

    // Create an arraylist of words
    ArrayList <Word> words = new ArrayList <Word> ();
        words.add(new

    Word("Where are you going?","Minto wuksus"));
        words.add(new

    Word("What is your name?","Tinne oyaase'ne"));
        words.add(new

    Word("My name is ...","Oyasset ..."));
        words.add(new

    Word( "How are you feeling?","Michekses?"));
        words.add(new

    Word("I'm feeling good","Kuchi achit"));
        words.add(new

    Word( "Are you coming?","Eenes'aa?"));
        words.add(new

    Word( "Yes, I'm coming","Hee 'eenem"));
        words.add(new

    Word("I'm coming","Eenem"));
        words.add(new

    Word( "Let's Go","Yoowutis"));
        words.add(new

    Word( "Come here","Enni 'nem"));

    WordAdapter adapter = new WordAdapter ( this, words );

    ListView listView = (ListView) findViewById ( R.id.list );

        listView.setAdapter(adapter);

}

}



