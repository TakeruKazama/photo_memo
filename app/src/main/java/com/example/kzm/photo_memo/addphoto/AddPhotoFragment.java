package com.example.kzm.photo_memo.addphoto;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.kzm.photo_memo.R;

public class AddPhotoFragment extends Fragment implements AddPhotoContract.View{

    private ImageView mImageView;
    private AddPhotoContract.Presenter mPresenter;
    private AddPhotoContract.Activity mActivity;
    private EditText mEditText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.add_photo_frag, container,false);
        mImageView = root.findViewById(R.id.addPhotoImageView);

        mEditText = root.findViewById(R.id.addPhotoMemoEditText);
        FloatingActionButton fab = root.findViewById(R.id.addPhotoFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.savePhoto(mEditText.getText().toString());
                mActivity.finishWithResult(true);
            }
        });

        return root;
    }

    @Override
    public void onResume(){
        super.onResume();
        mPresenter.start();
    }

    public void showPhoto(Uri photoImage){
        mImageView.setImageURI(photoImage);
    }

    public void setPresenter(AddPhotoContract.Presenter presenter){
        mPresenter = presenter;
    }

    public void setActivity(AddPhotoContract.Activity activity){
        mActivity = activity;
    }

}
