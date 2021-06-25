package com.paneerbuttermasala.studios.smartinfantcradle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Memories extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    private static final String HI ="https://uniqueandrocode.000webhostapp.com/hiren/androidweb.php" ;
    private ArrayList<List_data> list_data;
    private GridView gridView;
    MyAdapterMemories adapter;
    private FloatingActionButton refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memories);
        gridView=(GridView)findViewById(R.id.gridView);
        list_data=new ArrayList<>();
        refresh = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear array list of all previous entries
                list_data.clear();
                // adapter=new MyAdapter(getApplicationContext(),list_data);
                getData();
            }
        });


        // adapter=new MyAdapter(getApplicationContext(),list_data);
        getData();

        //Initialize the bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        //Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.memories);

        //Perform item selected listner
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.music:
                        startActivity(new Intent(getApplicationContext(),Music.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.parenting:
                        startActivity(new Intent(getApplicationContext(),Parenting.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.control:
                        startActivity(new Intent(getApplicationContext(),Control.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Homepage.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }


    private void getData() {
        ProgressBar progressBar;
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, HI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.VISIBLE);
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("data");
                    for (int i=0; i<array.length(); i++){
                        JSONObject ob=array.getJSONObject(i);
                        List_data listData=new List_data(ob.getString("imageurl"));
                        list_data.add(listData);
                    }
                    adapter=new MyAdapterMemories(getApplicationContext(),R.layout.memories_grid_list,list_data);
                    progressBar.setVisibility(View.GONE);
                    gridView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
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
}