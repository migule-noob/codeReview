package com.example.apptest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.ContentObservable;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

import com.example.mytools.log.L;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button obsQuery;
    private final String TAG = this.getClass().getSimpleName();
    private ContentResolver contentResolver;
    private Uri uri;
    private MyContentObs myContentObs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentResolver = this.getContentResolver();
        uri = Uri.parse("content://com.migule.provider/person_table");
        myContentObs = new MyContentObs(new Handler(Looper.getMainLooper()));
        contentResolver.registerContentObserver(uri,true,myContentObs);

        obsQuery = findViewById(R.id.obs_query);
        obsQuery.setOnClickListener(this);
    }

    @SuppressLint("Range")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.obs_query:{
                L.d(TAG,"obs query");
                Cursor cursor = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    cursor = contentResolver.query(uri, null, null, null);
                }
                while (cursor.moveToNext()) {
                   cursor.getString(cursor.getColumnIndex("name"));
                }
                break;
            }
            default: {
                break;
            }
        }
    }

    private class MyContentObs extends ContentObserver {

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public MyContentObs(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            L.d(TAG,"数据变化");

            super.onChange(selfChange);
        }
    }

    @Override
    protected void onDestroy() {
        contentResolver.unregisterContentObserver(myContentObs);
        super.onDestroy();
    }
}