package com.example.mac_soong.bomb_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_Add,btn_Back,btn_Confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_widget();
    }

    private void init_widget(){
        btn_Add = (Button) findViewById(R.id.btn_add);
        btn_Add.setOnClickListener(this);
        btn_Back = (Button) findViewById(R.id.bt_back);
        btn_Back.setOnClickListener(this);
        btn_Confirm = (Button) findViewById(R.id.bt_confirm);
        btn_Confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
//            Intent intent = new Intent(this,)
            case R.id.btn_add:

        }
    }
}
