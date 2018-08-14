package com.example.android.miwok;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import java.util.ArrayList;

public class WordAdapter  extends ArrayAdapter<Word>{

    /*resource id for background color*/

    private int mColorResourceId;


   public  WordAdapter(Context context, ArrayList <Word> words, int colorResourceId ){
        super ( context, 0, words );
        mColorResourceId = colorResourceId;
    }
@Override
       public View getView(int position, View convertView,ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Word currentWord = getItem(position);

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getMiwokTranslation());

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getDefaultTranslation());

    ImageView imageView = (ImageView) listItemView.findViewById ( R.id.image );
    imageView.setImageResource(currentWord.getImageResourceId());

    // check to see if image is present*/

    if (currentWord.hasImage ()){
        //if image is available, use it
        imageView.setImageResource ( currentWord.getImageResourceId () );
        //make sure view is visible
        imageView.setVisibility(View.VISIBLE);
    }    else{
        //if not, hide images
        imageView.setVisibility ( View.GONE );
    }
//set the them color for list item*/

    View textContainer = listItemView.findViewById ( R.id.text_container );
    //find color that resources ID's map to*/

    int color = ContextCompat.getColor ( getContext(), mColorResourceId );
    //set the background color of text container*/

    textContainer.setBackgroundColor (color);

        return listItemView;
    }
    }


