package com.jds.fitnessjunkiess.getfitapp.Dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.MainActivity;
import com.jds.fitnessjunkiess.getfitapp.CustomViews.CustomCheckbox;
import com.jds.fitnessjunkiess.getfitapp.CustomViews.Selector.SelectorGroup;
import com.jds.fitnessjunkiess.getfitapp.Data.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;

public class WorkoutDetailsDialog extends DialogFragment {

  public interface ActionsInterface {
    void onSaveAction(Workout result);
  }

  private SelectorGroup selectorGroup;
  private EditText workoutName;
  private CustomCheckbox cbMon;
  private CustomCheckbox cbTue;
  private CustomCheckbox cbWed;
  private CustomCheckbox cbThu;
  private CustomCheckbox cbFri;
  private CustomCheckbox cbSat;
  private CustomCheckbox cbSun;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    View rootView = inflater.inflate(R.layout.fragment_dialog_add_workout, container, false);

    Toolbar toolbar = rootView.findViewById(R.id.toolbar);
    toolbar.setTitle("Create Workout");

    toolbar.setNavigationIcon(R.drawable.baseline_close_white_24dp);
    toolbar.inflateMenu(R.menu.dialog_workout_details_menu);
    toolbar.setNavigationOnClickListener(v -> dismiss());

    toolbar.setOnMenuItemClickListener(item -> {
      if (item.getItemId() == R.id.save) {
        saveWorkout();
      }
      return true;
    });

    setHasOptionsMenu(true);

    this.selectorGroup = rootView.findViewById(R.id.selectorGroup);
    this.workoutName = rootView.findViewById(R.id.workout_name);
    this.cbMon = rootView.findViewById(R.id.checkbox_mon);
    this.cbTue = rootView.findViewById(R.id.checkbox_tue);
    this.cbWed = rootView.findViewById(R.id.checkbox_wed);
    this.cbThu = rootView.findViewById(R.id.checkbox_thu);
    this.cbFri = rootView.findViewById(R.id.checkbox_fri);
    this.cbSat = rootView.findViewById(R.id.checkbox_sat);
    this.cbSun = rootView.findViewById(R.id.checkbox_sun);

    return rootView;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    Dialog dialog = new Dialog(getContext(),R.style.AppTheme);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

    return dialog;
  }

  private void saveWorkout() {
    ActionsInterface context2 = (ActionsInterface) getParentFragment();
    context2.onSaveAction(this.getResults());
    this.dismiss();
  }

  public Workout getResults() {
    Workout workout = new Workout();
    workout.setName(this.workoutName.getText().toString());
    workout.setType(this.selectorGroup.getSelectedValue());
    String schedule = "";

    if (this.cbMon.isChecked()) {
      schedule += "Monday";
    }

    if (this.cbTue.isChecked()) {
      schedule += ",Tuesday";
    }

    if (this.cbWed.isChecked()) {
      schedule += ",Wednesday";
    }

    if (this.cbThu.isChecked()) {
      schedule += ",Thursday";
    }

    if (this.cbFri.isChecked()) {
      schedule += ",Friday";
    }

    if (this.cbSat.isChecked()) {
      schedule += ",Saturday";
    }

    if (this.cbSun.isChecked()) {
      schedule += ",Sunday";
    }

    workout.setSchedule(schedule);
    workout.setUserId(MainActivity.user.getId());

    return workout;
  }
}
