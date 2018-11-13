package com.example.kzm.photo_memo.addphoto;

import android.net.Uri;

import com.example.kzm.photo_memo.data.PhotoRepository;

public class AddPhotoPresenter implements AddPhotoContract.Presenter {
    private final PhotoRepository mPhotosRepository;
    private final AddPhotoContract.View mAddPhotoView;
    private final AddPhotoContract.Activity mAddPhotoActivity;

    public AddPhotoPresenter(PhotoRepository photoRepository,
                             AddPhotoContract.View addPhotoView,
                             AddPhotoContract.Activity addPhotoActivity){
        mPhotosRepository = photoRepository;
        mAddPhotoView = addPhotoView;
        mAddPhotoActivity = addPhotoActivity;
        mAddPhotoView.setPresenter(this);
        mAddPhotoView.setActivity(addPhotoActivity);
    }

    private boolean mIsStarted = false;

    public void start(){
        if (!mIsStarted){
            mAddPhotoActivity.showPicker();
            mIsStarted = true;
        }
    }

    public  void savePhoto(String memo){
        mPhotosRepository.savePhoto(memo);
    }

    public  void result(boolean result, Uri uri){
        if (!result)
            return;
        mPhotosRepository.setmTemporaryPhoto(uri);
        mAddPhotoView.showPhoto(uri);
    }
}
