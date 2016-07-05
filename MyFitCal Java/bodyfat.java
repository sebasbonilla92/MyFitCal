package com.example.sebz.MyFitCal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class bodyfat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodyfat);

        TextView bf_weight_unit = (TextView)findViewById(R.id.bf_pounds_view);


        if(MainActivity.unit_is_gram){
            bf_weight_unit.setText("Kilograms");
        }

        if(MainActivity.unit_is_meter){
            //change all the inches to centimeters
            TextView waist_unit_v = (TextView)findViewById(R.id.bf_waist_unit_view);
            TextView wrist_unit_v = (TextView)findViewById(R.id.bf_wrist_unit_view);
            TextView hip_unit_v = (TextView)findViewById(R.id.bf_hip_unit_view);
            TextView forearm_unit_v = (TextView)findViewById(R.id.bf_forearm_unit_view);
            waist_unit_v.setText("Centimeters");
            wrist_unit_v.setText("Centimeters");
            hip_unit_v.setText("Centimeters");
            forearm_unit_v.setText("Centimeters");
        }


        //make options invisible when male is selected.
        RadioButton bf_rb_male = (RadioButton) findViewById(R.id.bf_male_button);
        bf_rb_male.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        EditText wrist_num_v = (EditText) findViewById(R.id.bf_wrist_num);
                        wrist_num_v.setVisibility(View.INVISIBLE);

                        EditText hip_num_v = (EditText) findViewById(R.id.bf_hip_num);
                        hip_num_v.setVisibility(View.INVISIBLE);

                        EditText forearm_num_v = (EditText) findViewById(R.id.bf_forearm_num);
                        forearm_num_v.setVisibility(View.INVISIBLE);

                        TextView wrist_v = (TextView)findViewById(R.id.bf_wrist_view);
                        wrist_v.setVisibility(View.INVISIBLE);

                        TextView hip_v = (TextView)findViewById(R.id.bf_hip_view);
                        hip_v.setVisibility(View.INVISIBLE);

                        TextView forearm_v = (TextView)findViewById(R.id.bf_forearm_view);
                        forearm_v.setVisibility(View.INVISIBLE);

                        TextView bf_req_v = (TextView)findViewById(R.id.bf_required_view);
                        bf_req_v.setVisibility(View.INVISIBLE);

                        TextView wrist_unit_v = (TextView)findViewById(R.id.bf_wrist_unit_view);
                        wrist_unit_v.setVisibility(View.INVISIBLE);

                        TextView hip_unit_v = (TextView)findViewById(R.id.bf_hip_unit_view);
                        hip_unit_v.setVisibility(View.INVISIBLE);

                        TextView forearm_unit_v = (TextView)findViewById(R.id.bf_forearm_unit_view);
                        forearm_unit_v.setVisibility(View.INVISIBLE);

                    }
                }
        );


        //when female is selected show the other measurements
        RadioButton bf_rb_female = (RadioButton) findViewById(R.id.bf_fem_button);
        bf_rb_female.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        EditText wrist_num_v = (EditText) findViewById(R.id.bf_wrist_num);
                        wrist_num_v.setVisibility(View.VISIBLE);

                        EditText hip_num_v = (EditText) findViewById(R.id.bf_hip_num);
                        hip_num_v.setVisibility(View.VISIBLE);

                        EditText forearm_num_v = (EditText) findViewById(R.id.bf_forearm_num);
                        forearm_num_v.setVisibility(View.VISIBLE);

                        TextView wrist_v = (TextView)findViewById(R.id.bf_wrist_view);
                        wrist_v.setVisibility(View.VISIBLE);

                        TextView hip_v = (TextView)findViewById(R.id.bf_hip_view);
                        hip_v.setVisibility(View.VISIBLE);

                        TextView forearm_v = (TextView)findViewById(R.id.bf_forearm_view);
                        forearm_v.setVisibility(View.VISIBLE);

                        TextView bf_req_v = (TextView)findViewById(R.id.bf_required_view);
                        bf_req_v.setVisibility(View.VISIBLE);

                        TextView wrist_unit_v = (TextView)findViewById(R.id.bf_wrist_unit_view);
                        wrist_unit_v.setVisibility(View.VISIBLE);

                        TextView hip_unit_v = (TextView)findViewById(R.id.bf_hip_unit_view);
                        hip_unit_v.setVisibility(View.VISIBLE);

                        TextView forearm_unit_v = (TextView)findViewById(R.id.bf_forearm_unit_view);
                        forearm_unit_v.setVisibility(View.VISIBLE);


                    }
                }
        );

        Button resetButt = (Button)findViewById(R.id.bf_reset_button);
        resetButt.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        EditText weight_num_reset = (EditText) findViewById(R.id.bf_weight_num);
                        weight_num_reset.setText(null);

                        EditText waist_num_reset = (EditText) findViewById(R.id.bf_waist_num);
                        waist_num_reset.setText(null);

                        EditText wrist_num_reset = (EditText) findViewById(R.id.bf_wrist_num);
                        wrist_num_reset.setText(null);

                        EditText hip_num_reset = (EditText) findViewById(R.id.bf_hip_num);
                        hip_num_reset.setText(null);

                        EditText forearm_num_reset = (EditText) findViewById(R.id.bf_forearm_num);
                        forearm_num_reset.setText(null);

                        TextView bf_answer_reset = (TextView) findViewById(R.id.bf_answer);
                        bf_answer_reset.setText("");

                    }
                }
        );


        Button calculate_bf_button = (Button)findViewById(R.id.calc_bodyfat_button);
        calculate_bf_button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        RadioGroup rad_group = (RadioGroup) findViewById(R.id.bf_radio_group);
                        int check = rad_group.getCheckedRadioButtonId();
                        if (check == -1) {
                            Toast.makeText(bodyfat.this, "Select gender", Toast.LENGTH_SHORT).show();
                        }

                        RadioButton fem_button = (RadioButton) findViewById(R.id.bf_fem_button);
                        boolean fem = fem_button.isChecked();

                        RadioButton male_button = (RadioButton) findViewById(R.id.bf_male_button);
                        boolean male = male_button.isChecked();



                        EditText weight_num = (EditText) findViewById(R.id.bf_weight_num);
                        String weight_s = (weight_num.getText().toString());
                        double weight;
                        if (TextUtils.isEmpty(weight_s)) {
                            weight_num.setError("Weight field can't be empty");
                            weight = 0;
                            return;
                        }
                        weight = Integer.parseInt(weight_num.getText().toString());


                        EditText waist_num = (EditText) findViewById(R.id.bf_waist_num);
                        String waist_s = waist_num.getText().toString();
                        double waist;
                        if (TextUtils.isEmpty(waist_s)) {
                            waist_num.setError("Waist circumference can't be empty");
                            waist = 0;
                            return;
                        }
                        waist = Integer.parseInt(waist_num.getText().toString());

                        EditText wrist_num = (EditText) findViewById(R.id.bf_wrist_num);
                        String wrist_s = wrist_num.getText().toString();
                        double wrist = 0;
                        if (TextUtils.isEmpty(wrist_s) && fem_button.isChecked()) {
                            wrist_num.setError("Wrist circumference can't be empty");
                            wrist = 0;
                            return;
                        }
                        if (fem_button.isChecked())
                            wrist = Integer.parseInt(wrist_num.getText().toString());

                        EditText hip_num = (EditText) findViewById(R.id.bf_hip_num);
                        String hip_s = hip_num.getText().toString();
                        double hip = 0;
                        if (TextUtils.isEmpty(hip_s) && fem_button.isChecked()) {
                            hip_num.setError("Hip circumference can't be empty");
                            hip = 0;
                            return;
                        }
                        if (fem_button.isChecked())
                            hip = Integer.parseInt(hip_num.getText().toString());

                        EditText forearm_num = (EditText) findViewById(R.id.bf_forearm_num);
                        String forearm_s = forearm_num.getText().toString();
                        double forearm = 0;
                        if (TextUtils.isEmpty(forearm_s) && fem_button.isChecked()) {
                            forearm_num.setError("Forearm circumference can't be empty");
                            forearm = 0;
                            return;
                        }
                        if (fem_button.isChecked())
                            forearm = Integer.parseInt(forearm_num.getText().toString());

                        TextView bfResult = (TextView) findViewById(R.id.bf_answer);
                        double bfr = 0;
                        if (fem_button.isChecked()) {
                            bfr = doFemaleFat(weight, waist, wrist, hip, forearm);
                        } else if (male_button.isChecked()) {
                            bfr = doMaleFat(weight, waist);
                        }
                        String bfstring = "";
                        bfstring = String.valueOf(Math.round(bfr * 100) / 100.0);
                        bfstring = bfstring.concat(" %.");
                        if (check != -1){
                            bfResult.setText(bfstring);
                        }

                    }
                }
        );
    }

    public double doFemaleFat(double weight, double waist, double wrist, double hip, double forearm){

        if(MainActivity.unit_is_gram){
            weight = weight * 2.2;
        }

        if(MainActivity.unit_is_meter){
            waist = 0.39370*waist;
            wrist = 0.39370*wrist;
            hip = 0.39370*hip;
            forearm = 0.39370*forearm;
        }

        double a,b,c,d,e,f;
        a = weight*0.732;
        b = a + 8.987;
        c = wrist / 3.14;
        d = waist * 0.157;
        e = hip * 0.249;
        f = forearm*0.434;

        double lbm = (((b + c) - d) - e) + f;
        double totbf = ((weight - lbm) * 100) / weight;

        return totbf;
    }

    public double doMaleFat(double weight, double waist){

        if(MainActivity.unit_is_gram){
            weight = weight * 2.2;
        }

        if(MainActivity.unit_is_meter){
            waist = 0.393701*waist;
        }

        double a,b;
        a = (weight * 1.082) + 94.42;
        b = a - (waist * 4.15);

        double totfat = ((weight - b) * 100) / weight;

        return totfat;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bodyfat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.bf_about){
            Intent myintent = new Intent(this,about.class);
            startActivityForResult(myintent,0);
        }

        return super.onOptionsItemSelected(item);
    }
}
