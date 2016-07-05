package com.example.sebz.MyFitCal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sebz.MyFitCal.R;

public class units extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_units);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.8));

        Button doneButt = (Button)findViewById(R.id.unit_change_button);
        doneButt.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        RadioGroup unit_radgroup_weight = (RadioGroup) findViewById(R.id.unit_radio_weight_group);
                        int check_weight = unit_radgroup_weight.getCheckedRadioButtonId();

                        RadioButton poundButton = (RadioButton) findViewById(R.id.unit_pound_button);
                        boolean pound = poundButton.isChecked();

                        RadioButton kgButton = (RadioButton) findViewById(R.id.unit_kg_button);
                        boolean kg = kgButton.isChecked();

                        RadioGroup unit_radgroup_height = (RadioGroup) findViewById(R.id.unit_radio_weight_group);
                        int check_height = unit_radgroup_height.getCheckedRadioButtonId();

                        RadioButton inchButton = (RadioButton) findViewById(R.id.unit_inches_button);
                        boolean inch = inchButton.isChecked();

                        RadioButton meterButton = (RadioButton) findViewById(R.id.unit_meters_button);
                        boolean mts = meterButton.isChecked();

                        MainActivity.unit_is_gram = kgButton.isChecked();
                        MainActivity.unit_is_meter = meterButton.isChecked();

                        //should clear form
                        // change name of views


                        finish();

                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
