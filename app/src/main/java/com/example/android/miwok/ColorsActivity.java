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

public class ColorsActivity extends AppCompatActivity {
private MediaPlayer  mMediaPlayer;
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
        words.add ( new Word ( "Red", "Wetetti", R.drawable.color_red, R.raw.color_red ) );
        words.add ( new Word ( "Green", "Chokokki", R.drawable.color_green, R.raw.color_green ) );
        words.add ( new Word ( "Brown", "Takaakki", R.drawable.color_brown,R.raw.color_brown ) );
        words.add ( new Word ( "Gray", "Topoppi", R.drawable.color_gray, R.raw.color_gray ) );
        words.add ( new Word ( "Black", "Kululli", R.drawable.color_black, R.raw.color_black ) );
        words.add ( new Word ( "White", "Kelelli", R.drawable.color_white, R.raw.color_white ) );
        words.add ( new Word ( "Dusty Yellow", "Topiise", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow) );
        words.add ( new Word ( "Mustard Yellow", "Chiwiite", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow ) );

        WordAdapter adapter = new WordAdapter ( this, words, R.color.category_colors );

        ListView listView = (ListView) findViewById ( R.id.list );

        listView.setAdapter ( adapter );

        listView.setAdapter(adapter);
        listView.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id){
            releaseMediaPlayer ();

                Word word = words.get(position);

                //Request audio focus to play
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                }

                mMediaPlayer = MediaPlayer.create ( ColorsActivity.this, word.getAudioResourceId ());
                mMediaPlayer.start ();

                //set up media player so we stop and release
                mMediaPlayer.setOnCompletionListener ( mCompletionListener );
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






















