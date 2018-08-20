package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Workouts.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.MainActivity;
import com.jds.fitnessjunkiess.getfitapp.CustomViews.CustomCheckbox;
import com.jds.fitnessjunkiess.getfitapp.CustomViews.Selector.SelectorGroup;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;

public class AddWorkoutDialog extends DialogFragment {

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

    ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

    ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setHomeButtonEnabled(true);
      actionBar.setHomeAsUpIndicator(R.drawable.baseline_close_white_36dp);
    }
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

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    menu.clear();
    getActivity().getMenuInflater().inflate(R.menu.workout_details_dialogue, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.save) {
      this.saveWorkout();
      return true;
    } else if (id == android.R.id.home) {
      // handle close button click here
      dismiss();
      return true;
    }

    return super.onOptionsItemSelected(item);
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

  //TODO: Needs to be moved somewhere else later
  public void hideKeyboardFrom(Context context, View view) {
    InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    view.clearFocus();
  }
}
