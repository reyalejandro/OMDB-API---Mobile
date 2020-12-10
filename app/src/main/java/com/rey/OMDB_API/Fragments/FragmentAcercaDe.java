package com.rey.OMDB_API.Fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rey.OMDB_API.R;
import com.rey.OMDB_API.baseclasses.FragmentBase;
import com.rey.OMDB_API.utils.LogHelper;


public class FragmentAcercaDe extends FragmentBase {
    View layoutRoot = null;
    public FragmentAcercaDe() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(layoutRoot == null)
        {
            layoutRoot = inflater.inflate(R.layout.fragment_acerca_de,container,false);
        }
        setActivityTitle("Acerca De");
        LogHelper.d("FragentAcercaDe -> onCreateView");
        return layoutRoot;
    }


}
