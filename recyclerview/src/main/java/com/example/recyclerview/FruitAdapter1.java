package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 *
 * Created by mac_song on 2017/2/22.
 */
public class FruitAdapter1 extends ArrayAdapter<Fruit> {

    private int resourceId;

    public FruitAdapter1(Context context, int resource, List<Fruit> objects) {
        super(context, resource, objects);

        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Fruit fruit = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.fruit_image);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.fruit_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setImageResource(fruit.getImageId());
        viewHolder.textView.setText(fruit.getName());

        return convertView;

    }

    private class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
