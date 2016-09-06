package com.lishiwei.westbund.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lishiwei.model.Gallery;
import com.lishiwei.westbund.Activity.ActivityArtScene;
import com.lishiwei.westbund.Activity.ActivityGalleryList;
import com.lishiwei.westbund.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lishiwei on 16/5/18.
 */
public class GalleryRecyclerAdapter extends RecyclerView.Adapter<GalleryRecyclerAdapter.ViewHolder> {

    private static final String TAG = GalleryRecyclerAdapter.class.getSimpleName();

    private List<Gallery> galleryList;
    private Context context;

    String from;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_gallery_recyclerview, null);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (from.equals("1"))
                {
                    Intent intent = new Intent(context, ActivityGalleryList.class);
                    Log.d(TAG, "onClick: " + holder.getLayoutPosition());
                    intent.putExtra("gallery", galleryList.get(holder.getLayoutPosition()).getId());
                    context.startActivity(intent);
                }
                else if (from.equals("2")){
                    Intent intent = new Intent(context, ActivityArtScene.class);
                    context.startActivity(intent);
                }

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvGalleryChineseName.setText(galleryList.get(position).getChineseName());
        holder.tvGalleryEnglishName.setText(galleryList.get(position).getEnglishName());
        holder.tvGalleryLocation.setText(galleryList.get(position).getLocation());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_Gallery_ChineseName)
        TextView tvGalleryChineseName;
        @Bind(R.id.tv_Gallery_EnglishName)
        TextView tvGalleryEnglishName;
        @Bind(R.id.tv_Gallery_Location)
        TextView tvGalleryLocation;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public List<Gallery> getGalleryList() {
        return galleryList;
    }

    public void setGalleryList(List<Gallery> galleryList) {
        this.galleryList = galleryList;
        notifyDataSetChanged();
    }

    public GalleryRecyclerAdapter(List<Gallery> galleryList, Context context) {
        this.galleryList = galleryList;
        this.context = context;
    }

    public GalleryRecyclerAdapter(Context context) {

        this.context = context;
    }
}
