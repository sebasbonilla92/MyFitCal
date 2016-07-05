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

public class bmr extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmr);

        TextView bmr_weight_unit = (TextView)findViewById(R.id.bmr_unit_view);
        EditText weight_num = (EditText) findViewById(R.id.bmr_weight_num);

        if (MainActivity.unit_is_gram){
            bmr_weight_unit.setText("Kilograms");
        }

        if(MainActivity.unit_is_meter){

            TextView bmr_inches_to_cent = (TextView)findViewById(R.id.bmr_inches_view);
            bmr_inches_to_cent.setText("Centimeters");
            EditText cent_num = (EditText)findViewById(R.id.bmr_inches_num);
            cent_num.setWidth(610);
            TextView bmr_feet_to_meter = (TextView)findViewById(R.id.bmr_feet_view);
            bmr_feet_to_meter.setText("");
            EditText bmr_feet_disappear = (EditText)findViewById(R.id.bmr_feet_num);
            bmr_feet_disappear.setVisibility(View.INVISIBLE);


        }


        Button resetButt = (Button)findViewById(R.id.bmr_reset_button);
        resetButt.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        EditText weight_num_reset = (EditText) findViewById(R.id.bmr_age_num);
                        weight_num_reset.setText(null);

                        EditText waist_num_reset = (EditText) findViewById(R.id.bmr_weight_num);
                        waist_num_reset.setText(null);

                        EditText wrist_num_reset = (EditText) findViewById(R.id.bmr_feet_num);
                        wrist_num_reset.setText(null);

                        EditText hip_num_reset = (EditText) findViewById(R.id.bmr_inches_num);
                        hip_num_reset.setText(null);

                        TextView bf_answer_reset = (TextView) findViewById(R.id.bmr_answer);
                        bf_answer_reset.setText("");

                    }
                }
        );

        Button doCalcButton = (Button)findViewById(R.id.calc_bmr_button);
            doCalcButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        EditText age_num = (EditText) findViewById(R.id.bmr_age_num);
                        String age_s = (age_num.getText().toString());
                        double age;
                        if(TextUtils.isEmpty(age_s)){
                            age_num.setError("Age field can't be empty");
                            age = 0;
                            return;
                        }
                        age = Integer.parseInt(age_num.getText().toString());


                        EditText weight_num = (EditText) findViewById(R.id.bmr_weight_num);
                        String weight_s = (weight_num.getText().toString());
                        double weight;
                        if(TextUtils.isEmpty(weight_s)){
                            weight_num.setError("Weight field can't be empty");
                            weight = 0;
                            return;
                        }
                        weight = Integer.parseInt(weight_num.getText().toString());

                        double feet = 0;
                        if (!MainActivity.unit_is_meter){
                            EditText feet_num = (EditText) findViewById(R.id.bmr_feet_num);
                            String feet_s = feet_num.getText().toString();

                            if(TextUtils.isEmpty(feet_s)){
                                feet_num.setError("feet field can't be empty");
                                feet = 0;
                                return;
                            }
                            feet = Integer.parseInt(feet_num.getText().toString());

                        }


                        EditText inch_num = (EditText) findViewById(R.id.bmr_inches_num);
                        String inch_s = inch_num.getText().toString();
                        double inches = 0;
                        if(TextUtils.isEmpty(inch_s) && !MainActivity.unit_is_meter){
                            inch_num.setError("Inches field can't be empty");
                            inches = 0;
                            return;
                        }
                        else if (TextUtils.isEmpty(inch_s) && MainActivity.unit_is_meter){
                            inch_num.setError("Centimeters field can't be empty");
                            inches = 0;
                            return;
                        }
                        inches = Integer.parseInt(inch_num.getText().toString());

                        RadioGroup rad_group = (RadioGroup)findViewById(R.id.bmr_radiogroup);
                        int check = rad_group.getCheckedRadioButtonId();
                        if (check == -1){
                            Toast.makeText(bmr.this, "Select gender", Toast.LENGTH_SHORT).show();
                        }

                        RadioButton fem_button = (RadioButton) findViewById(R.id.bmr_fem_button);
                        boolean fem = fem_button.isChecked();

                        RadioButton male_button = (RadioButton) findViewById(R.id.bmr_male_button);
                        boolean male = male_button.isChecked();

                        TextView bmrResult = (TextView) findViewById(R.id.bmr_answer);
                        double bmrr = doBMR(fem, male, age, weight, feet, inches);
                        String bmrstring = "";
                        bmrstring = String.valueOf(Math.round(bmrr));
                        bmrstring = bmrstring.concat(" calories.");
                        if (check != -1) {
                            bmrResult.setText(bmrstring);
                        }

                    }
                }
        );
    }

    public double doBMR(boolean fem, boolean male, double age, double weight, double feet, double inches){

        double bmrResult = 0;
        double height_in_inches = 0;


        if(MainActivity.unit_is_meter){
            height_in_inches = 0.39370* inches; //actually is 0.39370 * centimeters
        }

        else height_in_inches = (feet*12)+inches;

        if (MainActivity.unit_is_gram){
            weight = weight*2.2;

        }

        if (fem == true) {
            //if it is a female use this formula
            //bmrResult = 655.09 + (4.35 * weight) + (4.7 + height_in_inches) - (4.67 * age);
            bmrResult = 10*(weight/2.2)+6.25*(height_in_inches*2.54)-5*age-161;
        }

        else if (male == true){
            //if it is a male use this formula
            //bmrResult = 66.47 + (6.23 * weight) + (12.7 + height_in_inches) - (6.75 * age);
            bmrResult = 10*(weight/2.2)+6.25*(height_in_inches*2.54)-5*age+5;
        }

        return bmrResult;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bmr, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.bmr_about){
            Intent myintent = new Intent(this,about.class);
            startActivityForResult(myintent,0);
        }

        return super.onOptionsItemSelected(item);
    }
}
