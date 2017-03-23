package com.example.litepal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_Add;
    private Button bt_Create;
    private Button bt_Show;

    private EditText edit_Name;
    private EditText edit_Id;

    private TextView tv_Show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_Add = (Button) findViewById(R.id.bt_add);
        bt_Create = (Button) findViewById(R.id.bt_create);
        bt_Show = (Button) findViewById(R.id.bt_show);

        tv_Show = (TextView) findViewById(R.id.tv_show);

        edit_Id = (EditText) findViewById(R.id.edit_id);
        edit_Name = (EditText) findViewById(R.id.edit_name);

        bt_Add.setOnClickListener(this);
        bt_Create.setOnClickListener(this);
        bt_Show.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_create:
                LitePal.getDatabase();
                break;
            case R.id.bt_add:
                Student student = new Student();
                if (edit_Id.getText().toString() != null && edit_Name.getText().toString() != null) {
                    student.setId(Integer.parseInt(edit_Id.getText().toString()));
                    student.setName(edit_Name.getText().toString());
                    student.save();
                } else {
                    Toast.makeText(MainActivity.this, "请输入", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_show:
                List<Student> students = DataSupport.findAll(Student.class);
                tv_Show.setText("");
                for (Student student1 : students) {
                    tv_Show.append(student1.getId() + "-" + student1.getName() + " ;");
//                    Log.d("TAG_1", student1.getName() + " and " + student1.getId());
                }
                break;
            default:
                break;
        }
    }
}
