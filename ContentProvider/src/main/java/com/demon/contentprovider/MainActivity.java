package com.demon.contentprovider;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import permissions.dispatcher.RuntimePermissions;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

}
