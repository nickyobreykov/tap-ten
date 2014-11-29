package com.liquidafro.tapten;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liquidafro.tapten.R;

public class HighScoreActivity extends Activity {

    TextView score1;
    TextView score2;
    TextView score3;
    TextView score4;
    TextView score5;
    TextView score6;
    TextView score7;
    TextView score8;
    TextView score9;
    TextView score10;
    RelativeLayout relativeLayout;

    int currScore;
    int currNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_high_score);

        Log.d("d","gsd");
        //relativeLayout = (RelativeLayout) findViewById(R.id.highScoreRL);
        //relativeLayout.setBackgroundColor(Color.parseColor("#ecf0f1"));

        score1 = (TextView) findViewById(R.id.score1);
        score2 = (TextView) findViewById(R.id.score2);
        score3 = (TextView) findViewById(R.id.score3);
        score4 = (TextView) findViewById(R.id.score4);
        score5 = (TextView) findViewById(R.id.score5);
        score6 = (TextView) findViewById(R.id.score6);
        score7 = (TextView) findViewById(R.id.score7);
        score8 = (TextView) findViewById(R.id.score8);
        score9 = (TextView) findViewById(R.id.score9);
        score10 = (TextView) findViewById(R.id.score10);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        currScore = preferences.getInt("score1", 0);
        score1.setText("1. " + String.valueOf(currScore) + " Taps");
        currScore = preferences.getInt("score2", 0);
        score2.setText("2. " + String.valueOf(currScore) + " Taps");
        currScore = preferences.getInt("score3", 0);
        score3.setText("3. " + String.valueOf(currScore) + " Taps");
        currScore = preferences.getInt("score4", 0);
        score4.setText("4. " + String.valueOf(currScore) + " Taps");
        currScore = preferences.getInt("score5", 0);
        score5.setText("5. " + String.valueOf(currScore) + " Taps");
        currScore = preferences.getInt("score6", 0);
        score6.setText("6. " + String.valueOf(currScore) + " Taps");
        currScore = preferences.getInt("score7", 0);
        score7.setText("7. " + String.valueOf(currScore) + " Taps");
        currScore = preferences.getInt("score8", 0);
        score8.setText("8. " + String.valueOf(currScore) + " Taps");
        currScore = preferences.getInt("score9", 0);
        score9.setText("9. " + String.valueOf(currScore) + " Taps");
        currScore = preferences.getInt("score10", 0);
        score10.setText("10. " + String.valueOf(currScore) + " Taps");
        currScore = preferences.getInt("score" + currNum, 0);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.high_score, menu);
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
