package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Workouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Workouts.Adapters.WorkoutListRecycleViewAdapter;
import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Workouts.Adapters.WorkoutListViewHolderInterface;
import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Workouts.Dialogs.AddWorkoutDialog;
import com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutViewActivity.WorkoutViewActivity;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;

public class WorkoutsListFragment extends Fragment
    implements View.OnClickListener, WorkoutListViewHolderInterface, AddWorkoutDialog.ActionsInterface {

  private static final String TAG = "WORKOUT_LIST_FRAGMENT";

  public WorkoutsListFragment() {}

  public static WorkoutsListFragment getInstance() {
    return new WorkoutsListFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_workouts_list, container, false);
    RecyclerView recyclerView = view.findViewById(R.id.workoutsList);

    RecyclerView.LayoutManager recyclerViewLayoutManager = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(recyclerViewLayoutManager);

    WorkoutListRecycleViewAdapter recycleViewAdapter = new WorkoutListRecycleViewAdapter(this);
    recyclerView.setAdapter(recycleViewAdapter);

    FloatingActionButton actionButton = view.findViewById(R.id.floating_action_add_workout);
    actionButton.setOnClickListener(this);

    return view;
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.floating_action_add_workout:
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        AddWorkoutDialog dialogTest = new AddWorkoutDialog();
        dialogTest.show(fragmentTransaction, "tag");
        break;
    }
  }

  @Override
  public void onWorkoutSelected(int workoutId) {
    Intent workoutView = new Intent(getContext(), WorkoutViewActivity.class);
    //TODO: Neat use of streams to find selected workout might be re-used in the near future
//    Workout workout =
//        Objects.requireNonNull(this.workoutViewModel.getWorkout().getValue())
//            .stream()
//            .filter(w -> w.getId() == workoutId)
//            .findFirst()
//            .get();
//
//    workoutView.putExtra("workoutData", workout);
    startActivity(workoutView);
  }

  @Override
  public void onSaveAction(Workout result) {
    //TODO: handle adding new workout here
    Log.i(TAG, "On save action triggered");
  }
}
