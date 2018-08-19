package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Workouts;

import android.arch.lifecycle.ViewModelProviders;
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
import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.MainActivity;
import com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutViewActivity.WorkoutViewActivity;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents.DaggerWorkoutViewModelFactoryComponent;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents.WorkoutViewModelFactoryComponent;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerModules.WorkoutViewModelFactoryModule;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.Factories.WorkoutViewModelFactory;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.WorkoutViewModel;
import java.util.List;
import java.util.Objects;

public class WorkoutsListFragment extends Fragment
    implements View.OnClickListener, WorkoutListViewHolderInterface, AddWorkoutDialog.ActionsInterface {

  private RecyclerView recyclerView;
  private WorkoutListRecycleViewAdapter recycleViewAdapter;
  private WorkoutViewModel workoutViewModel;

  public WorkoutsListFragment() {}

  public static WorkoutsListFragment getInstance() {
    return new WorkoutsListFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // TODO: This should be handled with fragment injection
    WorkoutViewModelFactoryComponent workoutViewModelFactoryComponent =
        DaggerWorkoutViewModelFactoryComponent.builder()
            .workoutViewModelFactoryModule(new WorkoutViewModelFactoryModule())
            .build();

    WorkoutViewModelFactory workoutViewModelFactory =
        workoutViewModelFactoryComponent.provideWorkoutViewModelFactory();

    this.workoutViewModel =
        ViewModelProviders.of(this, workoutViewModelFactory).get(WorkoutViewModel.class);

    //TODO: pass user is using bundle
    workoutViewModel.init(MainActivity.user.getId());
    workoutViewModel
        .getWorkout()
        .observe(this, w -> this.updateWorkoutsList(w, false));
  }

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_workouts_list, container, false);
    this.recyclerView = view.findViewById(R.id.workoutsList);

    RecyclerView.LayoutManager recyclerViewLayoutManager = new LinearLayoutManager(getContext());
    this.recyclerView.setLayoutManager(recyclerViewLayoutManager);

    this.recycleViewAdapter = new WorkoutListRecycleViewAdapter(this);
    this.recyclerView.setAdapter(recycleViewAdapter);

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

  public void updateWorkoutsList(List<Workout> workouts, boolean newWorkout) {
//    Log.i("something", workouts.toString());
    this.recycleViewAdapter.swapData(workouts);
    if (newWorkout) {
      recyclerView.smoothScrollToPosition(0);
    }
  }

  @Override
  public void onWorkoutSelected(int workoutId) {
    Intent workoutView = new Intent(getContext(), WorkoutViewActivity.class);
    Workout workout =
        Objects.requireNonNull(this.workoutViewModel.getWorkout().getValue())
            .stream()
            .filter(w -> w.getId() == workoutId)
            .findFirst()
            .get();

    workoutView.putExtra("workoutData", workout);
    startActivity(workoutView);
  }

  @Override
  public void onSaveAction(Workout result) {
    this.workoutViewModel.addWorkout(result);
    this.updateWorkoutsList(this.workoutViewModel.getWorkout().getValue(), true);
  }
}
