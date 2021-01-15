package com.example.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ChildSchedule> childSchedules = new ArrayList<>();

        childSchedules.add(new ChildSchedule("X", "X"));

        ChildScheduleAdapter<ChildSchedule> childScheduleAdapter =
                new ChildScheduleAdapter<ChildSchedule>(this, childSchedules);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(childScheduleAdapter);

    }
}