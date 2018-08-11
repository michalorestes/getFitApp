package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Exercises.ExercisesListFragment;
import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Workouts.Adapters.WorkoutListViewHolderInterface;
import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Workouts.AddWorkoutDialog;
import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Workouts.WorkoutsListFragment;
import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.profile.ProfileFragment;
import com.jds.fitnessjunkiess.getfitapp.Entities.User;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

  public static User user;
  private Button button;
  private TextView text;
  private JellyBeanViewModel jellyBean;
  private int value;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    button = findViewById(R.id.testBtn);
    text = findViewById(R.id.testTxt);
    jellyBean = ViewModelProviders.of(this).get(JellyBeanViewModel.class);

    button.setOnClickListener(this);


    Observer<Integer> observable = integer -> {
      text.setText(jellyBean.getAmount().getValue() + "");
    };

    jellyBean.getAmount().observe(this, observable);

  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    getSupportFragmentManager().popBackStackImmediate();
  }

  @Override
  public void onClick(View v) {
    value += 2;
    jellyBean.setAmount(value);
  }
}
