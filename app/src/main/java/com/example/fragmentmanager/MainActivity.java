package com.example.fragmentmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {
    Button toFinishTask;
    Button toCurrentTask;
    Button toAgainTask;
    Fragment fragment2;
    FragmentManager fm;
    Fragment fragment;
    Fragment fragment3;
    Fragment fragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.container_fragm);
        if (fragment == null){
            fragment = new current_task();
            fm.beginTransaction().add(R.id.container_fragm, fragment).commit();
        }



        toCurrentTask = findViewById(R.id.currentTask);
        toFinishTask = findViewById(R.id.finishTask);
        toAgainTask = findViewById(R.id.againTask);


        toFinishTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragment2 == null){
                    fragment2 = new FinishTaskFragment();

                }
                FragmentTransaction ft =  fm.beginTransaction();
                ft.replace(R.id.container_fragm,fragment2);
                ft.commit();
            }
        });
        toCurrentTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragment4 == null){
                    fragment4 = new current_task();
                }
                FragmentTransaction f = fm.beginTransaction();
                f.replace(R.id.container_fragm,fragment4);
                f.commit();
            }
        });
        toAgainTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragment3 == null){
                    fragment3 = new BlankFragment();
                }
                FragmentTransaction r = fm.beginTransaction();
                r.replace(R.id.container_fragm,fragment3).commit();
            }
        });
        
    }

}