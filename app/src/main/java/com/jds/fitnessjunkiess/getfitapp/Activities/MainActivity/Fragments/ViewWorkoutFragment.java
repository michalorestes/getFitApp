package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Adapters.WorkoutViewRecycleViewAdapter;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;

public class ViewWorkoutFragment extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Workout workout;

    public ViewWorkoutFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle  = getArguments();
        Gson gson = new Gson();
        this.workout = gson.fromJson(bundle.getString("workout"), Workout.class);
    }

    @Nullable
    @Override
    public View onCreateView
            (LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view_workout, container, false);
        this.recyclerView = view.findViewById(R.id.exercise_list_recycle_viewer);
        this.recyclerView.setHasFixedSize(true);
        this.linearLayoutManager = new LinearLayoutManager(getContext());
        this.recyclerView.setLayoutManager(this.linearLayoutManager);

        WorkoutViewRecycleViewAdapter workoutViewRecycleViewAdapter =
                new WorkoutViewRecycleViewAdapter(this.workout.getExercises());
        this.recyclerView.setAdapter(workoutViewRecycleViewAdapter);
        return view;
    }
}
