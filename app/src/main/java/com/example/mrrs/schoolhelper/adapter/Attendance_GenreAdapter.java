package com.example.mrrs.schoolhelper.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mrrs.schoolhelper.R;
import com.example.mrrs.schoolhelper.model.AttendanceModel;
import com.example.mrrs.schoolhelper.viewholders.Attendance_GenreViewHolder;
import com.example.mrrs.schoolhelper.viewholders.Attendance_ModelViewHolder;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Attendance_GenreAdapter extends ExpandableRecyclerViewAdapter<Attendance_GenreViewHolder, Attendance_ModelViewHolder> {

    public Attendance_GenreAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public Attendance_GenreViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_genre_attendance, parent, false);
        return new Attendance_GenreViewHolder(view);
    }

    @Override
    public Attendance_ModelViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_model_attendance, parent, false);
        return new Attendance_ModelViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(Attendance_ModelViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        AttendanceModel model = (AttendanceModel) group.getItems().get(childIndex);
        holder.setModelName(model.getName());
    }

    @Override
    public void onBindGroupViewHolder(Attendance_GenreViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setGenreTitle(group.getTitle());
    }


}
