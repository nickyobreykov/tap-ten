package com.liquidafro.tapten;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.liquidafro.tapten.R;

public class OwnSettingsActivity extends Activity {

    RadioButton normalRadioBtn;
    RadioButton juliaRadioBtn;
    RadioButton nellieRadioBtn;
    RadioGroup radioGroup;
    TextView quoteTV;
    Button saveSettingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide actionbar
        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        //getActionBar().hide();
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_own_settings);

        normalRadioBtn = (RadioButton) findViewById(R.id.normalRadioBtn);
        juliaRadioBtn = (RadioButton) findViewById(R.id.juliaRadioBtn);
        nellieRadioBtn = (RadioButton) findViewById(R.id.nellieRadioBtn);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        quoteTV = (TextView) findViewById(R.id.quoteTV);
        saveSettingsBtn = (Button) findViewById(R.id.saveSettingsBtn);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = preferences.edit();

        /*
        boolean normalMode = preferences.getBoolean("normalMode", false);
        boolean juliaMode = preferences.getBoolean("juliaMode", false);
        boolean nellieMode = preferences.getBoolean("nellieMode", false);
        */

        int whichMode = preferences.getInt("mode", 5);

        if(whichMode == 1){
            normalRadioBtn.toggle();
        }else if(whichMode == 2){
            juliaRadioBtn.toggle();
        }
        else if(whichMode == 3){
            nellieRadioBtn.toggle();
        }else{
            Log.d("t", "" + whichMode);
            Log.d("fel", "fel");
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId == R.id.normalRadioBtn) {
                    editor.putInt("mode", 1);
                    editor.commit();
                    quoteTV.setText("Normal Mode");
                }else if(checkedId == R.id.juliaRadioBtn){
                    editor.putInt("mode", 2);
                    editor.commit();
                    quoteTV.setText("\"Fan vad töntigt med spel!!!\" #juliasays");
                }else{
                    editor.putInt("mode", 3);
                    editor.commit();
                    quoteTV.setText("\"Nej, jag sa inte att det var töntigt!\" #pokerface");
                }
            }
        });

        saveSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
            }
        });

        /*
        editor.putBoolean("normalMode", normalRadioBtn.isChecked());
        editor.putBoolean("juliaMode", juliaRadioBtn.isChecked());
        editor.putBoolean("nellieMode", nellieRadioBtn.isChecked());
        editor.commit();
        */

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.own_settings, menu);
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
