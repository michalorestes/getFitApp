package com.jds.fitnessjunkiess.getfitapp.Activities.LoginActivity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.MainActivity;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents.DaggerUserViewModelFactoryComponent;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents.UserViewModelFactoryComponent;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerModules.UserViewModelFactoryModule;
import com.jds.fitnessjunkiess.getfitapp.Entities.User;
import com.jds.fitnessjunkiess.getfitapp.R;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.Factories.UserViewModelFactory;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.UserViewModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

  private GoogleSignInClient mGoogleSignInClient;
  private static final int RC_CODE = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    GoogleSignInOptions gso =
        new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

    mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    SignInButton signInButton = findViewById(R.id.sign_in_button);
    signInButton.setSize(SignInButton.SIZE_WIDE);
    signInButton.setOnClickListener(this);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
    if (requestCode == RC_CODE) {
      Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
      handleSignInResult(task);
    }
  }

  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.sign_in_button) {
      signIn();
    }
  }

  public void signIn() {
    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
    startActivityForResult(signInIntent, RC_CODE);
  }

  private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
    try {
      GoogleSignInAccount account = completedTask.getResult(ApiException.class);
      startWorkoutsActivity(account);
    } catch (ApiException e) {
      Log.w("***", "signInResult:failed code=" + e.getStatusCode());
    }
  }

  private void startWorkoutsActivity(GoogleSignInAccount account) {
    UserViewModelFactoryComponent userViewModelFactoryComponent =
        DaggerUserViewModelFactoryComponent.builder()
            .userViewModelFactoryModule(new UserViewModelFactoryModule())
            .build();

    UserViewModelFactory userViewModelFactory =
        userViewModelFactoryComponent.provideUserViewModelFactory();

    UserViewModel userViewModel =
        ViewModelProviders.of(this, userViewModelFactory).get(UserViewModel.class);

    User user = new User();
    user.setEmail(account.getEmail());
    user.setUsername(account.getDisplayName());

    userViewModel
        .addUser(user)
        .observe(
            this,
            u -> {
              if (u != null) {
                Log.i("***", "Got the user: " + u.getEmail() + " " + u.getUsername());
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("userData", u);
                startActivity(intent);
              }
            });
  }
}
