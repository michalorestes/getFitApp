package com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutsActivity;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutsActivity.Adapters.WorkoutListRecycleViewAdapter;
import com.jds.fitnessjunkiess.getfitapp.CustomViews.AddBoxView;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;
import java.util.List;
import java.util.Objects;

public class WorkoutsListFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private WorkoutListRecycleViewAdapter recycleViewAdapter;
    private onWorkoutSelectedInterface onWorkoutSelectedInterface;
    private AddBoxView addBoxView;
    private FloatingActionButton actionButton;
    private InputMethodManager imm;

    public WorkoutsListFragment() {

    }

    public interface onWorkoutSelectedInterface {
        void onWorkoutSelected(int workoutId);
        List<Workout> onWorkoutListRequested();
        void onAddWorkout(String workoutName);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.imm = (InputMethodManager) getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE);

        this.addBoxView = new AddBoxView(Objects.requireNonNull(getContext()));

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.BOTTOM
        );

        this.addBoxView.setLayoutParams(layoutParams);

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
                        this.onWorkoutSelectedInterface.onWorkoutListRequested(),
                        this.onWorkoutSelectedInterface
                );
        this.recyclerView.setAdapter(recycleViewAdapter);

        this.actionButton =
                new FloatingActionButton(Objects.requireNonNull(getContext()));

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.RIGHT | Gravity.BOTTOM
        );

        layoutParams.setMargins(0,0,60,90);
        actionButton.setLayoutParams(layoutParams);
        actionButton.setOnClickListener(this);
        actionButton.setId(R.id.floating_action_add_workout);

        FrameLayout overlayLayout = view.findViewById(R.id.overlay_layout);
        overlayLayout.addView(actionButton);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.recycleViewAdapter.swapData(
                this.onWorkoutSelectedInterface.onWorkoutListRequested()
        );
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            onWorkoutSelectedInterface = (onWorkoutSelectedInterface) context;
        }
    }

    public void updateWorkoutsList(List<Workout> workouts, boolean newWorkout) {
        this.recycleViewAdapter.swapData(workouts);
        if (newWorkout){
            recyclerView
                    .scrollToPosition(
                            this.onWorkoutSelectedInterface.onWorkoutListRequested().size()-1
                    );
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floating_action_add_workout:
                Log.d("***", "floating action button: clicked ");
                FrameLayout frameLayout = (FrameLayout) v.getParent();
                this.addBoxView(frameLayout, v);
                break;
            case R.id.overlay_layout:
                removeAddBoxView((FrameLayout) v);
                break;
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
        this.imm.showSoftInput(this.addBoxView.getInputTxt(), InputMethodManager.SHOW_IMPLICIT);
    }

    private void removeAddBoxView(FrameLayout viewGroup) {
        viewGroup.setClickable(false);
        this.imm.hideSoftInputFromWindow(this.addBoxView.getInputTxt().getWindowToken(), 0);
        viewGroup.removeView(this.addBoxView);
        viewGroup.setBackgroundColor(
                getResources().getColor(R.color.cardview_shadow_end_color, null)
        );
        viewGroup.addView(this.actionButton);
    }
}
