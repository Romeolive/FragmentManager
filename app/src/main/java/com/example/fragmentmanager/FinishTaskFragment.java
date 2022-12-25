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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FinishTaskFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FinishTaskFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static FinishTaskFragment newInstance(String param1, String param2) {
        FinishTaskFragment fragment = new FinishTaskFragment();
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
    public String nameOfFileDone = "donetasks";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View s = inflater.inflate(R.layout.fragment_finish_task, container, false);
        EditText finishTask = s.findViewById(R.id.finishTaskField);
        EditText allDoneTasks = s.findViewById(R.id.allDoneTask);
        Button aDDButton = s.findViewById(R.id.doneTask);


        File file1 = new File(nameOfFileDone + ".txt");
        try {
            file1.createNewFile();
        } catch (IOException i) {
        }

        aDDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allDoneTasks.append(finishTask.getText().toString());
                try {
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(getActivity().openFileOutput(nameOfFileDone + ".txt", Context.MODE_PRIVATE)));
                    bw.append(allDoneTasks.getText().toString() + "\n");
                    bw.close();
                } catch (IOException e) {
                }
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(getActivity().openFileInput(nameOfFileDone+".txt")));
                    String str = "";
                    // читаем содержимое
                    while ((str = br.readLine()) != null) {
                        allDoneTasks.append(str+"\n");
                    }


                }
                catch (IOException e){}

            }
        });
        return s;


    }
}