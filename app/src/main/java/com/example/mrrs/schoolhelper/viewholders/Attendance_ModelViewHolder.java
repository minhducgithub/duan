package com.example.mrrs.schoolhelper.viewholders;

import android.view.View;
import android.widget.TextView;

import com.example.mrrs.schoolhelper.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class Attendance_ModelViewHolder extends ChildViewHolder {
    private TextView modelName;

    public Attendance_ModelViewHolder(View itemView) {
        super(itemView);

        modelName = (TextView) itemView.findViewById(R.id.attendance_list_item_model_name);
    }

    public void setModelName(String name) {
        modelName.setText(name);
    }
}
