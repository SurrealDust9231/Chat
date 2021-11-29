package com.example.chat.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chat.R;
import com.example.chat.activities.FiltersActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FiltersFragment extends Fragment {

    View mView;
    CardView mCardViewAndroid;
    CardView mCardViewPython;
    CardView mCardViewJavaScript;
    CardView mCardViewC;

    public FiltersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_filter, container, false);
        mCardViewAndroid = mView.findViewById(R.id.cardViewAndroid);
        mCardViewPython = mView.findViewById(R.id.cardViewPython);
        mCardViewJavaScript = mView.findViewById(R.id.cardViewJavaScript);
        mCardViewC = mView.findViewById(R.id.cardViewC);

        mCardViewAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFilterActivity("Java");
            }
        });

        mCardViewPython.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFilterActivity("Python");
            }
        });

        mCardViewJavaScript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFilterActivity("JavaScript");
            }
        });

        mCardViewC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFilterActivity("C");
            }
        });

        return mView;
    }

    private void goToFilterActivity(String category) {
        Intent intent = new Intent(getContext(), FiltersActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}