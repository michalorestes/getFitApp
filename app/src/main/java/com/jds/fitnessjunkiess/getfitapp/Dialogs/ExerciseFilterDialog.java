package com.jds.fitnessjunkiess.getfitapp.Dialogs;

import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.jds.fitnessjunkiess.getfitapp.CompoundViews.CustomCheckbox.CustomCheckbox;
import com.jds.fitnessjunkiess.getfitapp.Pojo.ExerciseTypes;
import com.jds.fitnessjunkiess.getfitapp.Pojo.ExercisesFilter;
import com.jds.fitnessjunkiess.getfitapp.Pojo.MuscleGroups;
import com.jds.fitnessjunkiess.getfitapp.R;

import java.util.HashMap;
import java.util.Map;

public class ExerciseFilterDialog extends DialogFragment {

  private ExercisesFilter exercisesFilters;
  private Map<String, CustomCheckbox> checkboxMap;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.checkboxMap = new HashMap<>();
    this.exercisesFilters = getArguments().getParcelable("exerciseFilters");
    if (this.exercisesFilters == null) {
      this.exercisesFilters = new ExercisesFilter("", "");
    }
  }

  private void setFiltersOnUi() {
    for (String type : this.exercisesFilters.types) {
      switch (type) {
        case ExerciseTypes.WEIGHTS:
          this.checkboxMap.get(ExerciseTypes.WEIGHTS).setChecked(true);
          break;
        case ExerciseTypes.CARDIO:
          this.checkboxMap.get(ExerciseTypes.CARDIO).setChecked(true);
          break;
        case ExerciseTypes.BODY_WEIGHT:
          this.checkboxMap.get(ExerciseTypes.BODY_WEIGHT).setChecked(true);
          break;
          case  ExerciseTypes.ALL:
            this.checkboxMap.get(ExerciseTypes.WEIGHTS).setChecked(true);
            this.checkboxMap.get(ExerciseTypes.CARDIO).setChecked(true);
            this.checkboxMap.get(ExerciseTypes.BODY_WEIGHT).setChecked(true);
            break;
      }
    }
  }

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    View rootView = inflater.inflate(R.layout.dialog_filter_exercises, null);

    this.checkboxMap.put(ExerciseTypes.BODY_WEIGHT, rootView.findViewById(R.id.body_weight_customcheckbox));
    this.checkboxMap.put(ExerciseTypes.WEIGHTS, rootView.findViewById(R.id.weights_customcheckbox));
    this.checkboxMap.put(ExerciseTypes.CARDIO, rootView.findViewById(R.id.cardio_customcheckbox));

    this.checkboxMap.put(MuscleGroups.BACK, rootView.findViewById(R.id.back_mg_customcheckbox));
    this.checkboxMap.put(MuscleGroups.BICEPS, rootView.findViewById(R.id.biceps_mg_customcheckbox));
    this.checkboxMap.put(MuscleGroups.ABS, rootView.findViewById(R.id.abs_mg_customcheckbox));
    this.checkboxMap.put(MuscleGroups.TRICEPS, rootView.findViewById(R.id.triceps_mg_customcheckbox));
    this.checkboxMap.put(MuscleGroups.LEGS, rootView.findViewById(R.id.legs_mg_customcheckbox));

    this.setFiltersOnUi();

    builder.setView(rootView)
        .setPositiveButton("Awesome", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int id) {

          }
        })
        .setNegativeButton("Negative", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int id) {

          }
        });

    return builder.create();
  }
}
