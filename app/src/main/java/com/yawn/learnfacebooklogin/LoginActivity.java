package com.yawn.learnfacebooklogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {

  private LoginButton mUiLoginButton;
  private CallbackManager callbackManager;
  private String TAG = LoginActivity.class.getCanonicalName();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    FacebookSdk.sdkInitialize(getApplicationContext());
    callbackManager = CallbackManager.Factory.create();

    mUiLoginButton = (LoginButton) findViewById(R.id.login_button);
    mUiLoginButton.setReadPermissions("email");


    mUiLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
      @Override public void onSuccess(LoginResult loginResult) {
        Log.d(TAG, "onSuccess() called with: loginResult = [" + loginResult + "]");
      }

      @Override public void onCancel() {
        Log.d(TAG, "onCancel() called");
      }

      @Override public void onError(FacebookException error) {
        Log.d(TAG, "onError() called with: error = [" + error + "]");
      }
    });
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    callbackManager.onActivityResult(requestCode, resultCode, data);
  }
}
