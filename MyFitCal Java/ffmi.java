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

public class ffmi extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ffmi);

        TextView ffmi_weight_unit = (TextView)findViewById(R.id.ffmi_unit_view);
        EditText weight_num = (EditText) findViewById(R.id.ffmi_weight_num);

        if (MainActivity.unit_is_gram){
            ffmi_weight_unit.setText("Kilograms");
        }

        if(MainActivity.unit_is_meter){
            TextView ffmi_inches_to_cent = (TextView)findViewById(R.id.ffmi_inches_view);
            ffmi_inches_to_cent.setText("Centimeters");
            EditText cent_num = (EditText)findViewById(R.id.ffmi_inches_num);
            cent_num.setWidth(610);
            TextView ffmi_feet_to_meter = (TextView)findViewById(R.id.ffmi_feet_view);
            ffmi_feet_to_meter.setText("");
            EditText ffmi_feet_disappear = (EditText)findViewById(R.id.ffmi_feet_num);
            ffmi_feet_disappear.setVisibility(View.INVISIBLE);
        }

        Button resetButt = (Button)findViewById(R.id.ffmi_reset_button);
        resetButt.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        EditText weight_num_reset = (EditText) findViewById(R.id.ffmi_weight_num);
                        weight_num_reset.setText(null);

                        EditText bf_num_reset = (EditText) findViewById(R.id.ffmi_bf_num);
                        bf_num_reset.setText(null);

                        EditText feet_num_reset = (EditText) findViewById(R.id.ffmi_feet_num);
                        feet_num_reset.setText(null);

                        EditText inches_num_reset = (EditText) findViewById(R.id.ffmi_inches_num);
                        inches_num_reset.setText(null);

                        TextView answer_reset = (TextView) findViewById(R.id.ffmi_result_view);
                        answer_reset.setText("");

                        TextView adj_answer_reset = (TextView)findViewById(R.id.ffmi_adjusted_result_view);
                        adj_answer_reset.setText("");

                    }
                }
        );


        Button doCalcButton = (Button)findViewById(R.id.ffmi_calculate_button);
        doCalcButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {


                        EditText weight_num = (EditText) findViewById(R.id.ffmi_weight_num);
                        String weight_s = (weight_num.getText().toString());
                        double weight;
                        if(TextUtils.isEmpty(weight_s)){
                            weight_num.setError("Weight field can't be empty");
                            weight = 0;
                            return;
                        }
                        weight = Integer.parseInt(weight_num.getText().toString());

                        EditText bf_num = (EditText) findViewById(R.id.ffmi_bf_num);
                        String bf_s = (bf_num.getText().toString());
                        double bf;
                        if(TextUtils.isEmpty(bf_s)){
                            bf_num.setError("Body fat field can't be empty");
                            bf = 0;
                            return;
                        }
                        bf = Integer.parseInt(bf_num.getText().toString());


                        double feet = 0; double inches = 0;
                        if (!MainActivity.unit_is_meter){ //if unit is not KG
                            EditText feet_num = (EditText) findViewById(R.id.ffmi_feet_num);
                            String feet_s = feet_num.getText().toString();

                            if(TextUtils.isEmpty(feet_s)){
                                feet_num.setError("feet field can't be empty");
                                feet = 0;
                                return;
                            }
                            feet = Integer.parseInt(feet_num.getText().toString());

                        }


                        EditText inch_num = (EditText) findViewById(R.id.ffmi_inches_num);
                        String inch_s = inch_num.getText().toString();
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

                        TextView ffmi_result = (TextView) findViewById(R.id.ffmi_result_view);
                        TextView ffmi_adj_result = (TextView)findViewById(R.id.ffmi_adjusted_result_view);

                        double ffmi_result_number = ffmi_formula(weight, bf, feet, inches);
                        double ffmi_adj_result_number = adjusted_ffmi(ffmi_result_number, feet, inches);

                        String ffmi_string = "FFMI: ";
                        String ffmi_sn = String.format("%.2f", ffmi_result_number);
                        ffmi_string = ffmi_string.concat(ffmi_sn);

                        String ffmi_adj_string = "Adjusted FFMI: ";
                        String ffmi_adj_sn = String.format("%.2f", ffmi_adj_result_number);
                        ffmi_adj_string = ffmi_adj_string.concat(ffmi_adj_sn);

                        ffmi_result.setText(ffmi_string);
                        ffmi_adj_result.setText(ffmi_adj_string);

                    }
                }
        );
    }

    public double ffmi_formula(double weight, double bodyfat, double feet, double inches){


        double total_height_in_inches = 0;
        double total_weight_in_kg = 0;

        if(MainActivity.unit_is_meter){ //if unit is meters turn into inches
            total_height_in_inches = 0.393701* inches;  //total_height_in_inches = 0.39370* height in cms;
        }

        else total_height_in_inches = (feet*12)+inches; //get total height in inches

        if (MainActivity.unit_is_gram){  //if the unit is pounds turn into KG
            total_weight_in_kg = weight;
        }

        else total_weight_in_kg = weight*0.453592; // kg = lbs * 0.453592

        double lean = total_weight_in_kg * (1-(bodyfat/100));  //weight in kg
        double x = ((total_height_in_inches)*0.0254);
        x = x*x;
        double ffmiR = ((lean/2.2)/x)*2.20462;

        return ffmiR;
    }

    public double adjusted_ffmi(double ffmi, double feet, double inches){

        double total_height_in_inches = 0;
        if(MainActivity.unit_is_meter){ //if unit is meters turn into inches
            total_height_in_inches = 0.393701* inches;  //total_height_in_inches = 0.39370* height in cms;
        }

        else total_height_in_inches = (feet*12)+inches; //get total height in inches

        double x = ((total_height_in_inches)*0.0254);
        double adjusted_ffmi = ffmi + (6*(x-1.8));

        return adjusted_ffmi;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ffmi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.ffmi_about){
            Intent myintent = new Intent(this,about.class);
            startActivityForResult(myintent,0);
        }

        return super.onOptionsItemSelected(item);
    }
}
