package com.yash.spider_task_3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.MediaRouteButton;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Game_Activity extends AppCompatActivity {
    RelativeLayout game_rl;
    static int[][] moves;
    static item_adapter mAdapter;
    static int count_moves;
    TextView p1_name, p2_name,Score_p1_Name,Score_p2_Name;
    static TextView Score_winner;
    static String p1_Name, p2_Name, Winner = "";
    MediaPlayer mp;
    private static SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_);
        //tv=findViewById(R.id.no_data_tv);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        p1_name = findViewById(R.id.tv_player_1_name);
        p2_name = findViewById(R.id.tv_player_2_name);
        Intent intent = getIntent();
        Database db= new Database(this);
        mDatabase = db.getWritableDatabase();
        mAdapter = new item_adapter(this, getAllItems());
        p1_name.setText(String.valueOf(intent.getStringExtra("p1_name")));
        p2_name.setText(String.valueOf(intent.getStringExtra("p2_name")));
        p1_name.setTextColor(Color.RED);
        p2_name.setTextColor(Color.BLUE);
        Score_p1_Name = findViewById(R.id.text_view_player1);
        Score_p2_Name = findViewById(R.id.text_view_player2);
        Score_winner = findViewById(R.id.text_view_winner);
        p1_Name = String.valueOf(intent.getStringExtra("p1_name"));
        p2_Name = String.valueOf(intent.getStringExtra("p2_name"));
        game_rl = findViewById(R.id.Game_relativeLayout);
        moves = new int[3][3];
        count_moves = 0;
        DrawLines drawLines = new DrawLines(Game_Activity.this, game_rl);
        game_rl.addView(drawLines);
        game_rl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                if (x <= 50 || x >= game_rl.getWidth() - 50 || y <= game_rl.getHeight() / 4f || y >= 3 * game_rl.getHeight() / 4f) {
                    Toast.makeText(Game_Activity.this, "tap in the boxes", Toast.LENGTH_SHORT).show();
                } else {
                    //Score_p1_Name.setText(p1_Name);
                    //Score_p2_Name.setText(p2_Name);
                    draw_cross_circle draw_cross_circle = new draw_cross_circle(Game_Activity.this, game_rl, x, y);
                    game_rl.addView(draw_cross_circle);
                    mp = new MediaPlayer();
                }
                return false;
            }
        });
    }

    public static void GameOver(final Context context) {
        //Score_winner.setText(Winner);
        ContentValues cv = new ContentValues();
        cv.put(item_contract.ScoreEntry.COLUMN_PLAYER1_NAME, p1_Name);
        cv.put(item_contract.ScoreEntry.COLUMN_PLAYER2_NAME, p2_Name);
        cv.put(item_contract.ScoreEntry.COLUMN_WINNER, Winner);

        mDatabase.insert(item_contract.ScoreEntry.TABLE_NAME, null, cv);
        mAdapter.swapCursor(getAllItems());

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage("winner is player -" + Winner);
        dialog.setTitle("WINNER");
        dialog.setPositiveButton("NEW GAME",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Intent intent = new Intent(context, MainActivity.class);
                        ContextCompat.startActivity(context, intent, null);
                    }
                });
        dialog.setNegativeButton("REPLAY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, Game_Activity.class);
                intent.putExtra("p1_name", Game_Activity.p1_Name);
                intent.putExtra("p2_name", Game_Activity.p2_Name);
                ContextCompat.startActivity(context, intent, null);
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    public void newGame(View view) {
        Intent intent = new Intent(Game_Activity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public static Cursor getAllItems() {
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
