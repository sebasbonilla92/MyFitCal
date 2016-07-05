package com.example.sebz.MyFitCal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Calories extends MainActivity implements AdapterView.OnItemSelectedListener{

    Spinner sp, goal_sp;
    double valofactlevel = 0;
    int goalnum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories);
        TextView cal_pound_to_gram = (TextView)findViewById(R.id.cal_pounds_view);
        EditText weight_num = (EditText) findViewById(R.id.cal_weight_num);

        if (MainActivity.unit_is_gram){
            cal_pound_to_gram.setText("Kilograms");
        }

        if(MainActivity.unit_is_meter){
            TextView cal_inches_to_cent = (TextView)findViewById(R.id.cal_inches_view);
            cal_inches_to_cent.setText("Centimeters");
            //cal_inches_to_cent.setWidth(200);
            EditText cent_num = (EditText)findViewById(R.id.cal_inches_num);
            cent_num.setWidth(595);
            TextView cal_feet_to_meter = (TextView)findViewById(R.id.cal_feet_view);
            cal_feet_to_meter.setText(""); //meters
            EditText cal_disappear = (EditText)findViewById(R.id.cal_feet_num);
            cal_disappear.setVisibility(View.INVISIBLE);


        }

        Button resetButt = (Button)findViewById(R.id.cal_reset_button);
        resetButt.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        EditText weight_num_reset = (EditText) findViewById(R.id.cal_age_num);
                        weight_num_reset.setText(null);

                        EditText waist_num_reset = (EditText) findViewById(R.id.cal_weight_num);
                        waist_num_reset.setText(null);

                        EditText wrist_num_reset = (EditText) findViewById(R.id.cal_feet_num);
                        wrist_num_reset.setText(null);

                        EditText hip_num_reset = (EditText) findViewById(R.id.cal_inches_num);
                        hip_num_reset.setText(null);

                        TextView bf_answer_reset = (TextView) findViewById(R.id.cal_answer);
                        bf_answer_reset.setText("");


                    }
                }
        );


        sp = (Spinner) findViewById(R.id.cal_actlevel_list);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.levelofact,android.R.layout.simple_spinner_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);

        goal_sp = (Spinner) findViewById(R.id.cal_goal_spinner);
        ArrayAdapter goal_adapter = ArrayAdapter.createFromResource(this,R.array.goalsarr,android.R.layout.simple_spinner_item);
        goal_sp.setAdapter(goal_adapter);
        goal_sp.setOnItemSelectedListener(this);

        Button calculate_calories_button = (Button)findViewById(R.id.calc_calories_button);
        calculate_calories_button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {


                        EditText age_num = (EditText) findViewById(R.id.cal_age_num);
                        String age_s = (age_num.getText().toString());
                        double age;
                        if(TextUtils.isEmpty(age_s)){
                            age_num.setError("Field can't be empty");
                            age = 0;
                            return;
                        }
                        age = Integer.parseInt(age_num.getText().toString());


                        EditText weight_num = (EditText) findViewById(R.id.cal_weight_num);
                        String weight_s = (weight_num.getText().toString());
                        double weight = 0;
                        if(TextUtils.isEmpty(weight_s)){
                            weight_num.setError("Field can't be empty");
                            weight = 0;
                            return;
                        }
                        weight = Integer.parseInt(weight_num.getText().toString());

                        double feet = 0;
                        if (!MainActivity.unit_is_meter){
                            EditText feet_num = (EditText) findViewById(R.id.cal_feet_num);
                            String feet_s = feet_num.getText().toString();

                            if(TextUtils.isEmpty(feet_s) && !MainActivity.unit_is_meter){
                                feet_num.setError("Field can't be empty");
                                feet = 0;
                                return;
                            }
                            feet = Integer.parseInt(feet_num.getText().toString());
                        }

                        EditText inch_num = (EditText) findViewById(R.id.cal_inches_num);
                        String inch_s = inch_num.getText().toString();
                        double inches;
                        if(TextUtils.isEmpty(inch_s)){
                            inch_num.setError("Field can't be empty");
                            inches = 0;
                            return;
                        }
                        inches = Integer.parseInt(inch_num.getText().toString());

                        RadioGroup rad_group = (RadioGroup)findViewById(R.id.cal_radioGroup);
                        int check = rad_group.getCheckedRadioButtonId();
                        if (check == -1){
                            Toast.makeText(Calories.this, "Select gender", Toast.LENGTH_SHORT).show();
                        }

                        RadioButton fem_button = (RadioButton) findViewById(R.id.cal_fem_button);
                        boolean fem = fem_button.isChecked();

                        RadioButton male_button = (RadioButton) findViewById(R.id.cal_male_button);
                        boolean male = male_button.isChecked();

                        TextView calResult = (TextView) findViewById(R.id.cal_answer);
                        double bmrr = doCalories(fem, male, age, weight, feet, inches);
                        bmrr = (bmrr*valofactlevel)+goalnum;
                        String bmrstring = "";
                        bmrstring = String.valueOf(Math.round(bmrr));
                        if (goalnum == 0){
                            bmrstring = bmrstring.concat(" calories to maintain weight.");
                        }
                        else if (goalnum > 0){
                            bmrstring = bmrstring.concat(" calories to gain weight.");
                        }
                        else if (goalnum < 0){
                            bmrstring = bmrstring.concat(" calories to lose weight.");
                        }

                        if (check != -1){
                            calResult.setText(bmrstring);
                        }


                    }
                }


        );
    }

    public double doCalories(boolean fem, boolean male, double age, double weight, double feet, double inches){

        double bmrResult = 0;
        double height_in_inches;

        if (MainActivity.unit_is_gram){
            weight = weight * 2.2;
        }

        if (MainActivity.unit_is_meter){
            height_in_inches = 0.39370* inches; //actually is 0.39370 * centimeters
        }

        else height_in_inches = (feet*12)+inches;

        if (fem == true) {
            //if it is a female use this formula
            bmrResult = 10*(weight/2.2)+6.25*(height_in_inches*2.54)-5*age-161;
        }

        else if (male == true){
            //if it is a male use this formula

            bmrResult = 10*(weight/2.2)+6.25*(height_in_inches*2.54)-5*age+5;
        }

        return bmrResult;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner one = (Spinner)parent;

        if(one.getId() == R.id.cal_actlevel_list){
            TextView myText = (TextView) view;
            if (position == 0) valofactlevel = 1.2;
            else if (position == 1) valofactlevel = 1.375;
            else if (position == 2) valofactlevel = 1.55;
            else if (position == 3) valofactlevel = 1.725;
            else if (position == 4) valofactlevel = 1.9;
        }
        else if(one.getId() == R.id.cal_goal_spinner){
            if (position == 0){
                goalnum = 0;
            }
            else if (position == 1){
                goalnum = -500;
            }
            else if (position == 2){
                goalnum = 500;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        //Toast.makeText(this,"Select activity level ",Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calories, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cal_about){
            Intent calorie_to_about = new Intent(this,about.class);
            startActivityForResult(calorie_to_about,0);
        }

        return super.onOptionsItemSelected(item);
    }

}

