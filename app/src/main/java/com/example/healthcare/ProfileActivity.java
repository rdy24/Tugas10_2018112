package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.healthcare.api.MovieActivity;
import com.example.healthcare.databinding.ActivityProfileBinding;
import com.example.healthcare.sharedpreferences.UserDetails;
import com.example.healthcare.sqllite.DisplayData;
import com.google.android.material.navigation.NavigationView;

public class ProfileActivity extends AppCompatActivity {
  private DrawerLayout drawer;
  private ActionBarDrawerToggle toggle;
  private ActivityProfileBinding binding;
  SharedPreferences preferences;
  SharedPreferences.Editor editor;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    super.onCreate(savedInstanceState);
    binding = ActivityProfileBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
    drawer.addDrawerListener(toggle);
    toggle.setDrawerIndicatorEnabled(true);
    drawer.addDrawerListener(toggle);
    toggle.syncState();
    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setCheckedItem(R.id.nav_consult);
    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
          case R.id.nav_alarm:
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);
            break;
          case R.id.nav_news:
            Intent intent1 = new Intent(ProfileActivity.this, NewsActivity.class);
            startActivity(intent1);
            break;
          case R.id.nav_consult:
            Intent intent2 = new Intent(ProfileActivity.this, ConsultantActivity.class);
            startActivity(intent2);
            break;
          case R.id.nav_profile:
            Intent intent3 = new Intent(ProfileActivity.this, ProfileActivity.class);
            startActivity(intent3);
            break;
          case R.id.nav_sql:
            Intent intent4 = new Intent(ProfileActivity.this, DisplayData.class);
            startActivity(intent4);
            break;
          case R.id.nav_movie:
            Intent intent5 = new Intent(ProfileActivity.this, MovieActivity.class);
            startActivity(intent5);
            break;
        }
        return true;
      }
    });
    preferences = getSharedPreferences("AndroidHiveLogin", 0);
    editor = preferences.edit();
    binding.buttonLogout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        editor.clear();
        editor.commit();
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
      }
    });
  }
}