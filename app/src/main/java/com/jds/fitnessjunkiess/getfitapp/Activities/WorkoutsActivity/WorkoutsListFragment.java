package com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutsActivity;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutsActivity.Adapters.WorkoutListRecycleViewAdapter;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents.DaggerWorkoutViewModelFactoryComponent;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents.WorkoutViewModelFactoryComponent;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerModules.WorkoutViewModelFactoryModule;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.Factories.WorkoutViewModelFactory;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.WorkoutViewModel;
import java.util.ArrayList;
import java.util.List;

public class WorkoutsListFragment extends Fragment {

    private WorkoutViewModelFactory workoutViewModelFactory;
    private WorkoutViewModel workoutViewModel;
    private RecyclerView recyclerView;
    private WorkoutListRecycleViewAdapter recycleViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    private onWorkoutSelectedInterface onWorkoutSelectedInterface;
    private int userId;

    public WorkoutsListFragment() {

    }

    public interface onWorkoutSelectedInterface {
        void onWorkoutSelected(int workoutId);
        List<Workout> onWorkoutListRequested();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workouts_list, container, false);
        this.recyclerView = view.findViewById(R.id.workoutsList);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerViewLayoutManager = new LinearLayoutManager(getContext());
        this.recyclerView.setLayoutManager(recyclerViewLayoutManager);

        this.recycleViewAdapter =
                new WorkoutListRecycleViewAdapter(this.onWorkoutSelectedInterface.onWorkoutListRequested(), this.onWorkoutSelectedInterface);
        this.recyclerView.setAdapter(recycleViewAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.recycleViewAdapter.swapData(this.onWorkoutSelectedInterface.onWorkoutListRequested());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            onWorkoutSelectedInterface = (onWorkoutSelectedInterface) context;
        }
    }

    public void updateWorkoutsList(List<Workout> workouts) {
        this.recycleViewAdapter.swapData(workouts);
    }
}
