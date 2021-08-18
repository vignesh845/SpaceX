package com.example.spacex;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final List<CrewModel> crewModels = new ArrayList<>();
    private final CrewListAdapter adapter = new CrewListAdapter();
    private AppDatabase appDatabase;
    private RetrofitService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView crewRecyclerView = findViewById(R.id.recycler_view_crew);
        crewRecyclerView.setAdapter(adapter);
        createDatabase();
        initRetrofit();
        checkDatabase();
    }

    private void createDatabase() {
        appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class, "crew"
        ).build();
    }

    private void initRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spacexdata.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        service = retrofit.create(RetrofitService.class);
    }

    private void checkDatabase() {
        Thread thread = new Thread(() -> {
            Looper.prepare();
            crewModels.addAll(appDatabase.crewDao().getAll());
            if (crewModels.isEmpty()) {
                fetchCrew();
            } else {
                adapter.setCrewModels(crewModels);
            }
        });
        thread.start();
    }

    private void fetchCrew() {
        Call<List<CrewModel>> call = service.getCrew();
        call.enqueue(new Callback<List<CrewModel>>() {
            @Override
            public void onResponse(Call<List<CrewModel>> call, Response<List<CrewModel>> response) {
                Thread thread = new Thread(() -> {
                    Looper.prepare();
                    appDatabase.crewDao().deleteAll();
                    if (response.body() != null) {
                        crewModels.clear();
                        crewModels.addAll(response.body());
                        appDatabase.crewDao().insertAll(crewModels);
                        runOnUiThread(() -> adapter.setCrewModels(crewModels));
                    }
                });
                thread.start();
            }


            @Override
            public void onFailure(Call<List<CrewModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_crew, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        fetchCrew();
        return true;
    }
}