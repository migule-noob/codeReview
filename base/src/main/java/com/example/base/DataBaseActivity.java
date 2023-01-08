package com.example.base;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.base.callback.ICallBack;
import com.example.base.database.MyDataBaseHelper;
import com.example.base.database.MyDataBaseManager;
import com.example.base.entity.Person;
import com.example.mytools.log.L;

import java.util.List;

public class DataBaseActivity extends AppCompatActivity implements View.OnClickListener, ICallBack.DataBaseCallBack {
    private final String TAG = this.getClass().getSimpleName();
    private Button create;
    private Button query;
    private MyDataBaseManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);
        manager = MyDataBaseManager.getInstance();
        manager.init(getApplicationContext());

        create = findViewById(R.id.create);
        create.setOnClickListener(this);
        query = findViewById(R.id.query);
        query.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("callback","123");
        setResult(RESULT_OK,intent);
        finish();
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create:{
                manager.insert(this);
                break;
            }
            case R.id.query:{
                manager.queryAll(this);
            }
            default:{
                break;
            }
        }
    }

    @Override
    public void state(boolean b) {
        L.d(TAG,b + "");
    }

    @Override
    public void quertResult(List<Person> personList) {
        L.d(TAG,personList.toString());
    }
}