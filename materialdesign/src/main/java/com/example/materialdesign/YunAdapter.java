package com.example.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 Created by mac_soong on 2017/2/25.
 */

class YunAdapter extends RecyclerView.Adapter<YunAdapter.ViewHolder> {

    private List<Yun> mYunList;
    private Context mContext;

    YunAdapter(List<Yun> mYunList) {
        this.mYunList = mYunList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CardView cardView;
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            imageView = (ImageView) itemView.findViewById(R.id.yun_image);
            textView = (TextView) itemView.findViewById(R.id.yun_name);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.image_style, parent, false);
         final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Yun yun = mYunList.get(position);
                Intent intent = new Intent(mContext, Details.class);
                intent.putExtra(Details.IMAGE_NAME,yun.getName());
                intent.putExtra(Details.IMAGE_ID,yun.getImageId());
                mContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Yun yun = mYunList.get(position);
        holder.textView.setText(yun.getName());
        Glide.with(mContext).load(yun.getImageId()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mYunList.size();
    }


}
