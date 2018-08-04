package com.example.android.miwok;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.ListView;

        import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.word_list );

        // Create an arraylist of words
        ArrayList <Word> words = new ArrayList <Word> ();
        words.add ( new Word ( "Red", "Wetetti" ) );
        words.add ( new Word ( "Green", "Chokokki" ) );
        words.add ( new Word ( "Brown", "Takaakki" ) );
        words.add ( new Word ( "Gray", "Topoppi" ) );
        words.add ( new Word ( "Black", "Kululli" ) );
        words.add ( new Word ( "White", "Kelelli" ) );
        words.add ( new Word ( "Dusty Yellow", "Topiise" ) );
        words.add ( new Word ( "Mustard Yellow", "Chiwiite" ) );

        WordAdapter adapter = new WordAdapter ( this, words );

        ListView listView = (ListView) findViewById ( R.id.list );

        listView.setAdapter ( adapter );

    }

}













