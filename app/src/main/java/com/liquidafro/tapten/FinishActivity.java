package com.liquidafro.tapten;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class FinishActivity extends Activity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        int score = preferences.getInt("tempResult", 0);
        TextView scoreTV = (TextView) findViewById(R.id.scoreTV);
        TextView highScoreTV = (TextView) findViewById(R.id.showHighScoreTV);
        TextView highScoreTV2 = (TextView) findViewById(R.id.showHighScoreTV2);
        Button restartBtn = (Button) findViewById(R.id.restartBtn);
        Button homeBtn = (Button) findViewById(R.id.homeBtn);

        final Intent playIntent = new Intent(this, PlayActivity.class);
        final Intent mainIntent = new Intent(this, MainActivity.class);

        switch (preferences.getInt("color", 0)){
            case 1:
                scoreTV.setTextColor(Color.parseColor("#1abc9c"));
                break;
            case 2:
                scoreTV.setTextColor(Color.parseColor("#2ecc71"));
                break;
            case 3:
                scoreTV.setTextColor(Color.parseColor("#3498db"));
                break;
            case 4:
                scoreTV.setTextColor(Color.parseColor("#9b59b6"));
                break;
            case 5:
                scoreTV.setTextColor(Color.parseColor("#34495e"));
                break;
            case 6:
                scoreTV.setTextColor(Color.parseColor("#f1c40f"));
                break;
            case 7:
                scoreTV.setTextColor(Color.parseColor("#e67e22"));
                break;
            case 8:
                scoreTV.setTextColor(Color.parseColor("#e74c3c"));
                break;
            case 9:
                scoreTV.setTextColor(Color.parseColor("#ecf0f1"));
                break;
            case 10:
                scoreTV.setTextColor(Color.parseColor("#95a5a6"));
                break;
        }

        scoreTV.setText("" + score);

        if(preferences.getInt("tempPlace", -1) > 0){
            highScoreTV.setText("You've made it to the high score list!");
            highScoreTV2.setText("Place " + preferences.getInt("tempPlace", -1) + "!");
        }else{
            highScoreTV.setText("");
            highScoreTV2.setText("");
        }

        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //create counter before game starts 3, 2, 1 Go!
                startActivity(playIntent);

            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(mainIntent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_finish, menu);
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
