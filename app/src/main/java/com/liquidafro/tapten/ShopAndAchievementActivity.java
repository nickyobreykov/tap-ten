package com.liquidafro.tapten;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ShopAndAchievementActivity extends Activity {

    TextView testtv;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View shopCanvas = new ShopAndAchievementCanvas(this);

        setContentView(shopCanvas);
        //setContentView(R.layout.activity_shop_and_achievement);

        RelativeLayout shopLayout = (RelativeLayout) findViewById(R.id.shopLayout);

        //View firstCircle = (View) findViewById(R.id.firstCircle);
        View a = (View) findViewById(R.id.firstCircle);
        //ShapeDrawable b = (ShapeDrawable) a.getBackground();
        //firstCircle.setBackgroundColor(Color.parseColor("#1abc9c"));
        Drawable newCircle = this.getResources().getDrawable(R.drawable.circle);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.circle);

        //View view = new SandboxView(this.getActivity(), bitmap);
        //shopLayout.addView(newCircle);


        //GradientDrawable bgShape = (GradientDrawable) a.getBackground();
        //bgShape.setColor(Color.parseColor("#1abc9c"));


        //newCircle.setColorFilter(Integer.parseInt("#1abc9c"), PorterDuff.Mode.MULTIPLY);
        //firstCircle.setBackground(newCircle);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shop_and_achievement, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
