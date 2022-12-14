package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.healthcare.databinding.ActivityRegisterBinding;
import com.example.healthcare.sharedpreferences.DbHelper;
import com.example.healthcare.sharedpreferences.UserDetails;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
  private ActivityRegisterBinding binding;
  UserDetails userDetails;
  DbHelper DbHelper;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //setup view binding
    binding = ActivityRegisterBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    DbHelper = new DbHelper(this);
    userDetails = new UserDetails();
    binding.SignUpbuttonid.setOnClickListener(this);
    binding.SigninHerebuttonid.setOnClickListener(this);
  }
  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.SignUpbuttonid) {
      String name = binding.signUpnameEditText.getText().toString();
      String email = binding.signiUpEmailEditText.getText().toString();
      String username = binding.signUpusernameEditText.getText().toString();
      String password = binding.signUppasswordEditText.getText().toString();
      //checking the validity of the Name
      if (name.isEmpty()) {
        binding.signUpnameEditText.setError("Enter an Name");
        binding.signUpnameEditText.requestFocus();
        return;
      }
      //checking the validity of the US
      if (username.isEmpty()) {
        binding.signUpusernameEditText.setError("Enter an Username");
        binding.signUpusernameEditText.requestFocus();
        return;
      }
      //checking the validity of the Email
      if (email.isEmpty()) {
        binding.signiUpEmailEditText.setError("Enter an Email Address");
        binding.signiUpEmailEditText.requestFocus();
        return;
      }
      if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        binding.signiUpEmailEditText.setError("Enter a Valid Email Address");
        binding.signiUpEmailEditText.requestFocus();
        return;
      }//checking the validity of the password
      if (password.isEmpty()) {
        binding.signUppasswordEditText.setError("Enter a Password");
        binding.signUppasswordEditText.requestFocus();
        return;
      }
      if (password.length() < 6) {
        binding.signUppasswordEditText.setError("Minimum Length of a Password Should be 6");
        binding.signUppasswordEditText.requestFocus();
        return;
      }
      userDetails.setName(name);
      userDetails.setEmail(email);
      userDetails.setUsername(username);
      userDetails.setPassword(password);
      long rowid = DbHelper.insertData(userDetails);
      Toast.makeText(getApplicationContext(), "Data is successfully inserted", Toast.LENGTH_SHORT).show();
      Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
      startActivity(intent);
    } else if (v.getId() == R.id.SigninHerebuttonid) {
      Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
      startActivity(intent);
    }
  }
}