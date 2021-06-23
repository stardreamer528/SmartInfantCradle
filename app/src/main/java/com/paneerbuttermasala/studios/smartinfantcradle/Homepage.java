package com.paneerbuttermasala.studios.smartinfantcradle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Homepage extends AppCompatActivity {

    public SeekBar sbar;
    public TextView tview;
    int currentProgress = 0, lastProgress=100 ;
    boolean doubleBackToExitPressedOnce = false;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Retrofit();
        Log.d("debug","Retrofit completed");


        //Initialize the bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        //Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Perform item selected listner
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.music:
                        startActivity(new Intent(getApplicationContext(), Music.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.parenting:
                        startActivity(new Intent(getApplicationContext(), Parenting.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.control:
                        startActivity(new Intent(getApplicationContext(), Control.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.memories:
                        startActivity(new Intent(getApplicationContext(), Memories.class));
                        overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    public void SeekBarMethod()
    {
        sbar = findViewById(R.id.seekBar);
        tview = findViewById(R.id.seekbarTextview);
        int test = Integer.parseInt(tview.getText().toString());
        System.out.println(test);
        sbar.setProgress(test);

        sbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentProgress = progress;
                tview.setText(String.valueOf(currentProgress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tview.setText(String.valueOf(currentProgress));

            }
        });
    }

    public void Retrofit()
    {

        //Retrofit code
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceholderAPI placeholderAPI = retrofit.create(PlaceholderAPI.class);
        Call<List<Placeholder>> call = placeholderAPI.getPosts();

        call.enqueue(new Callback<List<Placeholder>>() {


            @Override
            public void onResponse(Call<List<Placeholder>> call, Response<List<Placeholder>> response) {
                if (response.isSuccessful()) {
                    List<Placeholder> posts = response.body();
                    Log.d("debug", String.valueOf(posts.get(80).getId()));
                    TextView textView = findViewById(R.id.seekbarTextview);
                    textView.setText(String.valueOf(posts.get(80).getId()));
                    Log.d("debug","Text set to Textview");
                    SeekBarMethod();
                    Log.d("debug","Seekbar Method Completed");
                    return;

                } else {
                    Log.d("debug", "Boo!");
                    return;
                }
            }

            public void onFailure(Call<List<Placeholder>> call, Throwable t) {
                Log.d("debug", "Errror!");
                return;
            }
        });
    }
}
