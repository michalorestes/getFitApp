package com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jds.fitnessjunkiess.getfitapp.R;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Exercise;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.WorkoutExerciseAssignment;
import com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase.adapters.AbstractExercisesAdapter;
import com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase.adapters.ExercisesAdapter;
import com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase.adapters.WorkoutContextExercisesAdapter;

import java.util.List;

public class ExercisesListFragment extends Fragment implements AbstractExercisesAdapter.OnItemMenuClickInterface {
  private OnFragmentInteractionListener mListener;
  private AbstractExercisesAdapter recyclerViewerAdapter;

  public ExercisesListFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_exercises_list, container, false);

    RecyclerView recyclerView = rootView.findViewById(R.id.exercise_view_recycle_viewer);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(rootView.getContext());
    recyclerView.setLayoutManager(layoutManager);

    if (this.mListener.getActiveWorkout() == null) {
      this.recyclerViewerAdapter = new ExercisesAdapter(this);
    } else {
      this.recyclerViewerAdapter = new WorkoutContextExercisesAdapter(this);
    }

    recyclerView.setAdapter(recyclerViewerAdapter);

    return rootView;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
      mListener = (OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString()
          + " must implement OnFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  public void updateDataSet(List<Exercise> exercises) {
    this.recyclerViewerAdapter.updateDataSet(exercises);
  }

  @Override
  public Context getAppContext() {
    return getContext();
  }

  @Override
  public List<Workout> getWorkoutsList() {
    return this.mListener.getWorkoutsList();
  }

  @Override
  public void insertExerciseAssignment(Exercise exercise, Workout workout) {
    WorkoutExerciseAssignment exerciseAssignment = new WorkoutExerciseAssignment();
    exerciseAssignment.setExerciseId(exercise.getId());
    exerciseAssignment.setWorkoutId(workout.getId());
    this.mListener.insertExerciseAssignment(exerciseAssignment);

    Toast.makeText(
        getContext(),
        exercise.getName() + " added to " + workout.getName(),
        Toast.LENGTH_LONG).show();
  }

  @Override
  public void insertExerciseAssignment(Exercise exercise) {
    WorkoutExerciseAssignment exerciseAssignment = new WorkoutExerciseAssignment();
    exerciseAssignment.setExerciseId(exercise.getId());
    exerciseAssignment.setWorkoutId(this.mListener.getActiveWorkout().getId());
    this.mListener.insertExerciseAssignment(exerciseAssignment);

    Toast.makeText(
        getContext(),
        exercise.getName() + " added to " + this.mListener.getActiveWorkout().getName(),
        Toast.LENGTH_LONG).show();
  }

  public interface OnFragmentInteractionListener {
    Workout getActiveWorkout();
    List<Workout> getWorkoutsList();
    void insertExerciseAssignment(WorkoutExerciseAssignment workoutExerciseAssignment);
  }
}
