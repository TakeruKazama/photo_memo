package com.example.kzm.photo_memo.photos;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kzm.photo_memo.R;
import com.example.kzm.photo_memo.data.PhotoData;
import com.example.kzm.photo_memo.data.PhotoRepository;

import java.util.ArrayList;
import java.util.List;

public class PhotosFragment extends Fragment implements PhotosContract.View {

    private PhotosContract.Presenter mPresenter;
    private PhotosContract.Activity mActivity;
    private PhotosAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mAdapter = new PhotosAdapter(new ArrayList<PhotoData>(0), mPhotoItemListner);
    }

    @Override
    public void onStart() {
        super.onStart();

        mAdapter.replaceData(mPresenter.getPhotosRepository().getPhotos());
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.photos_frag, container,false);


        FloatingActionButton photosFab = root.findViewById(R.id.photosFab);
        FloatingActionButton cameraFab = root.findViewById(R.id.cameraFab);

        photosFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.showAddPhoto();
            }
        });

        cameraFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.testToast("未実装");
            }
        });

        RecyclerView recyclerView = root.findViewById(R.id.photoRecyclerView);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        ItemTouchHelper touchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView view, @NonNull RecyclerView.ViewHolder holder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder holder, int direction) {
                        int index = holder.getAdapterPosition();
                        mPresenter.removePhoto(index);
                    }
                });
        touchHelper.attachToRecyclerView(recyclerView);

        return root;
    }

    private final PhotosAdapter.Listener mPhotoItemListner = new PhotosAdapter.Listener() {
        @Override
        public void onItemClicked(int index) {
            mPresenter.openPhotoDetails(index);
        }
    };



    public void showPhotos(List<PhotoData> photos){

    }


    public void setPresenter(PhotosContract.Presenter presenter){
        mPresenter = presenter;
    }
    public void setActivity(PhotosContract.Activity activity){ mActivity = activity; }
}
