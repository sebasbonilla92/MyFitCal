package com.example.sebz.MyFitCal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class bmi extends MainActivity {
    //public class bmi extends AppCompatActivity {    ***** idk what difference this makes **

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        TextView bmi_weight_unit = (TextView)findViewById(R.id.bmi_pounds_view);

        if(MainActivity.unit_is_gram){
            bmi_weight_unit.setText("Kilograms");
        }

        if(MainActivity.unit_is_meter){
            TextView bmi_inches_to_cent = (TextView)findViewById(R.id.bmi_inch_view);
            bmi_inches_to_cent.setText("Centimeters");
            EditText bmi_cent_filed = (EditText)findViewById(R.id.bmi_inch_num);
            bmi_cent_filed.setWidth(610);
            TextView bmi_feet_to_meter = (TextView)findViewById(R.id.bmi_feet_view);
            bmi_feet_to_meter.setText("");
            EditText bmi_feet_field_disappear = (EditText)findViewById(R.id.bmi_feet_num);
            bmi_feet_field_disappear.setVisibility(View.INVISIBLE);
        }

        Button resetButt = (Button)findViewById(R.id.bmi_reset_button);
        resetButt.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        EditText weight_num_reset = (EditText) findViewById(R.id.bmi_weight_num);
                        weight_num_reset.setText(null);

                        EditText feet_num_reset = (EditText) findViewById(R.id.bmi_feet_num);
                        feet_num_reset.setText(null);

                        EditText inches_num_reset = (EditText) findViewById(R.id.bmi_inch_num);
                        inches_num_reset.setText(null);

                        TextView answer_reset = (TextView) findViewById(R.id.bmi_answer_view);
                        answer_reset.setText("");

                        TextView diagnostic_reset = (TextView)findViewById(R.id.bmi_diagnostic_view);
                        diagnostic_reset.setText("");

                    }
                }
        );


        Button calculate_calories_button = (Button)findViewById(R.id.bmi_calc_button);
        calculate_calories_button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {


                        EditText weight_num = (EditText) findViewById(R.id.bmi_weight_num);
                        String weight_s = (weight_num.getText().toString());
                        double weight;
                        if (TextUtils.isEmpty(weight_s)) {
                            weight_num.setError("Weight field can't be empty");
                            weight = 0;
                            return;
                        }
                        weight = Integer.parseInt(weight_num.getText().toString());

                        double feet = 0;
                        if(!MainActivity.unit_is_meter){
                            EditText feet_num = (EditText) findViewById(R.id.bmi_feet_num);
                            String feet_s = feet_num.getText().toString();
                            if (TextUtils.isEmpty(feet_s)) {
                                feet_num.setError("Feet field can't be empty");
                                feet = 0;
                                return;
                            }
                            feet = Integer.parseInt(feet_num.getText().toString());
                        }


                        EditText inch_num = (EditText) findViewById(R.id.bmi_inch_num);
                        String inch_s = inch_num.getText().toString();
                        double inches;
                        if (TextUtils.isEmpty(inch_s) && !MainActivity.unit_is_meter) {
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



                        TextView bmiResult = (TextView) findViewById(R.id.bmi_answer_view);
                        TextView bmiDiagnostic = (TextView)findViewById((R.id.bmi_diagnostic_view));


                        if(MainActivity.unit_is_gram){
                            weight = weight * 2.2;
                        }

                        double height_sq = 0;

                        if(MainActivity.unit_is_meter){
                            inches = inches* 0.39370;  //if unit is centimeter (meters) inches = cent*0.39370
                            height_sq = inches * inches;  // height squared = inches ^2;
                        }

                        else height_sq = ((feet*12)+inches)*((feet*12)+inches); //of unit is inches do normal calculation


                        double bmi_n = (weight/(height_sq))*703;

                        if(bmi_n <= 18.5){
                            bmiDiagnostic.setText("Underweight");
                        }
                        else if(bmi_n > 18.5 && bmi_n <= 24.9){
                            bmiDiagnostic.setText("Normal Weight");
                        }
                        else if(bmi_n > 24.9 && bmi_n <= 29.9){
                            bmiDiagnostic.setText("Overweight");
                        }
                        else if(bmi_n > 29.9 && bmi_n <= 34.9){
                            bmiDiagnostic.setText("Class 1 obesity");
                        }
                        else if(bmi_n > 34.9 && bmi_n <= 39.9){
                            bmiDiagnostic.setText("Class 2 obesity");
                        }
                        else if(bmi_n > 39.9){
                            bmiDiagnostic.setText("Extreme Obesity");
                        }
                        String bmistring = "";
                        bmistring = String.valueOf(Math.round(bmi_n));
                        bmiResult.setText(bmistring);


                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bmi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.bmi_about){
            Intent myintent = new Intent(this,about.class);
            startActivityForResult(myintent,0);
        }

        return super.onOptionsItemSelected(item);
    }
}
