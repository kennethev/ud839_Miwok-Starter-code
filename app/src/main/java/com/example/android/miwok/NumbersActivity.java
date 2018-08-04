package com.example.android.miwok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.word_list );

// Create an arraylist of words
        ArrayList <Word> words = new ArrayList <Word> ();
        words.add (new Word("One","Lutti", R.drawable.number_one ));
        words.add (new Word("Two", "Otiiko", R.drawable.number_two ));
        words.add (new Word("Three","Tolookosu",R.drawable.number_three));
        words.add (new Word( "Four","Oyyisa",R.drawable.number_four));
        words.add (new Word("Five", "Massokka",R.drawable.number_five));
        words.add (new Word( "Six", "Temmokka",R.drawable.number_six));
        words.add (new Word( "Seven", "Kenekaku",R.drawable.number_seven));
        words.add (new Word("Eight", "Kawinta", R.drawable.number_eight));
        words.add (new Word( "Nine", "Wo'e",R.drawable.number_nine ));
        words.add (new Word( "Ten", "Na'aacha",R.drawable.number_ten));

        WordAdapter adapter = new WordAdapter(this, words);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

            }

    }

