package com.jds.fitnessjunkiess.getfitapp.Activities.Initialisation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.MainActivity;
import com.jds.fitnessjunkiess.getfitapp.Data.Entities.User;
import com.jds.fitnessjunkiess.getfitapp.R;

public class InitialisationActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_initialisation);

    //TODO: Dev code to avoid fetching data from DB
    User u = new User();
    u.setId(7);
    u.setEmail("michalorestes@gmail.com");
    u.setUsername("MichalOrestes");
    u.setPassword("noPass");
    Intent workoutsActivity = new Intent(this, MainActivity.class);
    workoutsActivity.putExtra("userData", u);
    startActivity(workoutsActivity);
    finish();
    //Currently unused google sign in
//    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//    if (account != null) {
//      startWorkoutsActivity(account);
//    } else {
//      startLoginActivity();
//    }
  }

//  private void startLoginActivity() {
//    Intent loginActivity = new Intent(this, LoginActivity.class);
//    startActivity(loginActivity);
//    finish();
//  }
}
