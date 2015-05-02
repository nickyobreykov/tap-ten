package com.liquidafro.tapten;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by bunibun on 18/09/14.
 */
public class MySurfaceView extends SurfaceView implements Runnable{

    SurfaceHolder holder;
    Thread t = null;
    boolean isRunning = true;

    public MySurfaceView(Context context){
        super(context);
        holder = getHolder();
        Log.d("d", "jhg");
    }

    public void run(){
        while(isRunning){
            if(!holder.getSurface().isValid()){
                //continue gör att det skippar det som finns under, går till while
                continue;
            }
            Canvas canvas = holder.lockCanvas();
            canvas.drawColor(Color.RED);
            //canvas.drawBitmap(circlepng, x, y, null);
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
