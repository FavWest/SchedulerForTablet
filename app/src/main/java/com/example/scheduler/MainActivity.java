package com.example.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

//I added a comment! 11-30-2020
public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    public static final String ENTER_NAME="com.example.scheduler.extra.ENTER_NAME";
    public static final String MON="com.example.scheduler.extra.MON";
    public static final String TUES="com.example.scheduler.extra.TUES";
    public static final String WED="com.example.scheduler.extra.WED";
    public static final String THURS="com.example.scheduler.extra.THURS";
    public static final String FRI="com.example.scheduler.extra.FRI";
    public static final String WANT="com.example.scheduler.extra.WANT";
    public static final String MIN="com.example.scheduler.extra.MIN";

    EditText enterName;
    Spinner mon_spinner;
    Spinner tues_spinner;
    Spinner wed_spinner;
    Spinner thurs_spinner;
    Spinner fri_spinner;
    Spinner days_wanted_spinner;
    Spinner minimum_days_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Attach enterName variable to EditText enterName
        //Other variable assignments happen within spinner creation
        enterName=findViewById(R.id.enterName);

        //Make Monday spinner
        mon_spinner = findViewById(R.id.mon_spinner);
        if (mon_spinner != null) {
            mon_spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> mon_adapter = ArrayAdapter.createFromResource(this,
                R.array.day_array, R.layout.scheduler_spinner_style);
        mon_adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        if (mon_spinner != null) {
            mon_spinner.setAdapter(mon_adapter);
        }
        //Make Tuesday spinner
        tues_spinner = findViewById(R.id.tues_spinner);
        if (tues_spinner != null) {
            tues_spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> tues_adapter = ArrayAdapter.createFromResource(this,
                R.array.day_array, R.layout.scheduler_spinner_style);
        tues_adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        if (tues_spinner != null) {
            tues_spinner.setAdapter(tues_adapter);
        }
        //Make Wednesday spinner
        wed_spinner = findViewById(R.id.wed_spinner);
        if (wed_spinner != null) {
            wed_spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> wed_adapter = ArrayAdapter.createFromResource(this,
                R.array.day_array, R.layout.scheduler_spinner_style);
        wed_adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        if (wed_spinner != null) {
            wed_spinner.setAdapter(wed_adapter);
        }

        //Make Thursday spinner
        thurs_spinner = findViewById(R.id.thurs_spinner);
        if (thurs_spinner != null) {
            thurs_spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> thurs_adapter = ArrayAdapter.createFromResource(this,
                R.array.day_array, R.layout.scheduler_spinner_style);
        thurs_adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        if (thurs_spinner != null) {
            thurs_spinner.setAdapter(thurs_adapter);
        }

        //Make Friday spinner
        fri_spinner = findViewById(R.id.fri_spinner);
        if (fri_spinner != null) {
            fri_spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> fri_adapter = ArrayAdapter.createFromResource(this,
                R.array.day_array, R.layout.scheduler_spinner_style);
        fri_adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        if (fri_spinner != null) {
            fri_spinner.setAdapter(fri_adapter);
        }

        //Make Days Wanted spinner
        days_wanted_spinner = findViewById(R.id.days_wanted_spinner);
        if (days_wanted_spinner != null) {
            days_wanted_spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> days_wanted_adapter = ArrayAdapter.createFromResource(this,
                R.array.days_wanted_array, R.layout.scheduler_spinner_style);
        days_wanted_adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        if (days_wanted_spinner != null) {
            days_wanted_spinner.setAdapter(days_wanted_adapter);
        }
        //Make Minimum Days spinner
        minimum_days_spinner = findViewById(R.id.minimum_days_spinner);
        if (minimum_days_spinner != null) {
            minimum_days_spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> minimum_days_adapter = ArrayAdapter.createFromResource(this,
                R.array.minimum_days_array, R.layout.scheduler_spinner_style);
        minimum_days_adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        if (minimum_days_spinner != null) {
            minimum_days_spinner.setAdapter(minimum_days_adapter);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int
            i, long l) {
        String spinnerLabel;
        spinnerLabel = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void saveInputData(View view) {
        //saves the user-entered data and takes the user to the display_table activity

        //sets the variables to be saved
        String name=enterName.getText().toString();
        String monChoice=mon_spinner.getSelectedItem().toString();
        String tuesChoice=tues_spinner.getSelectedItem().toString();
        String wedChoice=wed_spinner.getSelectedItem().toString();
        String thursChoice=thurs_spinner.getSelectedItem().toString();
        String friChoice=fri_spinner.getSelectedItem().toString();
        String wantDays=days_wanted_spinner.getSelectedItem().toString();
        String minDays=minimum_days_spinner.getSelectedItem().toString();

        //TODO Makes a toast- temporary
        String message=name + " " + monChoice+ " " + tuesChoice + " "
                + wedChoice + " " + thursChoice +" " + friChoice;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        //Sends the user entered info to the new activity as intent extras
        Intent intent= new Intent(this, DisplayTable.class);
        intent.putExtra(ENTER_NAME, name);
        intent.putExtra(MON, monChoice);
        intent.putExtra(TUES, tuesChoice);
        intent.putExtra(WED, wedChoice);
        intent.putExtra(THURS, thursChoice);
        intent.putExtra(FRI, friChoice);
        intent.putExtra(WANT, wantDays);
        intent.putExtra(MIN, minDays);

        //Starts the display_table activity
        startActivity(intent);
    }

    public void resetScreen(View view){

    }
}