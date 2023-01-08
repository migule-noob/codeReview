package com.example.base.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.base.callback.ICallBack;
import com.example.base.entity.Person;
import com.example.mytools.log.L;

import java.util.List;

public class DataBaseProvider extends ContentProvider {
    private static UriMatcher uriMatcher;
    public static final int TABLE_DIR = 1;
    private MyDataBaseHelper helper;
    private final String TAG = getClass().getSimpleName();

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.migule.provider","person_table",TABLE_DIR);
    }

    @Override
    public boolean onCreate() {
        helper = new MyDataBaseHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = null;
        if (uriMatcher.match(uri) == TABLE_DIR) {
            cursor = database.query("person_table", null, null, null, null, null, null);
            L.d(TAG,"provider query");
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
