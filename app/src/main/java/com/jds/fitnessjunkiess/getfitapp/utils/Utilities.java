package com.jds.fitnessjunkiess.getfitapp.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Utilities {

  public void hideKeyboardFrom(Context context, View view) {
    InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    view.clearFocus();
  }
}
