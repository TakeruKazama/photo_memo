package com.example.kzm.photo_memo.data;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class PhotoRepository {
    private static PhotoRepository mInstance = null;

    private final List<PhotoData> mPhotos;
    private final WeakReference<Context> mContextRef;
    private final DBHelper mDBHelper;


    private  PhotoRepository(Context context) {
        mContextRef = new WeakReference<>(context);
        mPhotos = new ArrayList<>();
        mDBHelper = new DBHelper(context, "photo.db", null, 1);
        try {
            mPhotos.addAll(mDBHelper.readData());
        }catch (Exception e){}

    }

    static  public  PhotoRepository getInstance(Context context){
        if (mInstance == null)
            mInstance = new PhotoRepository(context);

        return mInstance;
    }

    public List<PhotoData> getPhotos(){
        return mPhotos;
    }

    private Uri mTemporaryPhotoUri;

    public void savePhoto(String memo){
        mDBHelper.insertData(mTemporaryPhotoUri.toString(), memo);
        mPhotos.add(new PhotoData(mTemporaryPhotoUri, memo));
        mTemporaryPhotoUri = null;
    }

    public void setmTemporaryPhoto(Uri uri){
        mTemporaryPhotoUri = uri;
    }

    private void getPhotoInfo(PhotoData data){
        Cursor c =
                MediaStore.Images.Media.query(mContextRef.get().getContentResolver(), data.getImage(), null);
        c.moveToFirst();
        /*ファイル名取得*/
        String filename = c.getString(c.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME));
        data.setFilename(filename);

        /*サイズ取得*/
        long size = c.getLong(c.getColumnIndex(MediaStore.MediaColumns.SIZE));
        data.setSize(size);
        c.close();
    }

    public PhotoData getPhotoData(int index){
        PhotoData data = mPhotos.get(index);
        if(data.getFileName() == null){
            getPhotoInfo(data);
        }
        return data;
    }
}
