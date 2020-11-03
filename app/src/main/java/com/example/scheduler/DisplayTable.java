package com.example.scheduler;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.example.scheduler.MainActivity.FRI;
import static com.example.scheduler.MainActivity.MIN;
import static com.example.scheduler.MainActivity.MON;
import static com.example.scheduler.MainActivity.THURS;
import static com.example.scheduler.MainActivity.TUES;
import static com.example.scheduler.MainActivity.WANT;
import static com.example.scheduler.MainActivity.WED;

public class DisplayTable extends AppCompatActivity {

    String name;
    TextView display_name;
    TextView display_mon;
    TextView display_tues;
    TextView display_wed;
    TextView display_thurs;
    TextView display_fri;
    TextView display_total;
    int count;
    int daysWanted;
    int daysMinimum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_table);
        Intent intent = getIntent();

        //Get and display the name
        name = intent.getStringExtra(MainActivity.ENTER_NAME);
        display_name = findViewById(R.id.display_name);
        display_name.setText(name);

        //Get TextViews for weekdays and total
        display_mon = findViewById(R.id.display_mon);
        display_tues = findViewById(R.id.display_tues);
        display_wed = findViewById(R.id.display_wed);
        display_thurs = findViewById(R.id.display_thurs);
        display_fri = findViewById(R.id.display_fri);
        display_total = findViewById(R.id.display_total);

        //Get numbers from Days Wanted and Minimum Days
        daysWanted=convertWantToInt(intent.getStringExtra(WANT));
        //daysMinimum=convertMinimumToInt(MIN);

        //Fill and format the day cells for this row
        makeDayDisplay(intent, display_mon, MON);
        makeDayDisplay(intent, display_tues, TUES);
        makeDayDisplay(intent, display_wed, WED);
        makeDayDisplay(intent, display_thurs, THURS);
        makeDayDisplay(intent, display_fri, FRI);

        //Make display_total show the initial total
        makeNewRowTotal();
    }

    public void makeDayDisplay(Intent intent, TextView display, String extra) {
        String value = intent.getStringExtra(extra);
        //Translate that value and display appropriate text
        String displayValue = processInput(value);
        display.setText(displayValue);
        //Determine whether the cell should be locked or unlocked,
        //if it should be locked ("Need" or "Don't Want" chosen)
        //make the cell unclickable and set the background to gray
        if (!value.equals("Okay")) {
            display.setBackgroundColor(getResources().getColor(R.color.light_gray));
            display.setClickable(false);
        }
    }

    //Takes the user's entered choices and converts them to text to display
    public String processInput(String input) {
        switch (input) {
            case "Okay":
            case "Don't Want":
                input = "";
                break;
            case "Need":
                input = "X";
        }
        return input;
    }

    //When a clickable view is clicked, change that day's registration
    public void changeDay(View view) {
        //Change the registration for the clicked-on day
        makeNewDayText(view);
        //Add up the total registered days for that row and display the result
        makeNewRowTotal();

    }

    //Change the registration for the clicked-on day
    private void makeNewDayText(View view) {
        TextView textView = (TextView) view;
        String currentText = textView.getText().toString();
        if (currentText.equals("")) {
            textView.setText("X");
        } else {
            textView.setText("");
        }
    }

    //Add up the total registered days for that row and display the result
    private void makeNewRowTotal() {
        TextView[] days = {display_mon, display_tues, display_wed, display_thurs, display_fri};
        count = 0;
        for (TextView day : days) {
            if (day.getText().toString().equals("X")) {
                count += 1;
            }
        }
        display_total.setText(Integer.toString(count));
        if(daysWanted==count){
            display_total.setBackgroundColor(getResources().getColor(R.color.purple_200));
        }
        else{
            display_total.setBackgroundColor(getResources().getColor(R.color.red_alert));
            String message=String.format("%s (wanted: %d)", Integer.toString(count), daysWanted);
            display_total.setText(message);
        }
    }

    private int convertWantToInt(String string){
        return Integer.parseInt(String.valueOf(string.charAt(0)));
    }
}