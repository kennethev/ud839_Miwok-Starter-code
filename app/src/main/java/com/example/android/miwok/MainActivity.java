package com.example.android.miwok;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);


    //Find View that shows numbers category
        TextView numbers = (TextView) findViewById ( R.id.numbers );

        //Set a click listener on the view
        numbers.setOnClickListener ( new View.OnClickListener () {

            //The code in this method will be executed when numbers view is clicked on
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent ( MainActivity.this, NumbersActivity.class );
                startActivity ( numbersIntent );
            }
        } );
        //Find View that shows colors category
        TextView colors = (TextView) findViewById ( R.id.colors );

        //Set a click listener on the view
        colors.setOnClickListener ( new View.OnClickListener () {

            //The code in this method will be executed when colors view is clicked on
            @Override
            public void onClick(View view) {
                Intent colorsIntent = new Intent ( MainActivity.this, ColorsActivity.class );
                startActivity ( colorsIntent );
            }
        } );
        //Find View that shows phrases category
        TextView phrases = (TextView) findViewById ( R.id.phrases );

        //Set a click listener on the view
        phrases.setOnClickListener ( new View.OnClickListener () {

            //The code in this method will be executed when phrases view is clicked on
            @Override
            public void onClick(View view) {
                Intent phrasesIntent = new Intent ( MainActivity.this, PhrasesActivity.class );
                startActivity ( phrasesIntent );
            }
        } );
        //Find View that shows family members category
        TextView names = (TextView) findViewById ( R.id.family);

        //Set a click listener on the view
        names.setOnClickListener ( new View.OnClickListener () {

            //The code in this method will be executed when names view is clicked on
            @Override
            public void onClick(View view) {
                Intent namesIntent = new Intent ( MainActivity.this, FamilyActivity.class );
                startActivity ( namesIntent );
            }
        } );
    }

}


