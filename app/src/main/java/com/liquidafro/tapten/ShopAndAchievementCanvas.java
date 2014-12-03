package com.liquidafro.tapten;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by bunibun on 01/12/14.
 */
public class ShopAndAchievementCanvas extends View{

    Paint paint;
    float posX, posY;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public final int RECT_X_MAX = 360;

    public ShopAndAchievementCanvas(Context context){
        super(context);

        paint = new Paint();

        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        paint.setAntiAlias(true);

        for(int i = 0; i < RECT_X_MAX; i = i + 70){

        }
        paint.setColor(Color.parseColor("#1abc9c"));
        canvas.drawRect(30, 30, 80, 80, paint);
        paint.setColor(Color.parseColor("#2ecc71"));
        canvas.drawRect(100, 30, 150, 80, paint);
        paint.setColor(Color.parseColor("#3498db"));
        canvas.drawRect(170, 30, 220, 80, paint);
        paint.setColor(Color.parseColor("#9b59b6"));
        canvas.drawRect(240, 30, 290, 80, paint);
        paint.setColor(Color.parseColor("#34495e"));
        canvas.drawRect(310, 30, 360, 80, paint);

        paint.setColor(Color.parseColor("#f1c40f"));
        canvas.drawRect(30, 100, 80, 150, paint);
        paint.setColor(Color.parseColor("#e67e22"));
        canvas.drawRect(100, 100, 150, 150, paint);
        paint.setColor(Color.parseColor("#e74c3c"));
        canvas.drawRect(170, 100, 220, 150, paint);
        paint.setColor(Color.parseColor("#ecf0f1"));
        canvas.drawRect(240, 100, 290, 150, paint);
        paint.setColor(Color.parseColor("#95a5a6"));
        canvas.drawRect(310, 100, 360, 150, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.BLACK);
        if(posX >= 30 && posX <= 80 && posY >= 30 && posY <= 80){
            canvas.drawRect(27, 27, 83, 83, paint);
            editor.putInt("color", 1);
            editor.commit();
        }else if(posX >= 100 && posX <= 150 && posY >= 30 && posY <= 80){
            canvas.drawRect(97, 27, 153, 83, paint);
            editor.putInt("color", 2);
            editor.commit();
        }else if(posX >= 170 && posX <= 220 && posY >= 30 && posY <= 80) {
            canvas.drawRect(167, 27, 223, 83, paint);
            editor.putInt("color", 3);
            editor.commit();
        }else if(posX >= 170 && posX <= 220 && posY >= 30 && posY <= 80) {
            canvas.drawRect(167, 27, 223, 83, paint);
            editor.putInt("color", 3);
            editor.commit();
        }else if(posX >= 170 && posX <= 220 && posY >= 30 && posY <= 80) {
            canvas.drawRect(167, 27, 223, 83, paint);
            editor.putInt("color", 3);
            editor.commit();
        }else if(posX >= 170 && posX <= 220 && posY >= 30 && posY <= 80) {
            canvas.drawRect(167, 27, 223, 83, paint);
            editor.putInt("color", 3);
            editor.commit();
        }



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                posX = event.getX();
                posY = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                posX = event.getX();
                posY = event.getY();
                break;

            case MotionEvent.ACTION_UP:
                posX = event.getX();
                posY = event.getY();
                break;

        }

        invalidate();
        return true;
    }
}
