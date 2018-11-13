package com.example.kzm.photo_memo.addphoto;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kzm.photo_memo.R;
import com.example.kzm.photo_memo.data.PhotoRepository;


public class AddPhotoActivity extends AppCompatActivity implements AddPhotoContract.Activity{

    private AddPhotoPresenter mPresenter;

    static final int REQUEST_CODE = 1;
    public static final int REQUEST_ADD_PHOTO =1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_photo_act);
        AddPhotoFragment fragment = (AddPhotoFragment)
                getSupportFragmentManager().findFragmentById(R.id.add_photo_frag);
        mPresenter = new AddPhotoPresenter(PhotoRepository.getInstance(getApplicationContext()), fragment, this);
    }

    public void showPicker(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/jpeg");
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode != REQUEST_CODE)
            return;

        if (resultCode != RESULT_OK)
            return;

        Uri uri = data.getData();
        mPresenter.result(resultCode == RESULT_OK, uri);
    }

    public void finishWithResult(Boolean result){
        Intent intent = new Intent();
        setResult((result) ? RESULT_OK : RESULT_CANCELED, intent);
        finish();
    }
}



