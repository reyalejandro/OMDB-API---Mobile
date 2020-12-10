package com.rey.OMDB_API.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.rey.OMDB_API.MainActivity;
import com.rey.OMDB_API.R;
import com.rey.OMDB_API.baseclasses.FragmentBase;


public class FragmentMenu extends FragmentBase {
    View layoutRoot;
    ImageButton btnlista, btnfav, btnad, btnGeneral;
    //int[] iconColor = new int[] {255, 247, 23};
    public FragmentMenu() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (layoutRoot == null) {
            layoutRoot = inflater.inflate(R.layout.fragment_menu, container, false);
            ObtenerReferencias();
            SetearListeners();
        }

        return layoutRoot;
    }


    private void ObtenerReferencias() {
        btnlista = (ImageButton) layoutRoot.findViewById(R.id.btnListas);
        btnfav = (ImageButton) layoutRoot.findViewById(R.id.btnFavoritos);
        btnad = (ImageButton) layoutRoot.findViewById(R.id.btnAcercaDe);
       /* btnGeneral = (ImageButton) layoutRoot.findViewById(R.id.btnListas);
        btnGeneral = (ImageButton) layoutRoot.findViewById(R.id.btnFavoritos);
        btnGeneral = (ImageButton) layoutRoot.findViewById(R.id.btnAcercaDe);*/
    }

    private void SetearListeners() {
        btnad.setOnClickListener(btnAd_Click);
        btnfav.setOnClickListener(btnFav_Click);
        btnlista.setOnClickListener(btnLista_Click);
    }

    private View.OnClickListener btnLista_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MainActivity containerActivity;
            containerActivity = (MainActivity) getActivity();
            containerActivity.showPeliculas();
            SetIconColor(btnlista,255, 247, 23);
        }
    };


    private View.OnClickListener btnFav_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MainActivity containerActivity;
            containerActivity = (MainActivity) getActivity();
            containerActivity.showFavoritos();
            SetIconColor(btnfav,255, 247, 23);
        }
    };


    private View.OnClickListener btnAd_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MainActivity containerActivity;
            containerActivity = (MainActivity) getActivity();
            containerActivity.showAcercaDe();
            SetIconColor(btnad,255, 247, 23);
        }
    };


    private void SetIconColor(ImageButton btn,int r, int g, int b) {
        btnlista.clearColorFilter();
        btnfav.clearColorFilter();
        btnad.clearColorFilter();
        btn.setColorFilter(Color.rgb(r, g, b));
    }
}


