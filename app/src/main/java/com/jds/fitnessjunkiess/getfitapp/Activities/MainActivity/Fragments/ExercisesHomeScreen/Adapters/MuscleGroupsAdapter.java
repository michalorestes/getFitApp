package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.ExercisesHomeScreen.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jds.fitnessjunkiess.getfitapp.Data.DataModels.MuscleGroup;
import com.jds.fitnessjunkiess.getfitapp.R;

import java.util.ArrayList;
import java.util.List;

public class MuscleGroupsAdapter extends BaseAdapter {

  private List<MuscleGroup> data;
  private Context context;

  public MuscleGroupsAdapter(Context context) {
    this.context = context;
    this.data = new ArrayList<>();
  }

  public void updateDataSet(List<MuscleGroup> data) {
    this.data = data;
    MuscleGroup m = new MuscleGroup();
    m.setName("tester");

    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    data.add(m);
    notifyDataSetChanged();
  }

  @Override
  public int getCount() {
    return this.data.size();
  }

  @Override
  public MuscleGroup getItem(int position) {
    return this.data.get(position);
  }

  @Override
  public long getItemId(int position) {
    return this.data.get(position).getId();
  }

  @Override
  public View getView(int position, View view, ViewGroup parent) {
    if (view == null) {
      view = LayoutInflater
          .from(this.context)
          .inflate(R.layout.view_holder_muscle_groups, parent, false);
    }

    TextView name = view.findViewById(R.id.muscle_group_name);
    name.setText(this.data.get(position).getName());

    view.setOnClickListener(
        v -> Toast.makeText(
            context, this.data.get(position).getName() + " selected :) ", Toast.LENGTH_LONG
        ).show()
    );

    return view;
  }
}
