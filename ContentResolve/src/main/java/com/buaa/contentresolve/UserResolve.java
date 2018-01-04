package com.buaa.contentresolve;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UserResolve {

    private ContentResolver resolver;
    private Uri uri;

    public UserResolve(Context context) {
        resolver = context.getContentResolver();
        uri = Uri.parse("content://com.buaa.data.provider.userProdiver/user");
    }

    public void insert(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("age", user.getAge());
        values.put("phone", user.getPhone());
        resolver.insert(uri, values);
    }

    public void delete(User user) {
        resolver.delete(ContentUris.withAppendedId(uri, user.getUserId()), null, null);
    }

    public void update(User user) {
        ContentValues values = new ContentValues();
        values.put("age", user.getAge());
        resolver.update(ContentUris.withAppendedId(uri, user.getUserId()), values, null, null);
    }

    public List<User> query() {
        List<User> userList = new ArrayList<>();
        Cursor cursor = resolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            User user = new User();
            user.setUserId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setAge(cursor.getInt(2));
            user.setPhone(cursor.getString(3));
            userList.add(user);
        }
        return userList;
    }
}
