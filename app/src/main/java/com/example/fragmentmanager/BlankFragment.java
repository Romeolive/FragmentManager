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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
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
        View r =  inflater.inflate(R.layout.fragment_blank, container, false);
        EditText againTextField = r.findViewById(R.id.againTextField);
        EditText againDoneField = r.findViewById(R.id.againDoneTask);
        Button againBtn = r.findViewById(R.id.againTask);

        String nameOfFileRepeat = "repeatasks";

        File file2 = new File(nameOfFileRepeat+"txt");

        try {
            file2.createNewFile();
        }
        catch (IOException i){}

        againBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                againDoneField.append(againTextField.getText().toString());

                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(getActivity().openFileOutput(nameOfFileRepeat+".txt", Context.MODE_PRIVATE)));
                    bufferedWriter.append(againDoneField.getText().toString()+"\n");
                    bufferedWriter.close();
                } catch (FileNotFoundException e) {} catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(getActivity().openFileInput(nameOfFileRepeat+".txt")));
                    String str = "";
                    // читаем содержимое
                    while ((str = br.readLine()) != null) {
                        againDoneField.append(str+"\n");
                    }


                }
                catch (IOException e){}
            }
        });
        return r;
    }
}