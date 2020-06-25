package com.dinocodeacademy.maingoquizagain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database {

    private static final String TABLE_NAME = "myDatabase";
    myDbHelper helper;

    public Database(Context context)
    {
        helper = new myDbHelper(context);
    }


    public long insertData(String name, String password)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.PASSWORD, password);

        long id = db.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public boolean login(String name, String password){
        SQLiteDatabase db = helper.getWritableDatabase();
        String count = "";
        Cursor c = db.rawQuery("select count(*) x from \n" +
                "myTable where name = ? and password = ?", new String[]{name, password});
        if (c.moveToFirst()) {
            do {
                count = c.getString(c.getColumnIndex("x"));
            } while (c.moveToNext());
        }
        c.close();
        if(Integer.parseInt(count) > 0){
            return true;
        }
        else {
            return false;
        }
    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";
        private static final String TABLE_NAME = "myTable";
        private static final int DATABASE_Version = 1;
        private static final String UID="_id";
        private static final String NAME = "Name";
        private static final String PASSWORD= "Password";
        private static final String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME +
                " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , "  + NAME + " VARCHAR(225)," + PASSWORD+" VARCHAR(225) )";
        // private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {

                db.execSQL(CREATE_TABLE);

            } catch (Exception e) {

            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          /* try {
               Message.message(context,"OnUpgrade");
               db.execSQL(DROP_TABLE);
               onCreate(db);
           }catch (Exception e) {
               Message.message(context,""+e);
                          }*/
        }


    }
}
