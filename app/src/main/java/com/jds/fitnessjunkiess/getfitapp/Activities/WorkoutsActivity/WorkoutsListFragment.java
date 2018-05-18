package com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutsActivity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jds.fitnessjunkiess.getfitapp.CustomViews.WorkoutCardView;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents.DaggerWorkoutViewModelFactoryComponent;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents.WorkoutViewModelFactoryComponent;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerModules.WorkoutViewModelFactoryModule;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.Entities.WorkoutExercise;
import com.jds.fitnessjunkiess.getfitapp.R;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.Factories.WorkoutViewModelFactory;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.WorkoutViewModel;
import java.util.ArrayList;
import java.util.List;

//TODO: Implement Recycle Viewer: https://developer.android.com/guide/topics/ui/layout/recyclerview#java
//TODO: Implement OnCLickListener with RecycleViewer: https://stackoverflow.com/questions/27081787/onclicklistener-for-cardview
//TODO: Populate RecycleViewer


public class WorkoutsListFragment extends Fragment {

    private WorkoutViewModelFactory workoutViewModelFactory;
    private WorkoutViewModel workoutViewModel;

    private RecyclerView recyclerView;
    private WorkoutListRecycleViewAdapter recycleViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;

    private List<Workout> workouts;

    public WorkoutsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.workouts = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_workouts_list, container, false);
        this.recyclerView = view.findViewById(R.id.workoutsList);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerViewLayoutManager = new LinearLayoutManager(getContext());
        this.recyclerView.setLayoutManager(recyclerViewLayoutManager);

        this.recycleViewAdapter = new WorkoutListRecycleViewAdapter(this.workouts);
        this.recyclerView.setAdapter(recycleViewAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        WorkoutViewModelFactoryComponent workoutViewModelFactoryComponent =
        DaggerWorkoutViewModelFactoryComponent
                .builder()
                .workoutViewModelFactoryModule(new WorkoutViewModelFactoryModule())
                .build();

        this.workoutViewModelFactory =
                workoutViewModelFactoryComponent
                        .provideWorkoutViewModelFactory();

        this.workoutViewModel =  ViewModelProviders.of(this, this.workoutViewModelFactory)
                .get(WorkoutViewModel.class);

        workoutViewModel.init(1);
        workoutViewModel.getWorkout().observe(this, w -> {
            if (w != null){
                this.workouts = w;
                this.recycleViewAdapter.swapData(this.workouts);
            }
        });

    }
}
