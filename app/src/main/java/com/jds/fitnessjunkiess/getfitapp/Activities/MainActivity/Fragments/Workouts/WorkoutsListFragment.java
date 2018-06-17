package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Workouts;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Workouts.Adapters.WorkoutListRecycleViewAdapter;
import com.jds.fitnessjunkiess.getfitapp.CustomViews.AddBoxView;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents.DaggerWorkoutViewModelFactoryComponent;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents.WorkoutViewModelFactoryComponent;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerModules.WorkoutViewModelFactoryModule;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.Factories.WorkoutViewModelFactory;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.WorkoutViewModel;

import java.util.List;
import java.util.Objects;

public class WorkoutsListFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private WorkoutListRecycleViewAdapter recycleViewAdapter;
    private AddBoxView addBoxView;
    private FloatingActionButton actionButton;
    private InputMethodManager imm;
    private WorkoutViewModel workoutViewModel;
    private WorkoutsListInterface workoutsListInterface;

    public WorkoutsListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: This should be handled with fragment injection
        WorkoutViewModelFactoryComponent workoutViewModelFactoryComponent =
                DaggerWorkoutViewModelFactoryComponent
                        .builder()
                        .workoutViewModelFactoryModule(new WorkoutViewModelFactoryModule())
                        .build();

        WorkoutViewModelFactory workoutViewModelFactory = workoutViewModelFactoryComponent
                .provideWorkoutViewModelFactory();

        this.workoutViewModel =
                ViewModelProviders.of(this, workoutViewModelFactory)
                        .get(WorkoutViewModel.class);

        workoutViewModel.init(7);
        workoutViewModel.getWorkout().observe(this, w -> {
            this.updateWorkoutsList(w, false);
        });

        this.imm = (InputMethodManager) getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workouts_list, container, false);
        this.recyclerView = view.findViewById(R.id.workoutsList);
//        this.recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager recyclerViewLayoutManager =
                new LinearLayoutManager(getContext());
        this.recyclerView.setLayoutManager(recyclerViewLayoutManager);

        this.recycleViewAdapter =
                new WorkoutListRecycleViewAdapter(this.workoutsListInterface);
        this.recyclerView.setAdapter(recycleViewAdapter);

        this.actionButton = view.findViewById(R.id.floating_action_add_workout);
        this.actionButton.setOnClickListener(this);

        this.addBoxView = new AddBoxView(Objects.requireNonNull(getContext()));
        FrameLayout.LayoutParams addBoxLayoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.BOTTOM
        );

        this.addBoxView.setLayoutParams(addBoxLayoutParams);
        this.addBoxView.getButton().setOnClickListener(this);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            workoutsListInterface = (WorkoutsListInterface) context;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floating_action_add_workout:
                this.addBoxView((FrameLayout) v.getParent(), v);
                break;
            case R.id.overlay_layout:
                removeAddBoxView((FrameLayout) v);
                break;
            case R.id.button:
                this.addNewWorkout(this.addBoxView.getInputText());
                break;
        }
    }

    private void addNewWorkout(String workoutName) {
        Workout workout = new Workout();
        workout.setName(workoutName);
        workout.setUserId(7);
        this.workoutViewModel.addWorkout(workout).observe(this, w -> {
            this.workoutViewModel.getWorkout().getValue().add(0, w);
            this.updateWorkoutsList(this.workoutViewModel.getWorkout().getValue(), true);
        });
    }

    public void updateWorkoutsList(List<Workout> workouts, boolean newWorkout) {
        this.recycleViewAdapter.swapData(workouts);
        if (newWorkout){
            recyclerView.smoothScrollToPosition(0);
        }
    }

    private void addBoxView(FrameLayout viewGroup, View v) {
        viewGroup.setClickable(true);
        viewGroup.setOnClickListener(this);
        viewGroup.setBackgroundColor(
                getResources().getColor(R.color.cardview_shadow_start_color, null)
        );
        viewGroup.removeView(v);
        viewGroup.addView(this.addBoxView);
        this.addBoxView.requestInputFocus();
        this.imm.showSoftInput(this.addBoxView.getInput(), InputMethodManager.SHOW_IMPLICIT);
    }

    private void removeAddBoxView(FrameLayout viewGroup) {
        viewGroup.setClickable(false);
        this.imm.hideSoftInputFromWindow(this.addBoxView.getInput().getWindowToken(), 0);
        viewGroup.removeView(this.addBoxView);
        viewGroup.setBackgroundColor(
                getResources().getColor(R.color.cardview_shadow_end_color, null)
        );
        viewGroup.addView(this.actionButton);
    }
}
