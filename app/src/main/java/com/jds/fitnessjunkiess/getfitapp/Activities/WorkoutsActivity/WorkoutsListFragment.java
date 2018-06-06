package com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutsActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutsActivity.Adapters.WorkoutListRecycleViewAdapter;
import com.jds.fitnessjunkiess.getfitapp.CustomViews.AddBoxView;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;
import java.util.List;
import java.util.Objects;

import dagger.android.AndroidInjectionModule;

public class WorkoutsListFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private WorkoutListRecycleViewAdapter recycleViewAdapter;
    private onWorkoutInteractionInterface onWorkoutInteractionInterface;
    private AddBoxView addBoxView;
    private FloatingActionButton actionButton;
    private InputMethodManager imm;

    public WorkoutsListFragment() {

    }

    public interface onWorkoutInteractionInterface {
        void onWorkoutSelected(int workoutId);
        List<Workout> onWorkoutListRequested();
        void onAddWorkout(String workoutName);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.imm = (InputMethodManager) getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workouts_list, container, false);
        this.recyclerView = view.findViewById(R.id.workoutsList);
        this.recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager recyclerViewLayoutManager =
                new LinearLayoutManager(getContext());
        this.recyclerView.setLayoutManager(recyclerViewLayoutManager);

        this.recycleViewAdapter =
                new WorkoutListRecycleViewAdapter(
                        this.onWorkoutInteractionInterface.onWorkoutListRequested(),
                        this.onWorkoutInteractionInterface
                );
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.recycleViewAdapter.swapData(
                this.onWorkoutInteractionInterface.onWorkoutListRequested()
        );
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            onWorkoutInteractionInterface = (onWorkoutInteractionInterface) context;
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
                this.onWorkoutInteractionInterface.onAddWorkout(this.addBoxView.getInputText());
                break;
        }
    }

    public void updateWorkoutsList(List<Workout> workouts, boolean newWorkout) {
        this.recycleViewAdapter.swapData(workouts);
        if (newWorkout){
            recyclerView.smoothScrollToPosition(0);
        }
    }

    private void addBoxView(FrameLayout viewGroup, View v){
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
