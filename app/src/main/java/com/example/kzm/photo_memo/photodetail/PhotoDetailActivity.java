package com.example.kzm.photo_memo.photodetail;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.kzm.photo_memo.R;
import com.example.kzm.photo_memo.data.PhotoData;
import com.example.kzm.photo_memo.data.PhotoRepository;

public class PhotoDetailActivity extends AppCompatActivity implements PhotoDetailContract.Activity {
    public static final String EXTRA_DATA_INDEX = "EXTRA_DATA_INDEX";
    //private PhotoDetailPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_detail_act);

        int dataIndex = getIntent().getIntExtra(EXTRA_DATA_INDEX, -1);

        PhotoDetailFragment fragment = (PhotoDetailFragment) getSupportFragmentManager().findFragmentById(R.id.photo_detail_frag);
        fragment.setActivity(this);
        new PhotoDetailPresenter(PhotoRepository.getInstance(getApplicationContext()), fragment, this, dataIndex);
    }


}
