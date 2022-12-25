package com.example.fragmentmanager;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class current_task extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public current_task() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static current_task newInstance(String param1, String param2) {
        current_task fragment = new current_task();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_current_task, container, false);
        EditText currentTask = v.findViewById(R.id.currentTaskField);
        EditText newTask = v.findViewById(R.id.newTask);
        Button addButton = v.findViewById(R.id.addTask);
        String nameOfFileNew = "newtasks";
        File file3 = new File(nameOfFileNew+"txt");
        try {
            file3.createNewFile();
        }
        catch (IOException i){}

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTask.append(newTask.getText().toString());

                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(getActivity().openFileOutput(nameOfFileNew+".txt", Context.MODE_PRIVATE)));
                    bufferedWriter.append(currentTask.getText().toString()+"\n");
                    bufferedWriter.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(getActivity().openFileInput(nameOfFileNew+".txt")));
                    String str = "";
                    // читаем содержимое
                    while ((str = br.readLine()) != null) {
                        currentTask.append(str+"\n");
                    }


                }
                catch (IOException e){}

            }
        });

        return v;
    }
}