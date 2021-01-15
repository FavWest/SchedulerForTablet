package com.example.scheduler;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ChildScheduleAdapter<W> extends ArrayAdapter<ChildSchedule> {

    public ChildScheduleAdapter(Activity context, ArrayList<ChildSchedule> childSchedules){
        super(context, 0, childSchedules);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.child_schedule_item, parent, false);
        }

        ChildSchedule currentChildSchedule = getItem(position);

        TextView monValue = listItemView.findViewById(R.id.mon_value);

        monValue.setText(currentChildSchedule.getMonValue());

        TextView tuesValue = listItemView.findViewById(R.id.tues_value);

        tuesValue.setText(currentChildSchedule.getTuesValue());

        return listItemView;
    }
}
