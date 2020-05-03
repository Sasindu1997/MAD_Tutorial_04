package com.example.mad_tute4.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.UserManager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "UserInfo.db";

    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + UsersMaster.Users.TABLE_NAME + " (" +
                        UsersMaster.Users._ID + " INTEGER PRIMARY KEY," +
                        UsersMaster.Users.COLUMN_NAME_UserName + " TEXT," +
                        UsersMaster.Users.COLUMN_NAME_Password + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addInfo(String UserName, String Password){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersMaster.Users.COLUMN_NAME_UserName,UserName);
        values.put(UsersMaster.Users.COLUMN_NAME_Password,Password);

        long newRowId = db.insert(UsersMaster.Users.TABLE_NAME,null,values);
    }
    public List ReadAllInfo(){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                UsersMaster.Users._ID,
                UsersMaster.Users.COLUMN_NAME_UserName,
                UsersMaster.Users.COLUMN_NAME_Password
        };
        String sortOrders = UsersMaster.Users.COLUMN_NAME_UserName + " DESC";

        Cursor cursor = db.query(
                UsersMaster.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrders
        );
        List userNames = new ArrayList<>();
        List passwords = new ArrayList();

        while(cursor.moveToNext()){
            String username = cursor.getString( cursor.getColumnIndexOrThrow( UsersMaster.Users.COLUMN_NAME_UserName));
            String password = cursor.getString( cursor.getColumnIndexOrThrow( UsersMaster.Users.COLUMN_NAME_Password ));
            userNames.add( username );
            passwords.add( password );
        }
        cursor.close();
        return userNames;

    }
    public void readInfo(){

    }
    public void deleteInfo(String userName){
        SQLiteDatabase db = getReadableDatabase();

        String selection = UsersMaster.Users.COLUMN_NAME_UserName + " LIKE ?";
        String[] selectionArgs = { userName };

        db.delete( UsersMaster.Users.TABLE_NAME, selection, selectionArgs );

    }
    public void updateInfo(String userName, String password){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put( UsersMaster.Users.COLUMN_NAME_Password, password);

        String selection = UsersMaster.Users.COLUMN_NAME_UserName + " LIKE ?";
        String[] selectionArgs = {userName};

        int count = db.update(
                UsersMaster.Users.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
    }

}
