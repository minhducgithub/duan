package com.example.mrrs.schoolhelper.viewholders;

import android.view.View;
import android.widget.TextView;

import com.example.mrrs.schoolhelper.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

public class Attendance_GenreViewHolder extends GroupViewHolder {

    private TextView mGenreTitle;


    public Attendance_GenreViewHolder(View itemView) {
        super(itemView);

        mGenreTitle = (TextView) itemView.findViewById(R.id.attendance_list_item_genre_name);
    }
    // Create setter for genreTitle

    public void setGenreTitle(String name) {
        mGenreTitle.setText(name);
    }
}
