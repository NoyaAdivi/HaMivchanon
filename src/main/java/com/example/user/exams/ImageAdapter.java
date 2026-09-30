package com.example.user.exams;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.imageViewHolder> {
    private ArrayList<Image> images;
    private View.OnClickListener mOnItemClickListener;

    public ImageAdapter(ArrayList<Image> images) {
        this.images = images;
    }

    /**
     *View the pictures
     * @param parent Receiving the pictures
     * @param viewType Viewing the pictures
     * @return View the pictures
     */
    @NonNull
    @Override
    public imageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View imageView = LayoutInflater.from (parent.getContext()).inflate(R.layout.recycle_image, parent, false);
        return new ImageAdapter.imageViewHolder(imageView);
    }

    /**
     * Click the listener
     * @param itemClickListener Click the item
     */
    public void setmOnItemClickListener(View.OnClickListener itemClickListener){
        mOnItemClickListener= itemClickListener;
    }

    /**
     *Getting pictures from the database
     * @param holder get id
     * @param position get position
     */
    @Override
    public void onBindViewHolder(@NonNull imageViewHolder holder, int position) {
        Image image= images.get(position);
        holder.ivPicture.setImageResource(holder.itemView.getResources().getIdentifier(image.getLocation(),"drawable",holder.itemView.getContext().getPackageName()));

        Glide.with(holder.ivPicture.getContext())
                .load(image.getLocation())
                .into(holder.ivPicture);

    }


    @Override
    public int getItemCount() {
        return images.size();
    }

    public class imageViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivPicture;


        public imageViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPicture = (ImageView) itemView.findViewById(R.id.ivPicture);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);


        }
    }
}
