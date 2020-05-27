package com.yash.spider_task_3;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class item_adapter extends RecyclerView.Adapter<item_adapter.item_view_holder> {

    private Context mContext;
    private Cursor mCursor;

    public item_adapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    public class item_view_holder extends RecyclerView.ViewHolder {
        public TextView p1_Name;
        public TextView winner;
        public TextView p2_Name;

        public item_view_holder(@NonNull View itemView) {
            super(itemView);
            p1_Name = itemView.findViewById(R.id.text_view_player1);
            p2_Name = itemView.findViewById(R.id.text_view_player2);
            winner = itemView.findViewById(R.id.text_view_winner);
        }
    }

    @NonNull
    @Override
    public item_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_score_item, parent, false);
        return new item_view_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull item_view_holder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }
        String p1_name = mCursor.getString(mCursor.getColumnIndex(item_contract.ScoreEntry.COLUMN_PLAYER1_NAME));
        String p2_name = mCursor.getString(mCursor.getColumnIndex(item_contract.ScoreEntry.COLUMN_PLAYER2_NAME));
        String winner = mCursor.getString(mCursor.getColumnIndex(item_contract.ScoreEntry.COLUMN_WINNER));
        holder.p1_Name.setText(p1_name);
        holder.p2_Name.setText(p2_name);
        holder.winner.setText(winner);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}
