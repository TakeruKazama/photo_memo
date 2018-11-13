package com.example.kzm.photo_memo.data;

import android.net.Uri;

public class PhotoData {
    private final Uri mImageUri;
    private  final String mMemo;
    private String mFilename;
    private long mSize;

    public PhotoData(Uri uri, String memo){
        mImageUri = uri;
        mMemo = memo;
    }

    public Uri getImage(){
        return mImageUri;
    }

    public String getmMemo(){
        return mMemo;
    }

    public String getFileName(){
        return mFilename;
    }

    public void setFilename(String filename) {
        mFilename = filename;
    }

    public long getSize() {
        return mSize;
    }

    public void setSize(long mSize) {
        this.mSize = mSize;
    }
}
