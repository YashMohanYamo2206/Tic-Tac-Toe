package com.yash.spider_task_3;

import android.provider.BaseColumns;

public class item_contract {
    public item_contract() {
    }

    public static final class ScoreEntry implements BaseColumns {
        public static final String TABLE_NAME = "score";
        public static final String COLUMN_PLAYER1_NAME = "p1name";
        public static final String COLUMN_PLAYER2_NAME = "p2name";
        public static final String COLUMN_WINNER = "winner";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
