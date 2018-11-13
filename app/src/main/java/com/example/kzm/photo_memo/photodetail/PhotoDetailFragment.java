package com.example.kzm.photo_memo.photodetail;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kzm.photo_memo.R;

import java.util.Locale;

public class PhotoDetailFragment extends Fragment implements PhotoDetailContract.View {
    private PhotoDetailContract.Presenter mPresenter;
    private PhotoDetailContract.Activity mActivity;

    private ImageView mImageView;
    private TextView mPhotoInfoText;
    private TextView mPotoMemoText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onStart() {
        super.onStart();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.photo_detail_frag, container,false);
        mImageView = root.findViewById(R.id.photoDetailImageView);
        mPhotoInfoText = root.findViewById(R.id.photoDetailInfoTextView);
        mPotoMemoText = root.findViewById(R.id.photoDetailMemoTextView);
        return root;
    }

    @Override
    public void onResume(){
        super.onResume();
        mPresenter.start();
    }

    public void showPhotoImage(Uri image){
        mImageView.setImageURI(image);
    }

    public void showPhotoInfo(String name, long size){
        String infoStr = "Photo info:\n" +
                "\t" + "Name: " + name +"\n" +
                "\t" + "Size" + String.format(Locale.JAPAN, "%.1fMB", size/1024.0/1024.0);
        mPhotoInfoText.setText(infoStr);
    }

    public void showPhotoMemo(String memo){
        mPotoMemoText.setText(memo);
    }

    public void setPresenter(PhotoDetailContract.Presenter presenter){
        mPresenter = presenter;
    }
    public void setActivity(PhotoDetailContract.Activity activity){ mActivity = activity; }
}
