package com.yash.spider_task_3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.RelativeLayout;

@SuppressLint("ViewConstructor")
public class DrawLines extends View {
    Paint p, p1;
    RelativeLayout relativeLayout;

    public DrawLines(Context context, RelativeLayout relativeLayout) {
        super(context);
        this.relativeLayout = relativeLayout;
        init();
    }

    private void init() {

        p1 = new Paint();
        p1.setColor(Color.YELLOW);
        p1.setStrokeWidth(10);
        p1.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(50, relativeLayout.getHeight() / 4f, relativeLayout.getWidth() - 50f, 3 * relativeLayout.getHeight() / 4f, p1);
        canvas.drawLine(relativeLayout.getWidth() / 3f, relativeLayout.getHeight() / 4f, relativeLayout.getWidth() / 3f, 3 * relativeLayout.getHeight() / 4f, p1);
        canvas.drawLine(2 * relativeLayout.getWidth() / 3f, relativeLayout.getHeight() / 4f, 2 * relativeLayout.getWidth() / 3f, 3 * relativeLayout.getHeight() / 4f, p1);
        canvas.drawLine(50, 5 * relativeLayout.getHeight() / 12f, relativeLayout.getWidth() - 50f, 5 * relativeLayout.getHeight() / 12f, p1);
        canvas.drawLine(50, 7 * relativeLayout.getHeight() / 12f, relativeLayout.getWidth() - 50f, 7 * relativeLayout.getHeight() / 12f, p1);
    }
}
