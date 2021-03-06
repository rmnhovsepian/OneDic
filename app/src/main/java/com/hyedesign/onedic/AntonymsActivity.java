package com.hyedesign.onedic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hyedesign.onedic.adapter.AntonymAdapter;
import com.hyedesign.onedic.db.AntonymDataSource;
import com.hyedesign.onedic.models.Antonym;

import java.util.List;

/**
 * Created by Albaloo on 2/17/2017.
 */

public class AntonymsActivity extends BaseMenuActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antonyms);

        initialize();

        models = dataSource.getAll();
/*        if(models.size() == 0){
            dataSource.seed();
            models = dataSource.getAll();
        }*/

        refreshDisplay();

    }

    private void initialize() {
        dataSource = new AntonymDataSource(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.antonym_recycler_view);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Antonyms");
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AntonymActivity.class));
            }
        });
    }

    @Override
    public void goToIntent() {
        Intent intent = new Intent(this,AntonymActivity.class);
        startActivity(intent);
    }

    @Override
    public void refreshDisplay() {
        List<Antonym> antonyms = (List<Antonym>) (List<?>)models;

        mAdapter = new AntonymAdapter(antonyms);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // DIVIDER
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        mRecyclerView.setAdapter(mAdapter);
    }
}
