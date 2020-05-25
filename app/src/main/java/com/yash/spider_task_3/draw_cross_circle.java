package com.yash.spider_task_3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import java.util.Locale;

import static com.yash.spider_task_3.SinglePlayer.findBestMove;

@SuppressLint("ViewConstructor")
public class draw_cross_circle extends View {
    Paint[] p;
    float x_touch_coordinate, y_touch_coordinate;
    RelativeLayout game_relativeLayout;
    TextToSpeech tts;

    public draw_cross_circle(Context context, RelativeLayout game_relativeLayout, float x_touch_coordinate, float y_touch_coordinate) {
        super(context);
        this.game_relativeLayout = game_relativeLayout;
        this.x_touch_coordinate = x_touch_coordinate;
        this.y_touch_coordinate = y_touch_coordinate;
        init();
    }

    private void init() {
        p = new Paint[2];
        p[0] = new Paint();
        p[1] = new Paint();
        p[0].setStyle(Paint.Style.STROKE);
        p[1].setStyle(Paint.Style.STROKE);
        p[0].setColor(Color.RED);
        p[1].setColor(Color.BLUE);
        p[0].setStrokeWidth(30);
        p[1].setStrokeWidth(30);
        p[0].setAntiAlias(true);
        p[1].setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!MainActivity.isSinglePlayer) {
            for (float i = 50; i < game_relativeLayout.getWidth() - 50; i += ((game_relativeLayout.getWidth() - 100) / 3f)) {
                for (float j = game_relativeLayout.getHeight() / 4f; j < 3 * game_relativeLayout.getHeight() / 4f; j += (game_relativeLayout.getHeight() / 6f)) {
                    int k = (int) ((i - 50) / ((game_relativeLayout.getWidth() - 100) / 3f));
                    int l = (int) ((j - game_relativeLayout.getHeight() / 4f) / (game_relativeLayout.getHeight() / 6f));

                    if (x_touch_coordinate > i && x_touch_coordinate < i + ((game_relativeLayout.getWidth() - 100) / 3f) && y_touch_coordinate > j && y_touch_coordinate < j + (game_relativeLayout.getHeight() / 6f) && Game_Activity.moves[k][l] == 0) {
                        //Toast.makeText(getContext(), k + " , " + l, Toast.LENGTH_SHORT).show();
                        if (Game_Activity.count_moves % 2 == 0) {
                            Game_Activity.moves[k][l] = 1;
                            canvas.drawCircle((i + ((game_relativeLayout.getWidth() - 100) / 3f) / 2f), (j + (game_relativeLayout.getHeight() / 6f) / 2f), 100, p[0]);
                            Game_Activity.count_moves++;
                            checkWinner();
                        } else if (Game_Activity.count_moves % 2 == 1) {
                            Game_Activity.moves[k][l] = 2;
                            canvas.drawLine(i + 50, j + 50, i + ((game_relativeLayout.getWidth() - 100) / 3f) - 50, j + (game_relativeLayout.getHeight() / 6f) - 50, p[1]);
                            canvas.drawLine(i + ((game_relativeLayout.getWidth() - 100) / 3f) - 50, j + 50, i + 50, j + (game_relativeLayout.getHeight() / 6f) - 50, p[1]);
                            Game_Activity.count_moves++;
                            checkWinner();
                        }
                    }
                }
            }
        } else {
            if (Game_Activity.count_moves % 2 == 0) {
                for (float i = 50; i < game_relativeLayout.getWidth() - 50; i += ((game_relativeLayout.getWidth() - 100) / 3f)) {
                    for (float j = game_relativeLayout.getHeight() / 4f; j < 3 * game_relativeLayout.getHeight() / 4f; j += (game_relativeLayout.getHeight() / 6f)) {
                        int k = (int) ((i - 50) / ((game_relativeLayout.getWidth() - 100) / 3f));
                        int l = (int) ((j - game_relativeLayout.getHeight() / 4f) / (game_relativeLayout.getHeight() / 6f));

                        if (x_touch_coordinate > i && x_touch_coordinate < i + ((game_relativeLayout.getWidth() - 100) / 3f) && y_touch_coordinate > j && y_touch_coordinate < j + (game_relativeLayout.getHeight() / 6f) && Game_Activity.moves[k][l] == 0) {
                            if (Game_Activity.count_moves % 2 == 0) {
                                Game_Activity.moves[k][l] = 1;
                                canvas.drawCircle((i + ((game_relativeLayout.getWidth() - 100) / 3f) / 2f), (j + (game_relativeLayout.getHeight() / 6f) / 2f), 100, p[0]);
                                Game_Activity.count_moves++;
                                checkWinner();
                                break;
                            }
                        }
                    }
                }
                if (Game_Activity.count_moves % 2 == 1 && Game_Activity.count_moves <= 9) {
                    SinglePlayer.Move move = findBestMove(Game_Activity.moves);
                    if (move.row != -1 && move.col != -1 && Game_Activity.moves[move.row][move.col] == 0) {
                        Game_Activity.moves[move.row][move.col] = 2;
                        if (move.row == 0 && move.col == 0 && Game_Activity.moves[move.row][move.col] != 1) {
                            canvas.drawLine(50 + 50, game_relativeLayout.getHeight() / 4f + 50, 50 + ((game_relativeLayout.getWidth() - 100) / 3f) - 50, game_relativeLayout.getHeight() / 4f + (game_relativeLayout.getHeight() / 6f) - 50, p[1]);
                            canvas.drawLine(50 + ((game_relativeLayout.getWidth() - 100) / 3f) - 50, game_relativeLayout.getHeight() / 4f + 50, 50 + 50, game_relativeLayout.getHeight() / 4f + (game_relativeLayout.getHeight() / 6f) - 50, p[1]);
                        } else if (move.row == 1 && move.col == 0 && Game_Activity.moves[move.row][move.col] != 1) {
                            canvas.drawLine(50 + ((game_relativeLayout.getWidth() - 100) / 3f) + 50, game_relativeLayout.getHeight() / 4f + 50, 50 + ((2f * game_relativeLayout.getWidth() - 100) / 3f) - 50, game_relativeLayout.getHeight() / 4f + (game_relativeLayout.getHeight() / 6f) - 50, p[1]);
                            canvas.drawLine(50 + ((2f * game_relativeLayout.getWidth() - 100) / 3f) - 50, game_relativeLayout.getHeight() / 4f + 50, 50 + ((game_relativeLayout.getWidth() - 100) / 3f) + 50, game_relativeLayout.getHeight() / 4f + (game_relativeLayout.getHeight() / 6f) - 50, p[1]);
                        } else if (move.row == 2 && move.col == 0 && Game_Activity.moves[move.row][move.col] != 1) {
                            canvas.drawLine(50 + ((2 * game_relativeLayout.getWidth() - 100) / 3f) + 50, game_relativeLayout.getHeight() / 4f + 50, ((3f * game_relativeLayout.getWidth() - 100) / 3f) - 50, game_relativeLayout.getHeight() / 4f + (game_relativeLayout.getHeight() / 6f) - 50, p[1]);
                            canvas.drawLine(((3f * game_relativeLayout.getWidth() - 100) / 3f) - 50, game_relativeLayout.getHeight() / 4f + 50, 50 + ((2 * game_relativeLayout.getWidth() - 100) / 3f) + 50, game_relativeLayout.getHeight() / 4f + (game_relativeLayout.getHeight() / 6f) - 50, p[1]);
                        } else if (move.row == 0 && move.col == 1 && Game_Activity.moves[move.row][move.col] != 1) {
                            canvas.drawLine(50 + 50, game_relativeLayout.getHeight() / 4f + (game_relativeLayout.getHeight() / 6f) + 50, 50 + ((game_relativeLayout.getWidth() - 100) / 3f) - 50, game_relativeLayout.getHeight() / 4f + (2 * game_relativeLayout.getHeight() / 6f) - 50, p[1]);
                            canvas.drawLine(50 + ((game_relativeLayout.getWidth() - 100) / 3f) - 50, game_relativeLayout.getHeight() / 4f + (game_relativeLayout.getHeight() / 6f) + 50, 50 + 50, game_relativeLayout.getHeight() / 4f + (2 * game_relativeLayout.getHeight() / 6f) - 50, p[1]);
                        } else if (move.row == 1 && move.col == 1 && Game_Activity.moves[move.row][move.col] != 1) {
                            canvas.drawLine(50 + ((game_relativeLayout.getWidth() - 100) / 3f) + 50, game_relativeLayout.getHeight() / 4f + (game_relativeLayout.getHeight() / 6f) + 50, 50 + ((2f * game_relativeLayout.getWidth() - 100) / 3f) - 50, game_relativeLayout.getHeight() / 4f + (2 * game_relativeLayout.getHeight() / 6f) - 50, p[1]);
                            canvas.drawLine(50 + ((2f * game_relativeLayout.getWidth() - 100) / 3f) - 50, game_relativeLayout.getHeight() / 4f + (game_relativeLayout.getHeight() / 6f) + 50, 50 + ((game_relativeLayout.getWidth() - 100) / 3f) + 50, game_relativeLayout.getHeight() / 4f + (2 * game_relativeLayout.getHeight() / 6f) - 50, p[1]);
                        } else if (move.row == 2 && move.col == 1 && Game_Activity.moves[move.row][move.col] != 1) {
                            canvas.drawLine(50 + ((2 * game_relativeLayout.getWidth() - 100) / 3f) + 50, game_relativeLayout.getHeight() / 4f + (game_relativeLayout.getHeight() / 6f) + 50, ((3f * game_relativeLayout.getWidth() - 100) / 3f) - 50, game_relativeLayout.getHeight() / 4f + (2 * game_relativeLayout.getHeight() / 6f) - 50, p[1]);
                            canvas.drawLine(((3f * game_relativeLayout.getWidth() - 100) / 3f) - 50, game_relativeLayout.getHeight() / 4f + (game_relativeLayout.getHeight() / 6f) + 50, 50 + ((2 * game_relativeLayout.getWidth() - 100) / 3f) + 50, game_relativeLayout.getHeight() / 4f + (2 * game_relativeLayout.getHeight() / 6f) - 50, p[1]);
                        } else if (move.row == 0 && move.col == 2 && Game_Activity.moves[move.row][move.col] != 1) {
                            canvas.drawLine(50 + 50, game_relativeLayout.getHeight() / 4f + (2 * game_relativeLayout.getHeight() / 6f) + 50, 50 + ((game_relativeLayout.getWidth() - 100) / 3f) - 50, game_relativeLayout.getHeight() / 4f + (3 * game_relativeLayout.getHeight() / 6f) - 50, p[1]);
                            canvas.drawLine(50 + ((game_relativeLayout.getWidth() - 100) / 3f) - 50, game_relativeLayout.getHeight() / 4f + (2 * game_relativeLayout.getHeight() / 6f) + 50, 50 + 50, game_relativeLayout.getHeight() / 4f + (3 * game_relativeLayout.getHeight() / 6f) - 50, p[1]);
                        } else if (move.row == 1 && move.col == 2) {
                            canvas.drawLine(50 + ((game_relativeLayout.getWidth() - 100) / 3f) + 50, game_relativeLayout.getHeight() / 4f + (2 * game_relativeLayout.getHeight() / 6f) + 50, 50 + ((2f * game_relativeLayout.getWidth() - 100) / 3f) - 50, game_relativeLayout.getHeight() / 4f + (3 * game_relativeLayout.getHeight() / 6f) - 50, p[1]);
                            canvas.drawLine(50 + ((2f * game_relativeLayout.getWidth() - 100) / 3f) - 50, game_relativeLayout.getHeight() / 4f + (2 * game_relativeLayout.getHeight() / 6f) + 50, 50 + ((game_relativeLayout.getWidth() - 100) / 3f) + 50, game_relativeLayout.getHeight() / 4f + (3 * game_relativeLayout.getHeight() / 6f) - 50, p[1]);
                        } else if (move.row == 2 && move.col == 2 && Game_Activity.moves[move.row][move.col] != 1) {
                            canvas.drawLine(50 + ((2 * game_relativeLayout.getWidth() - 100) / 3f) + 50, game_relativeLayout.getHeight() / 4f + (2 * game_relativeLayout.getHeight() / 6f) + 50, ((3f * game_relativeLayout.getWidth() - 100) / 3f) - 50, game_relativeLayout.getHeight() / 4f + (3 * game_relativeLayout.getHeight() / 6f) - 50, p[1]);
                            canvas.drawLine(((3f * game_relativeLayout.getWidth() - 100) / 3f) - 50, game_relativeLayout.getHeight() / 4f + (2 * game_relativeLayout.getHeight() / 6f) + 50, 50 + ((2 * game_relativeLayout.getWidth() - 100) / 3f) + 50, game_relativeLayout.getHeight() / 4f + (3 * game_relativeLayout.getHeight() / 6f) - 50, p[1]);
                        }
                    }
                    //Toast.makeText(getContext(), move.row + " , " + move.col, Toast.LENGTH_SHORT).show();
                    Game_Activity.count_moves++;
                    checkWinner();
                }
            }
        }
    }

    private void checkWinner() {
        for (int i = 0; i < 3; i++) {
            if ((Game_Activity.moves[i][0] == (Game_Activity.moves[i][1])) && (Game_Activity.moves[i][1] == (Game_Activity.moves[i][2])) && Game_Activity.moves[i][0] != 0) {
                final int k = Game_Activity.moves[i][0];
                tts = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            int result = tts.setLanguage(Locale.US);
                            if (result == TextToSpeech.LANG_MISSING_DATA ||
                                    result == TextToSpeech.LANG_NOT_SUPPORTED) {
                                Toast.makeText(getContext(), "weird name", Toast.LENGTH_SHORT).show();
                            } else {
                                if (k == 1) {

                                    tts.speak(Game_Activity.p1_Name + " is winner", TextToSpeech.QUEUE_FLUSH, null, null);
                                } else if (k == 2) {
                                    tts.speak(Game_Activity.p2_Name + " is winner", TextToSpeech.QUEUE_FLUSH, null, null);
                                }
                            }
                        } else
                            Log.e("error", "Initilization Failed!");
                    }
                });


                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setMessage("winner is player -" + Game_Activity.moves[i][0]);
                dialog.setTitle("WINNER");
                dialog.setPositiveButton("NEW GAME",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                ContextCompat.startActivity(getContext(), intent, null);
                            }
                        });
                AlertDialog alertDialog = dialog.create();
                alertDialog.setCancelable(false);
                alertDialog.show();
                return;
            }
        }
        for (int i = 0; i < 3; i++) {
            if ((Game_Activity.moves[0][i] == (Game_Activity.moves[1][i])) && (Game_Activity.moves[1][i] == (Game_Activity.moves[2][i])) && Game_Activity.moves[0][i] != 0) {
                final int k = Game_Activity.moves[0][i];
                tts = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            int result = tts.setLanguage(Locale.US);
                            if (result == TextToSpeech.LANG_MISSING_DATA ||
                                    result == TextToSpeech.LANG_NOT_SUPPORTED) {
                                Toast.makeText(getContext(), "weird name", Toast.LENGTH_SHORT).show();
                            } else {
                                if (k == 1) {
                                    tts.speak(Game_Activity.p1_Name + " is winner", TextToSpeech.QUEUE_FLUSH, null, null);
                                } else if (k == 2) {
                                    tts.speak(Game_Activity.p2_Name + " is winner", TextToSpeech.QUEUE_FLUSH, null, null);
                                }
                            }
                        } else
                            Log.e("error", "Initilization Failed!");
                    }
                });
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setMessage("winner is player -" + Game_Activity.moves[0][i]);
                dialog.setTitle("WINNER");
                dialog.setPositiveButton("NEW GAME",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                ContextCompat.startActivity(getContext(), intent, null);
                            }
                        });
                AlertDialog alertDialog = dialog.create();
                alertDialog.setCancelable(false);
                alertDialog.show();
                return;
            }
        }
        if ((Game_Activity.moves[0][0] == Game_Activity.moves[1][1]) && (Game_Activity.moves[1][1] == Game_Activity.moves[2][2]) && Game_Activity.moves[0][0] != 0) {
            final int k = Game_Activity.moves[0][0];
            tts = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        int result = tts.setLanguage(Locale.US);
                        if (result == TextToSpeech.LANG_MISSING_DATA ||
                                result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Toast.makeText(getContext(), "weird name", Toast.LENGTH_SHORT).show();
                        } else {
                            if (k == 1) {
                                tts.speak(Game_Activity.p1_Name + " is winner", TextToSpeech.QUEUE_FLUSH, null, null);
                            } else if (k == 2) {
                                tts.speak(Game_Activity.p2_Name + " is winner", TextToSpeech.QUEUE_FLUSH, null, null);
                            }
                        }
                    } else
                        Log.e("error", "Initilization Failed!");
                }
            });

            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setMessage("winner is player -" + Game_Activity.moves[0][0]);
            dialog.setTitle("WINNER");
            dialog.setPositiveButton("NEW GAME",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            ContextCompat.startActivity(getContext(), intent, null);
                        }
                    });
            AlertDialog alertDialog = dialog.create();
            alertDialog.setCancelable(false);
            alertDialog.show();
        } else if ((Game_Activity.moves[0][2] == Game_Activity.moves[1][1]) && (Game_Activity.moves[1][1] == Game_Activity.moves[2][0]) && Game_Activity.moves[0][2] != 0) {
            final int k = Game_Activity.moves[2][0];
            tts = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        int result = tts.setLanguage(Locale.US);
                        if (result == TextToSpeech.LANG_MISSING_DATA ||
                                result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Toast.makeText(getContext(), "weird name", Toast.LENGTH_SHORT).show();
                        } else {
                            if (k == 1) {
                                tts.speak(Game_Activity.p1_Name + " is winner", TextToSpeech.QUEUE_FLUSH, null, null);
                            } else if (k == 2) {
                                tts.speak(Game_Activity.p2_Name + " is winner", TextToSpeech.QUEUE_FLUSH, null, null);
                            }
                        }
                    } else
                        Log.e("error", "Initilization Failed!");
                }
            });
            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setMessage("winner is player -" + Game_Activity.moves[0][2]);
            dialog.setTitle("WINNER");
            dialog.setPositiveButton("NEW GAME",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            ContextCompat.startActivity(getContext(), intent, null);
                        }
                    });
            AlertDialog alertDialog = dialog.create();
            alertDialog.setCancelable(false);
            alertDialog.show();

        } else if (Game_Activity.count_moves >= 9) {

            tts = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        int result = tts.setLanguage(Locale.US);
                        if (result == TextToSpeech.LANG_MISSING_DATA ||
                                result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Toast.makeText(getContext(), "weird name", Toast.LENGTH_SHORT).show();
                        } else {
                            tts.speak("it's a draw", TextToSpeech.QUEUE_FLUSH, null, null);
                        }
                    } else
                        Log.e("error", "Initilization Failed!");
                }
            });
            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setTitle("DRAW");
            dialog.setPositiveButton("NEW GAME",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            ContextCompat.startActivity(getContext(), intent, null);
                        }
                    });
            AlertDialog alertDialog = dialog.create();
            alertDialog.setCancelable(false);
            alertDialog.show();
        }
    }

}
