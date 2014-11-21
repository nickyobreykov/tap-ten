package com.liquidafro.tapten;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends Activity {

    int storedPreference;
    int whichMode;
    Typeface typeface;
    ImageView settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide actionbar
        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        //  getActionBar().hide();
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        final Intent intent = new Intent(this, PlayActivity.class);
        final TextView tvStartText = (TextView) findViewById(R.id.tvStartText);
        typeface = Typeface.createFromAsset(getAssets(), "lovelo_black.otf");
        tvStartText.setTypeface(typeface);
        RelativeLayout mainRL = (RelativeLayout) findViewById(R.id.mainRelativeLayout);
        final Animation scaleAlphaAnim = AnimationUtils.loadAnimation(this, R.anim.scale_alpha_anim);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("mode", 1);
        editor.commit();
        whichMode = 1;
        Log.d("ttt", "" + whichMode);

        mainRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvStartText.startAnimation(scaleAlphaAnim);
                startActivity(intent);

                Log.d("test", "click");
            }
        });

        settingsBtn = (ImageView) findViewById(R.id.settingsBtn);
        final Intent settingsIntent = new Intent(this, OwnSettingsActivity.class);
        Button highScoreBtn = (Button) findViewById(R.id.highScoreBtn);
        final Intent highScoreIntent = new Intent(this, HighScoreActivity.class);

        highScoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(highScoreIntent);
                Log.d("testa", "" + whichMode);
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(settingsIntent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                Log.d("test", "" + whichMode);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        whichMode = preferences.getInt("mode", 5);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
