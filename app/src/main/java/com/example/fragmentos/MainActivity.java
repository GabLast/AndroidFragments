package com.example.fragmentos;

import android.content.res.Configuration;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int orientation = getResources().getConfiguration().orientation;
        int cols;

        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_main_dual);
            cols =1;
        }else {
            setContentView(R.layout.activity_main);
            cols=2;
        }


        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentParent, ItemFragment.newInstance(cols))
                .commit();
    }
}