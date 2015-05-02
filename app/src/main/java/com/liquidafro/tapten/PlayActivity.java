package com.liquidafro.tapten;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.liquidafro.tapten.util.SystemUiHider;

import org.w3c.dom.Text;

public class PlayActivity extends Activity {

    int score = 0;
    TextView tvScore;
    ImageView i;
    AdView adView;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide actionbar
        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        //getActionBar().hide();
        Log.d("e", "dsa");

        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);

        relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));

        View myCanvas = new MyCanvas(this);

        //define ad view parameters
        RelativeLayout.LayoutParams adParams =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);


        //ta bort, byt till adview i addview(textView, adParams);
        TextView textView = new TextView(this);
        textView.setText("Ad");
        textView.setTextSize(50);
        textView.setId(R.id.text_view);

        RelativeLayout.LayoutParams canvasParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        canvasParams.addRule(RelativeLayout.ABOVE, R.id.text_view);


        relativeLayout.addView(myCanvas, canvasParams);
        relativeLayout.addView(textView, adParams);

        setContentView(relativeLayout);
        //Fungerar
        //DrawingView test = new DrawingView(this);
        //setContentView(test);


        //final Animation circleExpand = AnimationUtils.loadAnimation(this, R.anim.scale_alpha_anim);

        //final RelativeLayout playRL = (RelativeLayout) findViewById(R.id.playRelativeLayout);

        /*

        playRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int x = (int) view.getX();
                int y = (int) view.getY();
                i = new ImageView(PlayActivity.this);
                i.setImageResource(R.drawable.touch_circle);
                i.setAdjustViewBounds(true); // set the ImageView bounds to match the Drawable's dimensions
                //i.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                //        ViewGroup.LayoutParams.WRAP_CONTENT));
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.leftMargin = x;
                params.topMargin = y;
                i.setLayoutParams(params);
                /*
                i.setX(x);
                i.setY(y);
                float currX = convertPixelsToDp(x, PlayActivity.this);
                int nextX = (int) currX;
                float currY = convertPixelsToDp(y, PlayActivity.this);
                int nextY = (int) currY;
                */
        /*
                i.layout(x, y, 0, 0);
                Log.d("Play", "Touched");
                score++;
                String tempScore = String.valueOf(score);

                Log.v("d", "dfds");
                Log.d("t", x + ", " + y);
                tvScore.setText(tempScore);
                tvScore.startAnimation(circleExpand);
                playRL.addView(i);
                i.startAnimation(circleExpand);
            }
        });
        */



    }

    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }

    public class DrawingView extends SurfaceView {

        private final SurfaceHolder surfaceHolder;
        private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Drawable circle = getResources().getDrawable(R.drawable.touch_circle);

        public DrawingView(Context context) {
            super(context);
            surfaceHolder = getHolder();
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                if (surfaceHolder.getSurface().isValid()) {
                    Canvas canvas = surfaceHolder.lockCanvas();
                    canvas.drawColor(Color.WHITE);
                    canvas.drawCircle(event.getX(), event.getY(), 50, paint);
                    circle.draw(canvas);
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            return false;
        }


    }


    /*
    public boolean onTouchEvent(MotionEvent e){

        float x = e.getX();
        float y = e.getY();

        Log.d("touch", x + " " + y);

        return true;

    }
    */

/*
    public void playClick(View v){
        int x = (int) v.getX();
        int y = (int) v.getY();
        Log.d("t", x + ", " + y);
    }
*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.play, menu);
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
