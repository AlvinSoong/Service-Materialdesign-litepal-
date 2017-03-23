package com.example.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ListView list_view;
    private FruitAdapter1 adapter;
    private List<Fruit> list_Fruit = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lsitview);
        init();
        adapter = new FruitAdapter1(this, R.layout.frute_style, list_Fruit);
        list_view = (ListView) findViewById(R.id.list_view);
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fruit fruit = list_Fruit.get(i);
                Intent intent = new Intent(MainActivity.this,RecyclerActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
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
