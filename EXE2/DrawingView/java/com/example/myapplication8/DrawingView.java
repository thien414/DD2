package com.example.myapplication8;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class DrawingView extends View {
    DrawingView(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint paint = new Paint();
        Path path = new Path();

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        canvas.drawCircle(100, 50, 25, paint);

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        path.moveTo(75, 75);
        path.lineTo(125, 75);
        path.lineTo(125, 125);
        path.lineTo(175, 125);
        path.lineTo(175, 175);
        path.lineTo(225, 175);
        path.lineTo(225, 225);
        path.lineTo(275, 225);
        path.lineTo(275, 275);
        path.lineTo(325, 275);
        path.lineTo(325, 325);
        path.lineTo(75, 325);
        path.close();
        canvas.drawPath(path, paint);

    }
}
