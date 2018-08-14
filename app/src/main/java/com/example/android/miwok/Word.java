package com.example.android.miwok;
//** Represents a vocabulary word that we want to learn*/

public class Word {
    //**Default translation of word*/
    private String mDefaultTranslation;

    private String mMiwokTranslation;


    //**Miwok translation of word*/
//**Image resource for word*/
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = 0;

    private int mAudioResourceId;

    public Word (String defaultTranslation, String miwokTranslation, int audioResourceId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation =miwokTranslation;
        mAudioResourceId = audioResourceId;

    }

//**add image resource id image to word*//
    public Word (String defaultTranslation, String miwokTranslation, int imageResourceId, int audioResourceId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation =miwokTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    /**Get default translation*/
    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }
    /**Get Miwok translation*/
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }
    //**return image resource of word*/
    public int getImageResourceId(){
        return mImageResourceId;
    }

    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceId(){
    return mAudioResourceId;
    }
}

