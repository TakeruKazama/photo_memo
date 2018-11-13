package com.example.kzm.photo_memo.photos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.kzm.photo_memo.R;
import com.example.kzm.photo_memo.addphoto.AddPhotoActivity;
import com.example.kzm.photo_memo.addphoto.AddPhotoPresenter;
import com.example.kzm.photo_memo.data.PhotoData;
import com.example.kzm.photo_memo.data.PhotoRepository;
import com.example.kzm.photo_memo.photodetail.PhotoDetailActivity;

import java.util.ArrayList;

public class PhotosActivity extends AppCompatActivity implements PhotosContract.Activity {

    private PhotosPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photos_act);
        PhotosFragment fragment = (PhotosFragment)
                getSupportFragmentManager().findFragmentById(R.id.photos_frag);
        fragment.setActivity(this);
        mPresenter = new PhotosPresenter(PhotoRepository.getInstance(getApplicationContext()), fragment, this);
    }

    public void showAddPhoto(){
        Intent intent = new Intent(this, AddPhotoActivity.class);
        startActivityForResult(intent, AddPhotoActivity.REQUEST_ADD_PHOTO);
    }

    public void showPhotoDetails(int index){
        Intent intent = new Intent(this, PhotoDetailActivity.class);
        intent.putExtra(PhotoDetailActivity.EXTRA_DATA_INDEX, index);
        startActivity(intent);
    }

    public void testToast(String str){
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
}
