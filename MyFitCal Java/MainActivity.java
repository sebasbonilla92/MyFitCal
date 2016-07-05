package com.example.sebz.MyFitCal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    static boolean unit_is_gram;
    static boolean unit_is_meter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calorieButton = (Button)findViewById(R.id.caloriesButton);
        calorieButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        Toast.makeText(MainActivity.this, "Calories", Toast.LENGTH_SHORT).show();
                        Intent myintent = new Intent(v.getContext(), Calories.class);
                        startActivityForResult(myintent, 0);
                    }
                }
        );

        Button bmrButton = (Button)findViewById(R.id.bmrButton);
            bmrButton.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            Toast.makeText(MainActivity.this, "BMR", Toast.LENGTH_SHORT).show();
                            Intent myintent = new Intent(v.getContext(),bmr.class);
                            startActivityForResult(myintent,0);
                        }
                    }
            );

        Button bmiButton = (Button)findViewById(R.id.bmiButton);
        bmiButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "BMI", Toast.LENGTH_SHORT).show();
                        Intent myintent = new Intent(v.getContext(),bmi.class);
                        startActivityForResult(myintent,0);

                    }
                }
        );

        Button bfButton = (Button)findViewById(R.id.bfButton);
        bfButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Body Fat", Toast.LENGTH_SHORT).show();
                        Intent myintent = new Intent(v.getContext(),bodyfat.class);
                        startActivityForResult(myintent,0);
                    }
                }
        );

        Button ffmiButton = (Button)findViewById(R.id.ffmiButton);
        ffmiButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "FFMI", Toast.LENGTH_SHORT).show();
                        Intent myintent = new Intent(v.getContext(),ffmi.class);
                        startActivityForResult(myintent,0);
                    }
                }
        );


    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.main_about){
            Intent main_to_about = new Intent(this,about.class);
            startActivityForResult(main_to_about,0);
        }

        else if(id == R.id.main_unit_change){

            Toast.makeText(this, "Change Units", Toast.LENGTH_SHORT).show();
            Intent main_to_changeUnits = new Intent(this,units.class);
            startActivityForResult(main_to_changeUnits, 0);
        }

        return super.onOptionsItemSelected(item);
    }
}
