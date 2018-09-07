package com.jds.fitnessjunkiess.getfitapp.Dialogs;

import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.jds.fitnessjunkiess.getfitapp.CompoundViews.CustomCheckbox.CustomCheckbox;
import com.jds.fitnessjunkiess.getfitapp.Data.DataModels.MuscleGroup;
import com.jds.fitnessjunkiess.getfitapp.Pojo.ExerciseTypes;
import com.jds.fitnessjunkiess.getfitapp.Pojo.ExercisesFilter;
import com.jds.fitnessjunkiess.getfitapp.Pojo.MuscleGroups;
import com.jds.fitnessjunkiess.getfitapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExerciseFilterDialog extends DialogFragment {

  public interface ActionsInterface {
    void onPositiveClick(ExercisesFilter exercisesFilters);
  }
  private ExercisesFilter exercisesFilters;
  private Map<String, CustomCheckbox> typesCheckboxMap;
  private Map<String, CustomCheckbox> muscleGroupsCheckboxMap;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.typesCheckboxMap = new HashMap<>();
    this.muscleGroupsCheckboxMap = new HashMap<>();
    this.exercisesFilters = getArguments().getParcelable("exerciseFilters");
    if (this.exercisesFilters == null) {
      this.exercisesFilters = new ExercisesFilter("", "");
    }
  }

  private void setFiltersOnUi() {
    this.updateExerciseTypesFilter();
    this.updateMuscleGroupsFilter();
  }

  private void updateMuscleGroupsFilter() {
    for (String group : this.exercisesFilters.muscleGroup) {
      switch (group) {
        case MuscleGroups.BACK:
          this.muscleGroupsCheckboxMap.get(MuscleGroups.BACK).setChecked(true);
          break;
        case MuscleGroups.BICEPS:
          this.muscleGroupsCheckboxMap.get(MuscleGroups.BICEPS).setChecked(true);
          break;
        case MuscleGroups.CORE:
          this.muscleGroupsCheckboxMap.get(MuscleGroups.CORE).setChecked(true);
          break;
        case MuscleGroups.CHEST:
          this.muscleGroupsCheckboxMap.get(MuscleGroups.CHEST).setChecked(true);
          break;
        case MuscleGroups.CALVES:
          this.muscleGroupsCheckboxMap.get(MuscleGroups.CALVES).setChecked(true);
          break;
        case MuscleGroups.FOREARMS:
          this.muscleGroupsCheckboxMap.get(MuscleGroups.FOREARMS).setChecked(true);
          break;
        case MuscleGroups.HAMSTRINGS:
          this.muscleGroupsCheckboxMap.get(MuscleGroups.HAMSTRINGS).setChecked(true);
          break;
        case MuscleGroups.TRAPEZIUS:
          this.muscleGroupsCheckboxMap.get(MuscleGroups.TRAPEZIUS).setChecked(true);
          break;
        case MuscleGroups.TRICEPS:
          this.muscleGroupsCheckboxMap.get(MuscleGroups.TRICEPS).setChecked(true);
          break;
        case MuscleGroups.SHOULDERS:
          this.muscleGroupsCheckboxMap.get(MuscleGroups.SHOULDERS).setChecked(true);
          break;
        case MuscleGroups.QUADRICEPS:
          this.muscleGroupsCheckboxMap.get(MuscleGroups.QUADRICEPS).setChecked(true);
          break;
        case  MuscleGroups.ALL:
          this.muscleGroupsCheckboxMap.get(MuscleGroups.BACK).setChecked(true);
          this.muscleGroupsCheckboxMap.get(MuscleGroups.BICEPS).setChecked(true);
          this.muscleGroupsCheckboxMap.get(MuscleGroups.CORE).setChecked(true);
          this.muscleGroupsCheckboxMap.get(MuscleGroups.CHEST).setChecked(true);
          this.muscleGroupsCheckboxMap.get(MuscleGroups.CALVES).setChecked(true);
          this.muscleGroupsCheckboxMap.get(MuscleGroups.FOREARMS).setChecked(true);
          this.muscleGroupsCheckboxMap.get(MuscleGroups.HAMSTRINGS).setChecked(true);
          this.muscleGroupsCheckboxMap.get(MuscleGroups.TRAPEZIUS).setChecked(true);
          this.muscleGroupsCheckboxMap.get(MuscleGroups.TRICEPS).setChecked(true);
          this.muscleGroupsCheckboxMap.get(MuscleGroups.SHOULDERS).setChecked(true);
          this.muscleGroupsCheckboxMap.get(MuscleGroups.QUADRICEPS).setChecked(true);
          break;
      }
    }
  }

  private void updateExerciseTypesFilter() {
    for (String type : this.exercisesFilters.types) {
      switch (type) {
        case ExerciseTypes.WEIGHTS:
          this.typesCheckboxMap.get(ExerciseTypes.WEIGHTS).setChecked(true);
          break;
        case ExerciseTypes.CARDIO:
          this.typesCheckboxMap.get(ExerciseTypes.CARDIO).setChecked(true);
          break;
        case ExerciseTypes.BODY_WEIGHT:
          this.typesCheckboxMap.get(ExerciseTypes.BODY_WEIGHT).setChecked(true);
          break;
        case ExerciseTypes.CUSTOM:
          this.typesCheckboxMap.get(ExerciseTypes.CUSTOM).setChecked(true);
          break;
        case  ExerciseTypes.ALL:
          this.typesCheckboxMap.get(ExerciseTypes.WEIGHTS).setChecked(true);
          this.typesCheckboxMap.get(ExerciseTypes.CARDIO).setChecked(true);
          this.typesCheckboxMap.get(ExerciseTypes.BODY_WEIGHT).setChecked(true);
          break;
      }
    }
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    View rootView = inflater.inflate(R.layout.dialog_filter_exercises, null);

    this.typesCheckboxMap.put(ExerciseTypes.ALL, rootView.findViewById(R.id.all_types_customcheckbox));
    this.typesCheckboxMap.put(ExerciseTypes.BODY_WEIGHT, rootView.findViewById(R.id.body_weight_customcheckbox));
    this.typesCheckboxMap.put(ExerciseTypes.WEIGHTS, rootView.findViewById(R.id.weights_customcheckbox));
    this.typesCheckboxMap.put(ExerciseTypes.CARDIO, rootView.findViewById(R.id.cardio_customcheckbox));
    this.typesCheckboxMap.put(ExerciseTypes.CUSTOM, rootView.findViewById(R.id.custom_customcheckbox));

    this.muscleGroupsCheckboxMap.put(MuscleGroups.ALL, rootView.findViewById(R.id.all_muscle_groups_mg_customcheckbox));
    this.muscleGroupsCheckboxMap.put(MuscleGroups.BACK, rootView.findViewById(R.id.back_mg_customcheckbox));
    this.muscleGroupsCheckboxMap.put(MuscleGroups.BICEPS, rootView.findViewById(R.id.biceps_mg_customcheckbox));
    this.muscleGroupsCheckboxMap.put(MuscleGroups.CORE, rootView.findViewById(R.id.core_mg_customcheckbox));
    this.muscleGroupsCheckboxMap.put(MuscleGroups.CHEST, rootView.findViewById(R.id.chest_mg_customcheckbox));
    this.muscleGroupsCheckboxMap.put(MuscleGroups.CALVES, rootView.findViewById(R.id.calves_mg_customcheckbox));
    this.muscleGroupsCheckboxMap.put(MuscleGroups.FOREARMS, rootView.findViewById(R.id.forearms_mg_customcheckbox));
    this.muscleGroupsCheckboxMap.put(MuscleGroups.HAMSTRINGS, rootView.findViewById(R.id.hamstrings_mg_customcheckbox));
    this.muscleGroupsCheckboxMap.put(MuscleGroups.TRAPEZIUS, rootView.findViewById(R.id.trapezious_mg_customcheckbox));
    this.muscleGroupsCheckboxMap.put(MuscleGroups.TRICEPS, rootView.findViewById(R.id.triceps_mg_customcheckbox));
    this.muscleGroupsCheckboxMap.put(MuscleGroups.SHOULDERS, rootView.findViewById(R.id.shoulders_mg_customcheckbox));
    this.muscleGroupsCheckboxMap.put(MuscleGroups.QUADRICEPS, rootView.findViewById(R.id.quadriceps_mg_customcheckbox));

    this.setFiltersOnUi();

    builder.setView(rootView)
        .setPositiveButton("Awesome", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int id) {
            ActionsInterface context2 = (ActionsInterface) getActivity();
            if (context2 != null) {
              context2.onPositiveClick(getNewFilters());
            }
            dismiss();
          }
        })
        .setNegativeButton("Negative", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int id) {

          }
        });

    return builder.create();
  }

  private ExercisesFilter getNewFilters() {
    ExercisesFilter exercisesFilter = new ExercisesFilter();
    exercisesFilter.types.addAll(this.getTypesFilters());
    exercisesFilter.muscleGroup.addAll(this.getMuscleGroupsFilters());

    return exercisesFilter;
  }

  private List<String> getTypesFilters() {
    List<String> values = new ArrayList<>();
    for(Map.Entry<String, CustomCheckbox> entry : this.typesCheckboxMap.entrySet()) {
      String key = entry.getKey();
      CustomCheckbox checkbox = entry.getValue();

      if (key.equals(ExerciseTypes.ALL) && checkbox.isChecked()) {
        values.clear();
        values.add(key);
        break;
      }

      if (checkbox.isChecked()) {
        values.add(key);
      }
    }

    return values;
  }

  private List<String> getMuscleGroupsFilters() {
    List<String> values = new ArrayList<>();
    for(Map.Entry<String, CustomCheckbox> entry : this.muscleGroupsCheckboxMap.entrySet()) {
      String key = entry.getKey();
      CustomCheckbox checkbox = entry.getValue();

      if (key.equals(MuscleGroups.ALL) && checkbox.isChecked()) {
        values.clear();
        values.add(key);
        break;
      }

      if (checkbox.isChecked()) {
        values.add(key);
      }
    }

    return values;
  }

}
