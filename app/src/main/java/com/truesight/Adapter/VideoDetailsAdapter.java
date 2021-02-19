package com.truesight.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.truesight.Model.Item;
import com.truesight.R;
import com.truesight.Video;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class VideoDetailsAdapter extends RecyclerView.Adapter<VideoDetailsAdapter.VideoDetailsViewHolder> {
    private Context context;
    private List<Item> videoDetailsList;

    public VideoDetailsAdapter(Context context, List<Item> videoDetailsList) {
        this.context = context;
        this.videoDetailsList = videoDetailsList;
    }

    @NonNull
    @Override
    public VideoDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item,parent,false);
        return new VideoDetailsViewHolder(view);
    }


    public void onBindViewHolder(@NonNull VideoDetailsViewHolder holder,final int position) {
        holder.publishedAt.setText(videoDetailsList.get(position).getSnippet().getPublishedAt());
        holder.description.setText(videoDetailsList.get(position).getSnippet().getDescription());
        holder.title.setText(videoDetailsList.get(position).getSnippet().getTitle());

        // memakai glide untuk mengatur thumbnail
        Glide.with(context)
                .load(videoDetailsList
                    .get(position)
                    .getSnippet().getThumbnails().getMedium().getUrl())
                .into(holder.thumbnail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Video.class);
                intent.putExtra("videoID", videoDetailsList.get(position).getId().getVideoId());
                context.startActivity(intent);
            }
        });
    }


    public int getItemCount(){
        return videoDetailsList.size();
    }

    public class VideoDetailsViewHolder extends RecyclerView.ViewHolder{

        private TextView publishedAt, title, description;
        private CircleImageView thumbnail;


        public VideoDetailsViewHolder(View itemView) {
            super(itemView);

            publishedAt = itemView.findViewById(R.id.publishedAt);
            title  = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            thumbnail = itemView.findViewById(R.id.thumbnail);
        }

    }

}
