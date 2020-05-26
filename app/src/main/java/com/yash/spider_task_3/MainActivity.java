package com.yash.spider_task_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText p1_name, p2_name,p_name;
    public static boolean isSinglePlayer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        p1_name = findViewById(R.id.et_player_1_name);
        p2_name = findViewById(R.id.et_player_2_name);
        p_name=findViewById(R.id.et_player_name);
    }

    public void multiPlayer(View view) {
        isSinglePlayer = false;
        if (!String.valueOf(p1_name.getText()).equals("") && !String.valueOf(p2_name.getText()).equals("")) {
            Intent intent = new Intent(MainActivity.this, Game_Activity.class);
            intent.putExtra("p1_name", String.valueOf(p1_name.getText()));
            intent.putExtra("p2_name", String.valueOf(p2_name.getText()));
            startActivity(intent);
            finish();
        }
        if (String.valueOf(p1_name.getText()).equals("")) {
            Toast.makeText(this, "fill everything required", Toast.LENGTH_SHORT).show();
            p1_name.setError("can't be empty");
        }
        if (String.valueOf(p2_name.getText()).equals("")) {
            Toast.makeText(this, "fill everything required", Toast.LENGTH_SHORT).show();
            p2_name.setError("can't be empty");
        }
    }

    public void singlePlayer(View view) {
        if (!String.valueOf(p_name.getText()).equals("")) {
            isSinglePlayer = true;
            Intent intent = new Intent(MainActivity.this, Game_Activity.class);
            intent.putExtra("p1_name", String.valueOf(p_name.getText()));
            intent.putExtra("p2_name", "COMPUTER");
            startActivity(intent);
            finish();
        }
        if (String.valueOf(p_name.getText()).equals("")) {
            Toast.makeText(this, "fill everything required", Toast.LENGTH_SHORT).show();
            p_name.setError("can't be empty");
        }
    }
}
