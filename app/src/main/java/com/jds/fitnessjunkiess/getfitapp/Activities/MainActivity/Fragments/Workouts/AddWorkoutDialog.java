package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Workouts;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.jds.fitnessjunkiess.getfitapp.R;
import java.util.Objects;

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
    Objects.requireNonNull(
        getDialog().getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)
    );

    return inflater.inflate(R.layout.fragment_dialog_add_workout, container, false);
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    Dialog dialog = new Dialog(getContext(),R.style.AppTheme);
    dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.MATCH_PARENT);

    return dialog;
  }
}
