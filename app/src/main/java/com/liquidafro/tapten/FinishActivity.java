package com.liquidafro.tapten;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
        Button restartBtn = (Button) findViewById(R.id.restartBtn);
        Button homeBtn = (Button) findViewById(R.id.homeBtn);
        scoreTV.setText("You got " + score + " taps!");

        final Intent playIntent = new Intent(this, PlayActivity.class);
        final Intent mainIntent = new Intent(this, MainActivity.class);

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
