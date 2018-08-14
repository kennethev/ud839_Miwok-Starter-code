package com.example.android.miwok;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.media.AudioManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class NumbersActivity extends AppCompatActivity {
private MediaPlayer mMediaPlayer;

private AudioManager mAudioManager;
/** this gets triggered
 * when turned on
 */
private AudioManager.OnAudioFocusChangeListener  mOnAudioFocusChangeListener =
        new AudioManager.OnAudioFocusChangeListener (){
    @ Override
            public void onAudioFocusChange (int focusChange){
        if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||
        focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
            /** THE ALT works for a short time and app is allowed to continue to play
             * pause playback and restart
             */
            mMediaPlayer.pause ();
            mMediaPlayer.seekTo ( 0 );
        } else if (focusChange ==AudioManager.AUDIOFOCUS_GAIN) {
            /** AF gain audio back and resumes play*/
            mMediaPlayer.start ();
        }else if (focusChange ==AudioManager.AUDIOFOCUS_LOSS){
            /**AFL lose audio and stop playback*/
            releaseMediaPlayer ();
        }

    }

};
    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * <p>
     * playing the audio file.
     */

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener () {

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer ();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.word_list );

        mAudioManager = (AudioManager) getSystemService ( Context.AUDIO_SERVICE);

// Create an arraylist of words
       final ArrayList <Word> words = new ArrayList <Word> ();
        words.add (new Word("One","Lutti", R.drawable.number_one, R.raw.number_one ));
        words.add (new Word("Two", "Otiiko", R.drawable.number_two, R.raw.number_two ));
        words.add (new Word("Three","Tolookosu",R.drawable.number_three, R.raw.number_three));
        words.add (new Word( "Four","Oyyisa",R.drawable.number_four, R.raw.number_four));
        words.add (new Word("Five", "Massokka",R.drawable.number_five, R.raw.number_five));
        words.add (new Word( "Six", "Temmokka",R.drawable.number_six, R.raw.number_six));
        words.add (new Word( "Seven", "Kenekaku",R.drawable.number_seven, R.raw.number_seven));
        words.add (new Word("Eight", "Kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add (new Word( "Nine", "Wo'e",R.drawable.number_nine, R.raw.number_nine ));
        words.add (new Word( "Ten", "Na'aacha",R.drawable.number_ten, R.raw.number_ten));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
            @Override


            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                releaseMediaPlayer ();

                Word word = words.get ( position );

                //Request audio focus to play
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                         AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create ( NumbersActivity.this, word.getAudioResourceId () );
                    mMediaPlayer.start ();
                    //set up media player so we stop and release
                    mMediaPlayer.setOnCompletionListener ( mCompletionListener );
                }
            }
        } );
    }

    @Override
    protected void onStop(){
        super.onStop ();
        /**when activity stop, release media player*/
        releaseMediaPlayer ();
    }

    /**
     * cleanup media player by releasing it
     * after activity stops
     */
    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release ();
            mMediaPlayer = null;

            //*Abandon audio focus and unregister AFCL so no more callbacks are received/
            mAudioManager.abandonAudioFocus ( mOnAudioFocusChangeListener );
        }

            }
}



