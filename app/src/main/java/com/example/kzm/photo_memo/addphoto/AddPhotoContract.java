package com.example.kzm.photo_memo.addphoto;

import android.net.Uri;

public class AddPhotoContract {

    interface View {
        void setPresenter(Presenter presenter);
        void setActivity(Activity activity);
        void showPhoto(Uri photoImage);
    }

    interface Presenter{
        void start();
        void savePhoto(String memo);
    }

    interface Activity{
        void showPicker();
        void finishWithResult(Boolean result);
    }
}
