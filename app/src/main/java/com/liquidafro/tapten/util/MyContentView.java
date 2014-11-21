package com.liquidafro.tapten.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by bunibun on 10/10/14.
 */
public class MyContentView extends SurfaceView implements Runnable {


    SurfaceHolder holder;
    Thread t = null;
    boolean isRunning = true;

    public MyContentView(Context context){
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
            ///canvas.drawBitmap(circle, x, y, null);
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
