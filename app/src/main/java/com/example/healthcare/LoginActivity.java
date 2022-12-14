package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.healthcare.databinding.ActivityLoginBinding;
import com.example.healthcare.sharedpreferences.DbHelper;
import com.example.healthcare.sharedpreferences.SessionManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
  DbHelper DbHelper;
  private ActivityLoginBinding binding;
  private SessionManager session;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    super.onCreate(savedInstanceState);
    binding = ActivityLoginBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    session = new SessionManager(getApplicationContext());
    if (session.isLoggedIn()) {
      Intent intent = new Intent(LoginActivity.this, NewsActivity.class);
      startActivity(intent);
      finish();
    }
    session = new SessionManager(getApplicationContext());
    DbHelper = new DbHelper(this);
    SQLiteDatabase sqLiteDatabase = DbHelper.getWritableDatabase();
    binding.Signinbuttonid.setOnClickListener(this);
    binding.SignUpHerebuttonid.setOnClickListener(this);
  }
  @Override
  public void onClick(View v) {
    String username = binding.signinusernameEditText.getText().toString();
    String password = binding.signinpasswordEditText.getText().toString();
    if (v.getId() == R.id.Signinbuttonid) {
      Boolean result = DbHelper.findPassword(username, password);
      if (result == true) {
        Intent intent = new Intent(LoginActivity.this, NewsActivity.class);
        startActivity(intent);
        session.setLogin(true);
        finish();
      } else {
        Toast.makeText(this, "username and password didn`t match", Toast.LENGTH_SHORT).show();
      }
    } else if (v.getId() == R.id.SignUpHerebuttonid) {
      Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
      startActivity(intent);
    }
  }
}