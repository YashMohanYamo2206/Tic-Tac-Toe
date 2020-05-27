package com.yash.spider_task_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import static com.yash.spider_task_3.Game_Activity.getAllItems;

public class score extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private item_adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Database dbHelper = new Database(this);
        mDatabase = dbHelper.getWritableDatabase();
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new item_adapter(score.this, getAllItems());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.swapCursor(getAllItems());
    }
    public Cursor getAllItems() {
        return mDatabase.query(
                item_contract.ScoreEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                item_contract.ScoreEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }
}
