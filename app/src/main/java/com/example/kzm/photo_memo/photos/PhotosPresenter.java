package com.example.kzm.photo_memo.photos;

import com.example.kzm.photo_memo.data.PhotoData;
import com.example.kzm.photo_memo.data.PhotoRepository;

import java.util.List;

public class PhotosPresenter implements PhotosContract.Presenter{
    private final PhotoRepository mPhotosRepository;
    private final PhotosContract.View mPhotosView;
    private final PhotosContract.Activity mPhotosActivity;

    public PhotosPresenter(PhotoRepository photoRepository,
                             PhotosContract.View PhotosView,
                             PhotosContract.Activity PhotosActivity){
        mPhotosRepository = photoRepository;
        mPhotosView = PhotosView;
        mPhotosActivity = PhotosActivity;
        mPhotosView.setPresenter(this);
    }
    @Override
    public void start() {
        List<PhotoData> photos = mPhotosRepository.getPhotos();
        mPhotosView.showPhotos(photos);
    }

    public void removePhoto(int index){

    }
    public void openPhotoDetails(int index){
        mPhotosActivity.showPhotoDetails(index);
    }
    public PhotoRepository getPhotosRepository(){
        return mPhotosRepository;
    }
}
