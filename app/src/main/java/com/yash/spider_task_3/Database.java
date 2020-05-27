package com.yash.spider_task_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.yash.spider_task_3.item_contract.*;
import androidx.annotation.Nullable;

import static com.yash.spider_task_3.item_contract.*;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "SCORE.db";
    public static final int DATABASE_VERSION = 1;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_SCORE_TABLE = "CREATE TABLE " +
                ScoreEntry.TABLE_NAME + " (" +
                ScoreEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ScoreEntry.COLUMN_PLAYER1_NAME + " TEXT NOT NULL, " +
                ScoreEntry.COLUMN_PLAYER2_NAME + " TEXT NOT NULL, " +
                ScoreEntry.COLUMN_WINNER + " TEXT NOT NULL, " +
                ScoreEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        db.execSQL(SQL_CREATE_SCORE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ScoreEntry.TABLE_NAME);
        onCreate(db);
    }
}
