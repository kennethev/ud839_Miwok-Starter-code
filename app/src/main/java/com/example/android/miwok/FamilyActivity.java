package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.media.AudioManager;

import java.util.ArrayList;


public class FamilyActivity extends AppCompatActivity {
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


    /** This listener gets triggered when the {@link MediaPlayer} has completed
     * <p>
     * playing the audio file.*/


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
        words.add ( new Word ( "Father", "ede", R.drawable.family_father, R.raw.family_father ) );
        words.add ( new Word ( "Mother", "eta", R.drawable.family_mother, R.raw.family_mother ) );
        words.add ( new Word ( "Son", "Angsi", R.drawable.family_son, R.raw.family_son ) );
        words.add ( new Word ( "Daughter", "Tune", R.drawable.family_daughter, R.raw.family_daughter ) );
        words.add ( new Word ( "Older Brother", "Taachi", R.drawable.family_older_brother, R.raw.family_older_brother ) );
        words.add ( new Word ( "Younger Brother", "Challiti", R.drawable.family_younger_brother, R.raw.family_younger_brother ) );
        words.add ( new Word ( "Older Sister", "Tete", R.drawable.family_older_sister, R.raw.family_older_sister ) );
        words.add ( new Word ( "Younger Sister", "Kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister ) );
        words.add ( new Word ( "Grandmother", "Ama", R.drawable.family_grandmother, R.raw.family_grandmother ) );
        words.add ( new Word ( "Grandfather", "Paapa", R.drawable.family_grandfather, R.raw.family_grandfather ) );

        WordAdapter adapter = new WordAdapter ( this, words, R.color.category_family );

        ListView listView = (ListView) findViewById ( R.id.list );

        listView.setAdapter ( adapter );

        listView.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
        releaseMediaPlayer();

                Word word = words.get( position );

                //Request audio focus to play
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create ( FamilyActivity.this, word.getAudioResourceId () );
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













