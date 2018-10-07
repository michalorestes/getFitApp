package com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.jds.fitnessjunkiess.getfitapp.compoundViews.customCheckbox.CustomCheckbox;
import com.jds.fitnessjunkiess.getfitapp.data.pojo.ExerciseTypes;
import com.jds.fitnessjunkiess.getfitapp.data.pojo.ExercisesFilters;
import com.jds.fitnessjunkiess.getfitapp.data.pojo.MuscleGroups;
import com.jds.fitnessjunkiess.getfitapp.R;
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.ExerciseViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO: ExerciseTypes logic needs to be refactor to exclude custom
public class ExerciseFilterDialog extends DialogFragment implements DialogInterface.OnClickListener, View.OnClickListener {

  private ExercisesFilters exercisesFilters;
  private Map<String, CustomCheckbox> typesCheckboxMap;
  private Map<String, CustomCheckbox> muscleGroupsCheckboxMap;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.typesCheckboxMap = new HashMap<>();
    this.muscleGroupsCheckboxMap = new HashMap<>();
    ExerciseViewModel exerciseViewModel = ViewModelProviders.of(getActivity()).get(ExerciseViewModel.class);
    this.exercisesFilters = exerciseViewModel.getExercisesFilter();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    View rootView = inflater.inflate(R.layout.dialog_filter_exercises, null);

    //TODO: Can be further refactored by implementing keys for CustomCheckbox :)
    this.typesCheckboxMap
        .put(ExerciseTypes.ALL, rootView.findViewById(R.id.all_types_customcheckbox));
    this.typesCheckboxMap
        .put(ExerciseTypes.BODY_WEIGHT, rootView.findViewById(R.id.body_weight_customcheckbox));
    this.typesCheckboxMap
        .put(ExerciseTypes.WEIGHTS, rootView.findViewById(R.id.weights_customcheckbox));
    this.typesCheckboxMap
        .put(ExerciseTypes.CARDIO, rootView.findViewById(R.id.cardio_customcheckbox));
    this.typesCheckboxMap
        .put(ExerciseTypes.CUSTOM, rootView.findViewById(R.id.custom_customcheckbox));

    this.muscleGroupsCheckboxMap
        .put(MuscleGroups.ALL, rootView.findViewById(R.id.all_muscle_groups_mg_customcheckbox));
    this.muscleGroupsCheckboxMap
        .put(MuscleGroups.BACK, rootView.findViewById(R.id.back_mg_customcheckbox));
    this.muscleGroupsCheckboxMap
        .put(MuscleGroups.BICEPS, rootView.findViewById(R.id.biceps_mg_customcheckbox));
    this.muscleGroupsCheckboxMap
        .put(MuscleGroups.CORE, rootView.findViewById(R.id.core_mg_customcheckbox));
    this.muscleGroupsCheckboxMap
        .put(MuscleGroups.CHEST, rootView.findViewById(R.id.chest_mg_customcheckbox));
    this.muscleGroupsCheckboxMap
        .put(MuscleGroups.CALVES, rootView.findViewById(R.id.calves_mg_customcheckbox));
    this.muscleGroupsCheckboxMap
        .put(MuscleGroups.FOREARMS, rootView.findViewById(R.id.forearms_mg_customcheckbox));
    this.muscleGroupsCheckboxMap
        .put(MuscleGroups.HAMSTRINGS, rootView.findViewById(R.id.hamstrings_mg_customcheckbox));
    this.muscleGroupsCheckboxMap
        .put(MuscleGroups.TRAPEZIUS, rootView.findViewById(R.id.trapezious_mg_customcheckbox));
    this.muscleGroupsCheckboxMap
        .put(MuscleGroups.TRICEPS, rootView.findViewById(R.id.triceps_mg_customcheckbox));
    this.muscleGroupsCheckboxMap
        .put(MuscleGroups.SHOULDERS, rootView.findViewById(R.id.shoulders_mg_customcheckbox));
    this.muscleGroupsCheckboxMap
        .put(MuscleGroups.QUADRICEPS, rootView.findViewById(R.id.quadriceps_mg_customcheckbox));

