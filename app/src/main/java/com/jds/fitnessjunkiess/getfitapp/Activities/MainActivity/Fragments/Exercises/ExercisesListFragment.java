package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Exercises;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Adapters.ExercisesListAdapter;
import com.jds.fitnessjunkiess.getfitapp.Entities.Exercise;
import com.jds.fitnessjunkiess.getfitapp.R;

import java.util.ArrayList;
import java.util.List;

public class ExercisesListFragment extends Fragment {

    private List<Exercise> exercises;

    public ExercisesListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.exercises = new ArrayList<>();
        Exercise exercise = new Exercise();
        exercise.setName("Biceps Curl");

        Exercise exercise2 = new Exercise();
        exercise2.setName("Bench Press");

        Exercise exercise3 = new Exercise();
        exercise3.setName("Dead lift");

        this.exercises.add(exercise);
        this.exercises.add(exercise2);
        this.exercises.add(exercise3);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =
                inflater.inflate(R.layout.fragment_exercises_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.exercises_list_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        ExercisesListAdapter adapter = new ExercisesListAdapter();
        recyclerView.setAdapter(adapter);
        adapter.swapData(this.exercises);

        return view;
    }
}
