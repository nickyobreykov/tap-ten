package com.liquidafro.tapten;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.liquidafro.tapten.R;
import com.liquidafro.tapten.util.MyContentView;

public class LastPlayActivity extends Activity implements View.OnTouchListener {

    MyContentView v;
    Bitmap circle;
    float x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = new MyContentView(this);
        Log.d("d", "gds");
        v.setOnTouchListener(this);
        Log.d("d", "gdwes");
        //circlepng = BitmapFactory.decodeResource(getResources(), R.drawable.circlepng);
        x = y = 0;
        Log.d("d", "ghedfdsas");
        setContentView(v);
        Log.d("d", "gheds");
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        v.pause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        v.resume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.last_play, menu);
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

    public class MySurfaceView2 extends SurfaceView implements Runnable{

        SurfaceHolder holder;
        Thread t = null;
        boolean isRunning = true;

        public MySurfaceView2(Context context){
            super(context);
            holder = getHolder();

            Log.d("d", "vsur");

        }

        public void run(){
            while(isRunning){
                if(!holder.getSurface().isValid()){
                    //continue gör att det skippar det som finns under, går till while
                    Log.d("d", "gds");
                    continue;
                }
                Canvas canvas = holder.lockCanvas();
                canvas.drawColor(Color.RED);
                Log.d("d", "gdwws");
                canvas.drawBitmap(circle, x, y, null);
                holder.unlockCanvasAndPost(canvas);
            }
        }

        public void pause(){
            isRunning = false;
            while(true){
                try{
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            t = null;
        }

        public void resume(){
            isRunning = true;
            t = new Thread(this);
            t.start();
        }

    }

}
