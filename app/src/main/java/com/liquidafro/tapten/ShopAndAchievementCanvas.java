package com.liquidafro.tapten;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by bunibun on 01/12/14.
 */
public class ShopAndAchievementCanvas extends View{

    Paint paint;
    float posX, posY;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String[] colorArray;
    int[] rightStart;
    int[] leftStart;
    int colorNum;

    public final int RECT_X_MAX = 360;
    public final int RECT_X_MIN = 30;
    public final int RECT_Y_MIN = 30;
    public final int RECT_Y_MAX = 80;
    public final int WIDTH = 50;
    public final int WIDTH_AND_SPACE = WIDTH + 20;
    //change name?
    public final int PADDING = 3;
    public final int PADDINGTOP = WIDTH_AND_SPACE - 3;
    public final int PADDINGBOTTOM = WIDTH_AND_SPACE + 3;

    public ShopAndAchievementCanvas(Context context){
        super(context);

        paint = new Paint();

        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();

        colorArray = new String[]{"#1abc9c", "#2ecc71", "#3498db", "#9b59b6", "#34495e",
                "#f1c40f", "#e67e22", "#e74c3c", "#ecf0f1", "#95a5a6"};

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int tempX = canvas.getWidth() / 2;
        int tempY = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));
        tempX = tempX - 160;

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        paint.setAntiAlias(true);

        //init rightStart and leftStart
        rightStart = new int[5];
        leftStart = new int[5];
        //int tempRightStart = RECT_X_MIN + 50;
        int tempRightStart = tempX + WIDTH;
        int intPos = 0;

        /*
        int[] circleXPos = new int[5];
        int[] circleYPos = new int[10];
        int tempXCircle = (canvas.getWidth() / 2) - 250;

        for(int tempCircleXPos = tempXCircle; tempCircleXPos < (tempXCircle + (125 * 5)); tempCircleXPos += 125) {
            Log.d("t", "ta");
            circleXPos[intPos] = tempCircleXPos;
            circleYPos[intPos] = (canvas.getHeight() / 2) - 75;
            //tempXCircle += 75;
            intPos++;
            Log.d("t", "taa");
        }

        for(int i = 5; i < 10; i++){
            circleYPos[i] = (canvas.getHeight() / 2) + 75;
        }

        colorNum = 0;
        int a = 0;
        //Rad 1
        for (int i = 0; i < 5; i++) {
            paint.setColor(Color.parseColor(colorArray[colorNum]));
            canvas.drawCircle(circleXPos[i], circleYPos[i], 50, paint);
            colorNum++;
        }
        //Rad 2
        for (int i = 5; i < 10; i++) {
            paint.setColor(Color.parseColor(colorArray[colorNum]));
            canvas.drawCircle(circleXPos[a], circleYPos[i], 50, paint);
            a++;
            colorNum++;
        }
        */


        for(int tempLeftStart = tempX; tempLeftStart < (tempX + WIDTH_AND_SPACE * 5); tempLeftStart += 70) {
            rightStart[intPos] = tempRightStart;
            leftStart[intPos] = tempLeftStart;
            tempRightStart += 70;
            intPos++;
        }

        //Draw rects
        colorNum = 0;
        for(int i = 0; i < rightStart.length; i++) {
            paint.setColor(Color.parseColor(colorArray[colorNum]));
            canvas.drawRect(leftStart[i], RECT_Y_MIN, rightStart[i], RECT_Y_MAX, paint);
            colorNum++;
            //ändra rect_y_min och max till vanliga variabler, eller?
        }
        for(int i = 0; i < rightStart.length; i++){
            paint.setColor(Color.parseColor(colorArray[colorNum]));
            canvas.drawRect(leftStart[i], RECT_Y_MIN + 70, rightStart[i], RECT_Y_MAX + 70, paint);
            colorNum++;
        }

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.BLACK);
        if(posX >= leftStart[0] && posX <= rightStart[0] && posY >= RECT_Y_MIN && posY <= RECT_Y_MAX){
            canvas.drawRect(leftStart[0] - PADDING, RECT_Y_MIN - PADDING, rightStart[0] + PADDING, RECT_Y_MAX + PADDING, paint);
            editor.putInt("color", 1);
        }else if(posX >= leftStart[1] && posX <= rightStart[1] && posY >= RECT_Y_MIN && posY <= RECT_Y_MAX){
            canvas.drawRect(leftStart[1] - PADDING, RECT_Y_MIN - PADDING, rightStart[1] + PADDING, RECT_Y_MAX + PADDING, paint);
            editor.putInt("color", 2);
        }else if(posX >= leftStart[2] && posX <= rightStart[2] && posY >= RECT_Y_MIN && posY <= RECT_Y_MAX) {
            canvas.drawRect(leftStart[2] - PADDING, RECT_Y_MIN - PADDING, rightStart[2] + PADDING, RECT_Y_MAX + PADDING, paint);
            editor.putInt("color", 3);
        }else if(posX >= leftStart[3] && posX <= rightStart[3] && posY >= RECT_Y_MIN && posY <= RECT_Y_MAX) {
            canvas.drawRect(leftStart[3] - PADDING, RECT_Y_MIN - PADDING, rightStart[3] + PADDING, RECT_Y_MAX + PADDING, paint);
            editor.putInt("color", 4);
        }else if(posX >= leftStart[4] && posX <= rightStart[4] && posY >= RECT_Y_MIN && posY <= RECT_Y_MAX) {
            canvas.drawRect(leftStart[4] - PADDING, RECT_Y_MIN - PADDING, rightStart[4] + PADDING, RECT_Y_MAX + PADDING, paint);
            editor.putInt("color", 5);
        }else if(posX >= leftStart[0] && posX <= rightStart[0] && posY >= RECT_Y_MIN + WIDTH_AND_SPACE && posY <= RECT_Y_MAX + WIDTH_AND_SPACE) {
            canvas.drawRect(leftStart[0] - PADDING, RECT_Y_MIN + PADDINGTOP, rightStart[0] + PADDING, RECT_Y_MAX + PADDINGBOTTOM, paint);
            editor.putInt("color", 6);
        }else if(posX >= leftStart[1] && posX <= rightStart[1] && posY >= RECT_Y_MIN + WIDTH_AND_SPACE && posY <= RECT_Y_MAX + WIDTH_AND_SPACE) {
            canvas.drawRect(leftStart[1] - PADDING, RECT_Y_MIN + PADDINGTOP, rightStart[1] + PADDING, RECT_Y_MAX + PADDINGBOTTOM, paint);
            editor.putInt("color", 7);
        }else if(posX >= leftStart[2] && posX <= rightStart[2] && posY >= RECT_Y_MIN + WIDTH_AND_SPACE && posY <= RECT_Y_MAX + WIDTH_AND_SPACE) {
            canvas.drawRect(leftStart[2] - PADDING, RECT_Y_MIN + PADDINGTOP, rightStart[2] + PADDING, RECT_Y_MAX + PADDINGBOTTOM, paint);
            editor.putInt("color", 8);
        }else if(posX >= leftStart[3] && posX <= rightStart[3] && posY >= RECT_Y_MIN + WIDTH_AND_SPACE && posY <= RECT_Y_MAX + WIDTH_AND_SPACE) {
            canvas.drawRect(leftStart[3] - PADDING, RECT_Y_MIN + PADDINGTOP, rightStart[3] + PADDING, RECT_Y_MAX + PADDINGBOTTOM, paint);
            editor.putInt("color", 9);
        }else if(posX >= leftStart[4] && posX <= rightStart[4] && posY >= RECT_Y_MIN + WIDTH_AND_SPACE && posY <= RECT_Y_MAX + WIDTH_AND_SPACE) {
            canvas.drawRect(leftStart[4] - PADDING, RECT_Y_MIN + PADDINGTOP, rightStart[4] + PADDING, RECT_Y_MAX + PADDINGBOTTOM, paint);
            editor.putInt("color", 10);
        }
        editor.commit();


        /*
        paint.setColor(Color.parseColor(colorArray[0]));
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
        */

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
