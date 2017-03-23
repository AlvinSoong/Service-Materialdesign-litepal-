package com.example.materialdesign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Yun[] yuns = {
            new Yun("图片1", R.drawable.yun1), new Yun("图片2", R.drawable.yun2), new Yun("图片3", R.drawable.yun3), new Yun("图片4", R.drawable.yun4),
            new Yun("图片5", R.drawable.yun5), new Yun("图片6", R.drawable.yun6), new Yun("图片7", R.drawable.yun7), new Yun("图片8", R.drawable.yun8),
            new Yun("图片9", R.drawable.yun9)
    };
    private List<Yun> mYunList = new ArrayList<>();
    private YunAdapter yunAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        GridLayoutManager manager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);
        init();
        yunAdapter = new YunAdapter(mYunList);
        recyclerView.setAdapter(yunAdapter);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshUI();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "删除", Snackbar.LENGTH_SHORT).setAction("撤销", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "撤销成功", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
        }

        navigationView.setCheckedItem(R.id.nav_call);  //默认选中
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                /*switch (item.getItemId()){
                    //每个侧边栏菜单的响应事件
                }*/
                return true;
            }
        });
    }

    private void init(){
        for (int i = 0;i<50;i++){
            Random random = new Random();
            int index = random.nextInt(yuns.length);
            mYunList.add(yuns[index]);
        }
    }

    //刷新函数
    private void refreshUI(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        init();
                        yunAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }

        }).start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                makeText(this, "back", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
//                Snackbar.make(item.getActionView(), "deleted", Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(MainActivity.this, "已撤销删除", Toast.LENGTH_SHORT).show();
//                    }
//                }).show();
                break;
            case R.id.settings:
                makeText(this, "settings", Toast.LENGTH_SHORT).show();
                break;

            default:

        }
        return true;
    }
}
