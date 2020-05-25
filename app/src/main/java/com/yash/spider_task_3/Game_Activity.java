package com.yash.spider_task_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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
    static int count_moves;
    TextView p1_name, p2_name;
    static String p1_Name, p2_Name;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        p1_name = findViewById(R.id.tv_player_1_name);
        p2_name = findViewById(R.id.tv_player_2_name);
        Intent intent = getIntent();
        p1_name.setText(String.valueOf(intent.getStringExtra("p1_name")));
        p2_name.setText(String.valueOf(intent.getStringExtra("p2_name")));
        p1_name.setTextColor(Color.RED);
        p2_name.setTextColor(Color.BLUE);
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
                    draw_cross_circle draw_cross_circle = new draw_cross_circle(Game_Activity.this, game_rl, x, y);
                    game_rl.addView(draw_cross_circle);
                    mp = new MediaPlayer();
                }
                return false;
            }
        });
    }
    public void new_game() {
        Intent intent = new Intent(Game_Activity.this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
