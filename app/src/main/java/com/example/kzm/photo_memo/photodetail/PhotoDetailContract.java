package com.example.kzm.photo_memo.photodetail;

import android.net.Uri;

public class PhotoDetailContract {

    interface View {
        void setPresenter(Presenter presenter);
        void setActivity(Activity activity);
        void showPhotoImage(Uri image);
        void showPhotoInfo(String name, long size);
        void showPhotoMemo(String memo);
    }

    interface Presenter{
        void start();
    }

    interface Activity {
    }

}
