package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Workouts;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.jds.fitnessjunkiess.getfitapp.Entities.WorkoutExercise;
import com.jds.fitnessjunkiess.getfitapp.R;
import com.jds.fitnessjunkiess.getfitapp.Repositories.WorkoutsRepository;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.WorkoutExerciseViewModel;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.WorkoutsViewModel;

import java.util.List;

public class WorkoutsListFragment extends Fragment
    implements View.OnClickListener, WorkoutListViewHolderInterface, AddWorkoutDialog.ActionsInterface {

  private static final String TAG = "WORKOUT_LIST_FRAGMENT";

  private WorkoutsViewModel workoutsViewModel;
  private WorkoutListRecycleViewAdapter recycleViewAdapter;

  public WorkoutsListFragment() {}

  public static WorkoutsListFragment getInstance() {
    return new WorkoutsListFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    this.workoutsViewModel = ViewModelProviders.of(this).get(WorkoutsViewModel.class);
    WorkoutExerciseViewModel workoutExerciseViewModel = ViewModelProviders.of(this).get(WorkoutExerciseViewModel.class);
    workoutExerciseViewModel.selectAll(1).observe(this, new Observer<List<WorkoutExercise>>() {
      @Override
      public void onChanged(@Nullable List<WorkoutExercise> workoutExercises) {
        if (workoutExercises != null) {
          Log.d(TAG, "Size: " + workoutExercises.size());
        }
      }
    });

    WorkoutExercise workoutExercise = new WorkoutExercise();
    workoutExercise.setId(0);
    workoutExercise.setWorkoutId(1);
    workoutExercise.setExerciseId(11);
    workoutExercise.setReps(111);
    workoutExercise.setLength("traalal");

    workoutExerciseViewModel.insert(workoutExercise);

    this.workoutsViewModel.getData().observe(this, new Observer<List<Workout>>() {
      @Override
      public void onChanged(@Nullable List<Workout> workouts) {
        if (workouts != null) {
          recycleViewAdapter.updateDataSet(workouts);
        }
      }
    });
  }

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_workouts_list, container, false);
    RecyclerView recyclerView = view.findViewById(R.id.workoutsList);

    RecyclerView.LayoutManager recyclerViewLayoutManager = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(recyclerViewLayoutManager);

    this.recycleViewAdapter = new WorkoutListRecycleViewAdapter(this);
    recyclerView.setAdapter(this.recycleViewAdapter);

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
  public void onWorkoutSelected(Workout workoutId) {
    Intent workoutView = new Intent(getContext(), WorkoutViewActivity.class);
//    workoutView.putExtra("workoutData", workout);
    startActivity(workoutView);
  }

  @Override
  public void onSaveAction(Workout result) {
    this.workoutsViewModel.insert(result);
    Log.i(TAG, "On save action triggered");
  }
}
