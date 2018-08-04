package com.example.android.miwok;
//** Represents a vocabulary word that we want to learn*/

public class Word {
    //**Default translation of word*/
    private String mDefaultTranslation;

    //**Miwok translation of word*/
    private String mMiwokTranslation;

//**Image resource for word*/
    private int mImageResourceId;

    public Word (String defaultTranslation, String miwokTranslation){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation =miwokTranslation;
    }

//**add image resource id image to word*//
    public Word (String defaultTranslation, String miwokTranslation, int imageResourceId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation =miwokTranslation;
        mImageResourceId = imageResourceId;
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
}
