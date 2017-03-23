package com.example.materialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
  Created by mac_soong on 2017/2/26.
 */

public class Details extends AppCompatActivity {
    public static final String IMAGE_NAME = "IMAGE_NAME";
    public static final String IMAGE_ID = "image_id";
    private TextView tv_Details;
    private ImageView img_Details;
    String a = "hello";
    private String image_name;
    private int image_id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        image_name = intent.getStringExtra(IMAGE_NAME);
        image_id = intent.getIntExtra(IMAGE_ID, 0);

        tv_Details = (TextView) findViewById(R.id.tv_details);
        img_Details = (ImageView) findViewById(R.id.img_details);
        Glide.with(this).load(image_id).into(img_Details);
        init();
        tv_Details.setText(a);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_details);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_details);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(image_name);
    }

    private void init() {
        for (int i = 0; i < 300; i++) {
            a +=image_name;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}
