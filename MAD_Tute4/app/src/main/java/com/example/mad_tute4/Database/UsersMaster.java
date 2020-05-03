package com.example.mad_tute4.Database;

import android.provider.BaseColumns;

public final class UsersMaster {
    private UsersMaster(){}

    public static class Users implements BaseColumns{
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_UserName = "userName";
        public static final String COLUMN_NAME_Password = "password";
    }
}