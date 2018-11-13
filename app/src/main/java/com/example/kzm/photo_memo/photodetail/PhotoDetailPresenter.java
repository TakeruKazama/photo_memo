package com.example.kzm.photo_memo.photodetail;

import android.content.Context;

import com.example.kzm.photo_memo.data.PhotoData;
import com.example.kzm.photo_memo.data.PhotoRepository;

import java.lang.ref.WeakReference;

public class PhotoDetailPresenter implements PhotoDetailContract.Presenter {
    private final PhotoRepository mPhotosRepository;
    private final PhotoDetailContract.View mPhotoDetailView;
    private final PhotoDetailContract.Activity mActivity;
    private final int mIndex;



    public PhotoDetailPresenter(PhotoRepository photoRepository,
                           PhotoDetailContract.View PhotoDetailFragment,
                           PhotoDetailContract.Activity PhotoDetailActivity,
                           int index){
        mIndex = index;
        mPhotosRepository = photoRepository;
        mPhotoDetailView=PhotoDetailFragment;
        mActivity = PhotoDetailActivity;
        mPhotoDetailView.setPresenter(this);
        mPhotoDetailView.setActivity(PhotoDetailActivity);
    }

    @Override
    public void start() {
        mPhotoDetailView.setPresenter(this);

        PhotoData data = mPhotosRepository.getPhotoData(mIndex);

        mPhotoDetailView.showPhotoImage(data.getImage());
        mPhotoDetailView.showPhotoMemo(data.getmMemo());
        mPhotoDetailView.showPhotoInfo(data.getFileName(), data.getSize());
    }
}
