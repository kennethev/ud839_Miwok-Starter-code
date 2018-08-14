package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.media.AudioManager;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

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
                         * pause playback and restart*/

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
        words.add ( new Word ( "Where are you going?", "Minto wuksus", R.raw.phrase_where_are_you_going ) );
        words.add ( new Word ( "What is your name?", "Tinne oyaase'ne", R.raw.phrase_what_is_your_name ) );
        words.add ( new Word ( "My name is ...", "Oyasset ...", R.raw.phrase_my_name_is ) );
        words.add ( new Word ( "How are you feeling?", "Michekses?", R.raw.phrase_how_are_you_feeling ) );
        words.add ( new Word ( "I'm feeling good", "Kuchi achit", R.raw.phrase_im_feeling_good ) );
        words.add ( new Word ( "Are you coming?", "Eenes'aa?", R.raw.phrase_are_you_coming ) );
        words.add ( new Word ( "Yes, I'm coming", "Hee 'eenem", R.raw.phrase_im_coming ) );
        words.add ( new Word ( "I'm coming", "Eenem", R.raw.phrase_im_coming ) );
        words.add ( new Word ( "Let's Go", "Yoowutis", R.raw.phrase_lets_go ) );
        words.add ( new Word ( "Come here", "Enni 'nem", R.raw.phrase_come_here ) );

        WordAdapter adapter = new WordAdapter ( this, words, R.color.category_phrases );

        ListView listView = (ListView) findViewById ( R.id.list );

        listView.setAdapter ( adapter );
        listView.setOnItemClickListener ( new AdapterView.OnItemClickListener () {

            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                releaseMediaPlayer ();


                Word word = words.get ( position );
                //Request audio focus to play
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {


                    mMediaPlayer = MediaPlayer.create ( PhrasesActivity.this, word.getAudioResourceId () );
                    mMediaPlayer.start ();

                    //set up media player so we stop and release
                    mMediaPlayer.setOnCompletionListener ( mCompletionListener );
                }
            }
        } );
    }

    @Override
    protected void onStop() {
        super.onStop ();
        //when activity stop, release media player
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




