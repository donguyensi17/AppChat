package com.example.demochat.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.example.demochat.data.TakeData;
import com.example.demochat.home.adapter.AdapterMessage;
import com.example.demochat.R;
import com.example.demochat.home.view.UpdateData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  UpdateData{

    private RecyclerView mRecyclerView;
    private AdapterMessage mMessAdapter;
    private List<String> data;

    private Button btnSent;
    private EditText edtInput;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        new TakeData(this).execute();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.RecyslerView);
        btnSent = findViewById(R.id.Button);
        edtInput = findViewById(R.id.Edittex);

        data = new ArrayList<>();

        mMessAdapter = new AdapterMessage(data);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mMessAdapter);

    }

    @Override
    public void updateUi(String mess) {
        data.add(mess);
        mMessAdapter.notifyDataSetChanged();
    }
}