    for (Map.Entry<String, CustomCheckbox> customCheckbox : this.muscleGroupsCheckboxMap.entrySet()) {
      customCheckbox.getValue().setOnClickListener(this);
    }

    for (Map.Entry<String, CustomCheckbox> customCheckbox : this.typesCheckboxMap.entrySet()) {
      customCheckbox.getValue().setOnClickListener(this);
    }

    this.setFiltersOnUi();

    builder.setView(rootView)
        .setPositiveButton("Filter", this)
        .setNegativeButton("Cancel", this);

    return builder.create();
  }

  @Override
  public void onClick(View v) {
    CustomCheckbox ccb = (CustomCheckbox) v;
    if (this.typesCheckboxMap.containsValue(v)) {
      if (ccb.getId() != R.id.all_types_customcheckbox) {
        this.typesCheckboxMap.get(ExerciseTypes.ALL).setChecked(false);
      } else if (ccb.getId() == R.id.all_types_customcheckbox) {
        for (Map.Entry<String, CustomCheckbox> checkbox : this.typesCheckboxMap.entrySet()) {
          checkbox.getValue().setChecked(false);
        }
      }
    }

    if (this.muscleGroupsCheckboxMap.containsValue(v)) {
      if (ccb.getId() != R.id.all_muscle_groups_mg_customcheckbox) {
        this.muscleGroupsCheckboxMap.get(MuscleGroups.ALL).setChecked(false);
      } else if (ccb.getId() == R.id.all_muscle_groups_mg_customcheckbox) {
        for (Map.Entry<String, CustomCheckbox> checkbox : this.muscleGroupsCheckboxMap.entrySet()) {
          checkbox.getValue().setChecked(false);
        }
      }
    }

    ccb.toggle();
  }

  @Override
  public void onClick(DialogInterface dialog, int id) {
    if (id == DialogInterface.BUTTON_POSITIVE) {
      ExerciseViewModel exerciseViewModel = ViewModelProviders.of(getActivity()).get(ExerciseViewModel.class);
      exerciseViewModel.setFilterMutableLiveData(getNewFilters());
      dismiss();
    } else if (id == DialogInterface.BUTTON_NEGATIVE) {
      dismiss();
    }
  }

  private void setFiltersOnUi() {
    for (String muscleGroup : this.exercisesFilters.getMuscleGroups()) {
      this.muscleGroupsCheckboxMap.get(muscleGroup).setChecked(true);
    }

    for (String type : this.exercisesFilters.getTypes()) {
      this.typesCheckboxMap.get(type).setChecked(true);
    }

    if (exercisesFilters.isCustom()) {
      this.typesCheckboxMap.get(ExerciseTypes.CUSTOM).setChecked(true);
      this.typesCheckboxMap.get(ExerciseTypes.ALL).setChecked(false);
    }
  }

  private ExercisesFilters getNewFilters() {
    ExercisesFilters exercisesFilter = new ExercisesFilters();
    exercisesFilter.getTypes().addAll(this.getNewTypesFilters());
    exercisesFilter.getMuscleGroups().addAll(this.getNewMuscleGroupsFilters());
    if (this.typesCheckboxMap.get(ExerciseTypes.CUSTOM).isChecked()) {
      exercisesFilter.setCustom(true);
    }
    return exercisesFilter;
  }

  private List<String> getNewTypesFilters() {
    List<String> values = new ArrayList<>();
    for (Map.Entry<String, CustomCheckbox> entry : this.typesCheckboxMap.entrySet()) {
      String key = entry.getKey();
      CustomCheckbox checkbox = entry.getValue();

      if (key.equals(ExerciseTypes.CUSTOM)) {
        continue;
      }

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

  private List<String> getNewMuscleGroupsFilters() {
    List<String> values = new ArrayList<>();
    for (Map.Entry<String, CustomCheckbox> entry : this.muscleGroupsCheckboxMap.entrySet()) {
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
