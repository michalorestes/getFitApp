package com.jds.fitnessjunkiess.getfitapp.Dialogs;

import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.jds.fitnessjunkiess.getfitapp.R;

public class ExerciseFilterDialog extends DialogFragment {

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    // Get the layout inflater
    LayoutInflater inflater = getActivity().getLayoutInflater();

    // Inflate and set the layout for the dialog
    // Pass null as the parent view because its going in the dialog layout
    builder.setView(inflater.inflate(R.layout.dialog_filter_exercises, null))
        // Add action buttons
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
