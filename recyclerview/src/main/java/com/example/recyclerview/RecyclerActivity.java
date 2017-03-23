package com.example.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**Created by mac_song on 2017/2/22.
 */
public class RecyclerActivity extends Activity {
    private List<Fruit> list_Fruit = new ArrayList<>();
    private FruitAdapter2 adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        adapter2 = new FruitAdapter2(list_Fruit);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.review);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter2);
    }

    private void init() {
        for (int i =0 ;i<4;i++){
            Fruit fruit = new Fruit("Apple",R.mipmap.ic_launcher);
            list_Fruit.add(fruit);
            Fruit fruit2 = new Fruit("Orange",R.mipmap.ic_launcher);
            list_Fruit.add(fruit2);
            Fruit fruit3 = new Fruit("Pear",R.mipmap.ic_launcher);
            list_Fruit.add(fruit3);
        }
    }
}
