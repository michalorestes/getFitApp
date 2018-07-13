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
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

    dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.MATCH_PARENT);

    return dialog;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
//    getDialog().getWindow().setSoftInputMode(
//        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    getDialog().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

  }

  @Override
  public void onStart() {
    super.onStart();
//    Dialog dialog = getDialog();
//    if (dialog != null) {
//      int width = ViewGroup.LayoutParams.MATCH_PARENT;
//      int height = ViewGroup.LayoutParams.MATCH_PARENT;
//      dialog.getWindow().setLayout(width, height);
//    }
  }
}
