package com.liquidafro.tapten;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liquidafro.tapten.R;

public class TestPlayActivity extends Activity {

    int score = 0;
    TextView tvScore;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_test_play);
        setContentView(new DrawingView(this));

    }


    class DrawingView extends SurfaceView {

        SurfaceHolder surfaceHolder;
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Canvas canvas;
        //Rect r = new Rect(0, 0, getWidth(), getHeight());

        public DrawingView(Context context) {
            super(context);

            surfaceHolder = getHolder();
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.FILL);



            Log.d("s", "as");
            tvScore = new TextView(context);
            Log.d("s", "as2");
            //bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.touch_circle);
            tvScore.setText("0");
                Log.d("s", "as3");
            LinearLayout layout = new LinearLayout(context);
            //surfaceHolder.getSurface();
            Log.d("s", "a4s");
            if (surfaceHolder.getSurface().isValid()) {
                canvas = surfaceHolder.lockCanvas();
                Log.d("s", "as5");
                canvas.drawColor(Color.RED);
                Log.d("s", "as");
                surfaceHolder.unlockCanvasAndPost(canvas);
            }

            Log.d("s", "as6");
            layout.addView(tvScore);
            Log.d("s", "as7");
            setContentView(layout);

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                if (surfaceHolder.getSurface().isValid()) {
                    canvas = surfaceHolder.lockCanvas();
                    String tempScore = String.valueOf(score);
                    tvScore.setText(tempScore);
                    canvas.drawColor(Color.RED);
                    Drawable aa = drawCircle();
                    aa.draw(canvas);
                    canvas.drawCircle(event.getX(), event.getY(), 50, paint);
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            return false;
        }

        public Drawable drawCircle(){
            Context context = getContext();
            Resources res = context.getResources();
            Drawable tee;
            Drawable test = res.getDrawable(R.drawable.touch_circle);
            tee = test;
            return tee;
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test_play, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
