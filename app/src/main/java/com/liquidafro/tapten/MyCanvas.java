package com.liquidafro.tapten;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by bunibun on 16/11/14.
 */
public class MyCanvas extends View {

    Paint paint;
    Path path;
    float posX;
    float posY;
    float canvasX;
    float canvasY;
    int counter = 0;
    //LinearLayout layout;
    //TextView textView;
    long globalTime;
    boolean isTouchable = true;
    Bitmap juliaPic;
    Bitmap juliaPicSide;
    Bitmap scaledJuliaPic;
    Bitmap scaledJuliaPicSmall;
    Bitmap scaledJuliaPicSide;
    Bitmap knife;
    Bitmap scaledKnife;
    Bitmap blood;
    Bitmap scaledBlood;
    String timerText;
    int textPosY;
    boolean picTouched = false;
    int whichMode;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Typeface typeface;
    AssetManager assetManager;

    int currScore;

    public MyCanvas(Context context) {
        super(context);
        paint = new Paint();
        path = new Path();
        assetManager = context.getAssets();

        //layout = new LinearLayout(context);
        //textView = new TextView(context);
        //textView.setVisibility(View.VISIBLE);
        //textView.setText("Hello world");
        //textView.setX(width/2 - textView.getWidth()/2);
        //textView.setY(200);

        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        whichMode = preferences.getInt("mode", 5);

        //Julia
        if(whichMode == 2) {
            juliaPic = BitmapFactory.decodeResource(getResources(), R.drawable.juliagame);
            juliaPicSide = BitmapFactory.decodeResource(getResources(), R.drawable.juliagameside);
            scaledJuliaPic = scaleDown(juliaPic, 750, true);
            scaledJuliaPicSmall = scaleDown(juliaPic, 725, true);
            scaledJuliaPicSide = scaleDown(juliaPicSide, 650, true);
        }
        //Nellie
        else if(whichMode == 3){
            juliaPic = BitmapFactory.decodeResource(getResources(), R.drawable.nelliegame);
            juliaPicSide = BitmapFactory.decodeResource(getResources(), R.drawable.nelliegameside);
            scaledJuliaPic = scaleDown(juliaPic, 750, true);
            scaledJuliaPicSmall = scaleDown(juliaPic, 725, true);
            scaledJuliaPicSide = scaleDown(juliaPicSide, 650, true);
        }
        //else Normal

        knife = BitmapFactory.decodeResource(getResources(), R.drawable.knife);
        scaledKnife = scaleDown(knife, 175, true);
        blood = BitmapFactory.decodeResource(getResources(), R.drawable.blood);
        scaledBlood = scaleDown(blood, 175, true);

        //Counter
        final int[] secondsLeft = {0};
        CountDownTimer timer = new CountDownTimer(10000, 100) {
            @Override
            public void onTick(long l) {
                if (Math.round((float)l / 1000.0f) != secondsLeft[0])
                {
                    secondsLeft[0] = Math.round((float)l / 1000.0f);
                }
                globalTime = secondsLeft[0];
                Log.d("d", "" + l);
                Log.d("d", "d" + globalTime);
                invalidate();
            }

            @Override
            public void onFinish() {
                isTouchable = false;
                Log.d("d", "k" + globalTime);

                editor = preferences.edit();
                currScore = counter;
                int score1 = preferences.getInt("score1", 1);
                int score2 = preferences.getInt("score2", 1);
                int score3 = preferences.getInt("score3", 1);
                int score4 = preferences.getInt("score4", 1);
                int score5 = preferences.getInt("score5", 1);
                int score6 = preferences.getInt("score6", 1);
                int score7 = preferences.getInt("score7", 1);
                int score8 = preferences.getInt("score8", 1);
                int score9 = preferences.getInt("score9", 1);
                int score10 = preferences.getInt("score10", 1);

                if(currScore > score1){
                    editor.putInt("score1", counter);
                    Log.d("T", "s1");
                }else if(currScore > score2){
                    editor.putInt("score2", counter);
                    Log.d("T", "s2");
                }else if(currScore > score3){
                    editor.putInt("score3", counter);
                    Log.d("T", "s3");
                }else if(currScore > score4){
                    editor.putInt("score4", counter);
                }else if(currScore > score5){
                    editor.putInt("score5", counter);
                }else if(currScore > score6){
                    editor.putInt("score6", counter);
                }else if(currScore > score7){
                    editor.putInt("score7", counter);
                }else if(currScore > score8){
                    editor.putInt("score8", counter);
                }else if(currScore > score9){
                    editor.putInt("score9", counter);
                }else if(currScore > score10){
                    editor.putInt("score10", counter);
                }
                editor.commit();
                invalidate();
            }
        };
        timer.start();

        //layout.addView(textView);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasX = canvas.getWidth();
        canvasY = canvas.getHeight();

        paint.setColor(Color.parseColor("#ecf0f1"));
        canvas.drawPaint(paint);
        paint.setAntiAlias(true);

        if(globalTime != 0) {
            if(whichMode == 2 || whichMode == 3) {
                if (picTouched) {
                    canvas.drawBitmap(scaledJuliaPicSmall, canvas.getWidth() / 2 - 130, canvas.getHeight() / 2 - 345, null);
                    canvas.drawBitmap(scaledBlood, posX - 200, posY - 50, null);
                    canvas.drawBitmap(scaledKnife, posX - 130, posY - 50, null);
                } else {
                    canvas.drawBitmap(scaledJuliaPic, canvas.getWidth() / 2 - 135, canvas.getHeight() / 2 - 350, null);
                    canvas.drawBitmap(scaledKnife, posX - 130, posY - 50, null);
                }
            }else{
                //Draw circle
                paint.setColor(Color.RED);
                canvas.drawCircle(posX, posY, 50, paint);
            }
        }

        //Draw line
        /*
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(50);
        canvas.drawPath(path, paint);
        Log.d("t", "onDraw");
        */

        //Add click counter
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        String textCounter = String.valueOf(counter);
        paint.setTextSize(100);
        typeface = Typeface.createFromAsset(assetManager, "dense_regular.otf");
        paint.setTypeface(typeface);
        int textPosX = (canvas.getWidth() / 2);
        //((textPaint.descent() + textPaint.ascent()) / 2) is the distance from the baseline to the center.
        canvas.drawText(textCounter, textPosX , 150, paint);

        //Add results or timer
        if(globalTime == 0){
            if(whichMode == 2 || whichMode == 3) {
                canvas.drawBitmap(scaledJuliaPicSide, canvas.getWidth() / 2 - 300, canvas.getHeight() / 2 + 100, null);
                canvas.drawBitmap(scaledBlood, 225, 775, null);
                timerText = "Du lyckades skada\nhenne " + counter + " gånger";
                textPosY = 1000;
                paint.setTextSize(50);
                textPosX = 75;
                canvas.drawText(timerText, textPosX, textPosY, paint);
                canvas.drawText("Inte tillräckligt mycket...", 155, 1050, paint);
                paint.setTextSize(150);
                paint.setColor(Color.RED);
                canvas.drawText("KO!!!", 150, 350, paint);
                paint.setTextSize(75);
                paint.setColor(Color.BLUE);
                canvas.drawText("YOU GOT REKT!!", 300, 600, paint);
            }else{
                paint.setColor(Color.BLACK);
                timerText = "You got " + counter + " taps in 10 sec!";
                textPosY = 1000;
                paint.setTextSize(75);
                textPosX = 85;
                canvas.drawText(timerText, textPosX, textPosY, paint);
            }
        }else {
            timerText = String.valueOf(globalTime);
            paint.setTextSize(150);
            textPosX = (canvas.getWidth() / 2 - 20);
            textPosY = 1100;
            canvas.drawText(timerText, textPosX, textPosY, paint);
        }

        //paint.setTextSize(250);
        //textPosX = (canvas.getWidth() / 2);
        //int textPosY = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));

        //how big of an view
        //layout.measure(canvas.getWidth(), canvas.getHeight());
        //where to put it 0, 0 top and bottom getWidth getHeight
        //layout.layout(0, 0, canvas.getWidth(), canvas.getHeight());

        //canvas.translate(canvas.getWidth()/2, canvas.getWidth()/2);
        //layout.draw(canvas);


    }

    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(isTouchable) {
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    picTouched = true;
                    posX = event.getX();
                    posY = event.getY();
                    path.moveTo(event.getX(), event.getY());
                    Log.d("t", "down");
                    break;


                case MotionEvent.ACTION_MOVE:
                    picTouched = true;
                    posX = event.getX();
                    posY = event.getY();
                    path.lineTo(event.getX(), event.getY());
                    Log.d("e", "move");
                    break;

                case MotionEvent.ACTION_UP:
                    picTouched = false;
                    posX = event.getX();
                    posY = event.getY();
                    counter++;
                    Log.d("r", "up");
                    break;

            }
        }

        invalidate();
        Log.d("t", "onTouchEvent");
        return true;

        //this is shit
        //return super.onTouchEvent(event);


    }
}
