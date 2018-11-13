package com.example.kzm.photo_memo.photos;

import android.net.Uri;

import com.example.kzm.photo_memo.data.PhotoData;
import com.example.kzm.photo_memo.data.PhotoRepository;

import java.util.List;

public class PhotosContract {
    interface View {
        void showPhotos(List<PhotoData> photos);
        void setPresenter(PhotosContract.Presenter presenter);
        void setActivity(PhotosContract.Activity activity);
    }

    interface Presenter{
        void start();
        void removePhoto(int index);
        void openPhotoDetails(int index);
        PhotoRepository getPhotosRepository();
    }

    interface Activity{
        void showAddPhoto();
        void showPhotoDetails(int index);
        void testToast(String str);
    }

}
