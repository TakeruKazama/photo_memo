package com.example.kzm.photo_memo.photos;

import android.net.sip.SipSession;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kzm.photo_memo.R;
import com.example.kzm.photo_memo.data.PhotoData;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {
    private List<PhotoData> mData;
    private final Listener mListener;
    interface Listener {
        void onItemClicked(int index);
    }

    public PhotosAdapter(List<PhotoData> photos, Listener listener) {
        mData = photos;
        mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView textView;
        ViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.photoListImageView);
            textView = itemView.findViewById(R.id.photoListTextView);
        }
    }

    @Override @NonNull
    public PhotosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        PhotoData item = mData.get(holder.getAdapterPosition());
        holder.imageView.setImageURI(item.getImage());
        holder.textView.setText(item.getmMemo());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClicked(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount(){
        return mData.size();
    }

    public void replaceData(List<PhotoData> photos){
        mData = photos;
        notifyDataSetChanged();
    }
}
