package com.example.harshitvaish.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static Retrofit retrofit = null;
    ListView Review;
    ArrayList bio=new ArrayList();

    RecyclerView recycleview;
ArrayList<String> art=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Review=findViewById(R.id.Review);
        recycleview=findViewById(R.id.recycleview);
        StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recycleview.setLayoutManager(manager);
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
           Api api=retrofit.create(Api.class);

        Call<List<Hero>> call=api.getHeroes();
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
               List<Hero> heroes=response.body();
               for(Hero h:heroes){
                 art.add(h.getName());
                 bio.add(h.getRealname());
               }
            Review.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_expandable_list_item_1,
                  art));
             //  recycleview.setAdapter(new RecyclerAdapter(heroes.get));
                Review.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(),bio.get(position)+"",Toast.LENGTH_SHORT).show();

                    }
                });
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
