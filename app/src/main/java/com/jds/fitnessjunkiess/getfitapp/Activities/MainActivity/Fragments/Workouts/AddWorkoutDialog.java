package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Workouts;


import android.app.Dialog;
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
import com.jds.fitnessjunkiess.getfitapp.R;

public class AddWorkoutDialog extends DialogFragment {

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
      // handle confirmation button click here
      return true;
    } else if (id == android.R.id.home) {
      // handle close button click here
      dismiss();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
