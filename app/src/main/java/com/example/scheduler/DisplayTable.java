package com.example.scheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import static com.example.scheduler.MainActivity.FRI;
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

        //Get the intent from the Main Activity.
        //This includes extras containing the user-entered data for the new row
        Intent intent = getIntent();

        //Get and display the name
        name = intent.getStringExtra(MainActivity.ENTER_NAME);
        display_name = findViewById(R.id.display_name);
        display_name.setText(name);

        //Get TextViews for weekday choices and total days wanted
        display_mon = findViewById(R.id.display_mon);
        display_tues = findViewById(R.id.display_tues);
        display_wed = findViewById(R.id.display_wed);
        display_thurs = findViewById(R.id.display_thurs);
        display_fri = findViewById(R.id.display_fri);
        display_total = findViewById(R.id.display_total);

        //Get the user selection for daysWanted (a String) and convert it to an int type
        daysWanted=convertWantToInt(intent.getStringExtra(WANT));
        //TODO Minimum Days
        //daysMinimum=convertMinimumToInt(MIN);

        //Fill and format the day cells for this row
        makeDayDisplay(intent, display_mon, MON);
        makeDayDisplay(intent, display_tues, TUES);
        makeDayDisplay(intent, display_wed, WED);
        makeDayDisplay(intent, display_thurs, THURS);
        makeDayDisplay(intent, display_fri, FRI);

        //Make display_total show the total days the child is currently assigned
        makeNewRowTotal();
    }

    public void makeDayDisplay(Intent intent, TextView display, String extra) {
        //for the specified day display TextView, get the intent extra
        // that holds the user's choice for that day

        //Get the user's choice for the day from the intent extra
        String value = intent.getStringExtra(extra);

        //Use the processInput method to translate that value and display an "X"
        //if the user chose "need" for this day
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
    //display an "X" if the user chose "need" for this day, otherwise display
    //an empty string
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

    //When a clickable view is clicked, change that day's registration:
    //ie, add or remove an "X" to indicate whether that day is selected.
    //Then recalculate the number of days selected in the row by counting
    //the "X"s in the row
    public void changeDay(View view) {
        //Change the registration for the clicked-on day
        makeNewDayText(view);
        //Add up the total registered days for that row and display the result
        makeNewRowTotal();

    }

    //Change the registration for the clicked-on day
    //ie, add or remove an "X" to indicate whether that day is selected
    private void makeNewDayText(View view) {
        TextView textView = (TextView) view;
        String currentText = textView.getText().toString();
        if (currentText.equals("")) {
            textView.setText("X");
        } else {
            textView.setText("");
        }
    }

    //Add up the total registered days for the row by counting
    //the "X"s in the row and display the result in the "Total" column.
    private void makeNewRowTotal() {

        //Count the "X"s in the row
        TextView[] days = {display_mon, display_tues, display_wed, display_thurs, display_fri};
        count = 0;
        for (TextView day : days) {
            if (day.getText().toString().equals("X")) {
                count += 1;
            }
        }

        //Reset the display_total TextView to display the count
        //If the count is equal to daysWanted, set the background to purple
        display_total.setText(Integer.toString(count));
        if(daysWanted==count){
            display_total.setBackgroundColor(getResources().getColor(R.color.purple_200));
        }

        //If the count is NOT equal to daysWanted, set the background to red
        //and change the display_total message to indicate both count and days wanted
        else{
            display_total.setBackgroundColor(getResources().getColor(R.color.red_alert));
            String message=String.format("%s (wanted: %d)", Integer.toString(count), daysWanted);
            display_total.setText(message);
        }
    }

    private int convertWantToInt(String string){
        //takes the number selected by the user and converts it from a String type to an int
        return Integer.parseInt(String.valueOf(string.charAt(0)));
    }
}