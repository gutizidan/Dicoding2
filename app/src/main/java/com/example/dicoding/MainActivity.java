package com.example.dicoding;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView rvSurah;
    private jadwalAdapter allLeaguesAdapter;
    private List<ModelJadwal> allLeagueList = new ArrayList<>();
    private ProgressDialog mProgress;
    SwipeRefreshLayout swipeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvSurah = findViewById(R.id.recycler_view );
//        swipeLayout = findViewById(R.id.swipe_container);

        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Processing...");
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);

        mProgress.show();
        fetchscheduleApi();



//        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                // Your code here
//                allLeagueList.clear();
//                fetchscheduleApi();
//                // To keep animation for 4 seconds
//                new Handler().postDelayed(new Runnable() {
//                    @Override public void run() {
//                        // Stop animation (This will be after 3 seconds)
//                        swipeLayout.setRefreshing(false);
//                    }
//                }, 1500);
//                Toast.makeText(getApplicationContext(), "Data is Up to date!", Toast.LENGTH_SHORT).show();// Delay in millis
//            }
//        });

        setupRecycler();
    }

    private void setupRecycler(){
        allLeaguesAdapter = new jadwalAdapter(this,  allLeagueList);
        rvSurah.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvSurah.setHasFixedSize(true);
        rvSurah.setAdapter(allLeaguesAdapter);
    }

    private void fetchscheduleApi() {
        AndroidNetworking.get(Constants.BASE_URL)
                .setTag("leagues")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray hasilList = response.getJSONArray("player");
                            for (int i = 0; i < hasilList.length(); i++) {
                                JSONObject hasil = hasilList.getJSONObject(i);
                                ModelJadwal item = new ModelJadwal();
                                item.setStrHomeTeam(hasil.getString("strPlayer"));
//                                item.setStrAwayTeam(hasil.getString("strAwayTeam"));
//                                item.setStrDate(hasil.getString("strDate"));
//                                item.setStrTime(hasil.getString("strTime"));
//                                item.setIntAwayScore(hasil.getString("intAwayScore"));
//                                item.setIntHomeScore(hasil.getString("intHomeScore"));
                                item.setStrEvent(hasil.getString("strPlayer"));
                                item.setStrHomeYellowCards(hasil.getString("dateBorn"));
                                item.setStrAwayYellowCards(hasil.getString("dateSigned"));
                                item.setStrHomeLineupGoalkeeper(hasil.getString("strBirthLocation"));
//                                item.setStrAwayLineupGoalkeeper(hasil.getString("strDescriptionEN"));
                                item.setStrAwayLineupMidfield(hasil.getString("strDescriptionEN"));
//                                item.setStrHomeLineupMidfield(hasil.getString("strHomeLineupMidfield"));
//                                item.setStrAwayLineupDefense(hasil.getString("strAwayLineupDefense"));
//                                item.setStrHomeLineupDefense(hasil.getString("strHomeLineupDefense"));

                                item.setStrThumb(hasil.getString("strThumb"));
                                item.setStrThumbs2(hasil.getString("strFanart4"));
                                allLeagueList.add(item);
                            }
                            mProgress.dismiss();
                            allLeaguesAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("", "onError: " + anError.getErrorBody());
                        Toast.makeText(getApplicationContext(), Constants.EROR, Toast.LENGTH_SHORT).show();
                    }
                });


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Membaca file menu dan menambahkan isinya ke action bar jika ada.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void onComposeAction(MenuItem mi) {
        Intent m =new Intent(getApplicationContext(), about_me.class);
        startActivity(m);    }
}

