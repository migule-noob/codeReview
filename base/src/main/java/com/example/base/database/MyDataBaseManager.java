package com.example.base.database;

import android.content.Context;

import com.example.base.callback.ICallBack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyDataBaseManager {
    private final String TAG = this.getClass().getSimpleName();
    private static final ExecutorService pool = Executors.newCachedThreadPool();
    private MyDataBaseHelper helper;

    private MyDataBaseManager() {
    }

    private static class MyDataBaseManagerHolder {
        private static MyDataBaseManager INSTANCE = new MyDataBaseManager();
    }

    public static MyDataBaseManager getInstance() {
        return MyDataBaseManagerHolder.INSTANCE;
    }

    public void init (Context context) {
        helper = new MyDataBaseHelper(context);
    }

    public void insert(ICallBack.DataBaseCallBack callBack) {
        pool.execute(new Runnable() {
            @Override
            public void run() {
                helper.insert();
                callBack.state(true);
            }
        });
    }

    public void queryAll(ICallBack.DataBaseCallBack callBack) {
        pool.execute(new Runnable() {
            @Override
            public void run() {
                callBack.quertResult(helper.queryAll());
            }
        });
    }
}
