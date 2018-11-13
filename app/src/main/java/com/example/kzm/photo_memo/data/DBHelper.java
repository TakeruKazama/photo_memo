package com.example.kzm.photo_memo.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private final String mTableName = "photo_table";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);

    }


    @Override
    public void onCreate(SQLiteDatabase db){
        try{
            db.execSQL(
                    String.format(
                            "create table %s ("+
                            "uri text primary key not null,"+
                            "memo text);",
                            mTableName)
            );

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public void insertData(String uri, String memo){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("uri", uri);
        values.put("memo", memo);
        db.insert(mTableName, null, values);
        db.close();
    }

    public List<PhotoData> readData(){

        List<PhotoData> items = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();


        //カーソルが取れない。
        Cursor c = db.query(mTableName, null, null, null, null,null, null);



        int uriIdx = c.getColumnIndex("uri");
        int memoIdx = c.getColumnIndex("memo");
        c.moveToFirst();
        while (!c.isAfterLast()){
            String uriStr = c.getString(uriIdx);
            Uri uri = Uri.parse(uriStr);
            String memo = c.getString(memoIdx);
            PhotoData data = new PhotoData(uri, memo);
            items.add(data);
            c.moveToNext();
        }
        c.close();
        db.close();

        return items;
    }

    public void deleteData(String uri){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(mTableName, "uri=?", new String[]{uri});
        db.close();
    }
}
