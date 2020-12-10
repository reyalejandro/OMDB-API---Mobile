package com.rey.OMDB_API.Fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rey.OMDB_API.Entidades_Managers.ClasePeliculas;
import com.rey.OMDB_API.R;
import com.rey.OMDB_API.adapter.PeliculasArrayAdapter;
import com.rey.OMDB_API.baseclasses.FragmentBase;
import com.rey.OMDB_API.utils.MessageHelper;

import java.util.ArrayList;


public class FragmentFavoritos extends FragmentBase {
    ListView lvFav;
    View layoutRoot;

    public FragmentFavoritos() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (layoutRoot == null) {
            layoutRoot = inflater.inflate(R.layout.fragment_favoritos, container, false);
            ObtenerReferencias();
        }
        MostrarPelisFav();
        //setActivityTitle("Favoritos");
        return layoutRoot;
    }

    private void ObtenerReferencias() {
        lvFav = (ListView) layoutRoot.findViewById(R.id.lvFavoritos);
    }

    AdapterView.OnItemClickListener lvFav_Click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    };

    private void MostrarPelisFav() {
        ArrayList<ClasePeliculas> datosArrayList;
        datosArrayList = getContainerActivity().ObtenerPeliculaFavorita();
        PeliculasArrayAdapter adapter;
        adapter = new PeliculasArrayAdapter(getContainerActivity(),R.layout.listview_activity,datosArrayList);
        lvFav.setAdapter(adapter);
        setActivityTitle("Favoritos");

        if (datosArrayList.size() > 0)
        {
            setActivityTitle("Favoritos ("+ datosArrayList.size() + ")");
        }
        if(datosArrayList.size() == 0)
        {
            MessageHelper.Warningmsg(getContainerActivity(),"No hay peliculas favoriras");
        } else {
            MessageHelper.Successmsg(getContainerActivity(),"Hay " + datosArrayList.size() + " peliculas favoritas");
        }
    }
}