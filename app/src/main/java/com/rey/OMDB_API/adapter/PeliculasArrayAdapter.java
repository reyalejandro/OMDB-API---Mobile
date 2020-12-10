package com.rey.OMDB_API.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.rey.OMDB_API.Entidades_Managers.ClasePeliculas;
import com.rey.OMDB_API.MainActivity;
import com.rey.OMDB_API.R;
import com.rey.OMDB_API.utils.MessageHelper;

import java.util.ArrayList;

public class PeliculasArrayAdapter extends ArrayAdapter<ClasePeliculas> {
    private ArrayList<ClasePeliculas> peliculasList;
    private int resourceLayout;
    private Context context;

    public PeliculasArrayAdapter(Context context,int resource,ArrayList<ClasePeliculas> datosArray)
    {
        super(context,resource,datosArray);
        this.context = context;
        this.resourceLayout = resource;
        this.peliculasList = datosArray;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Boolean blnPeliculaExiste;
        ClasePeliculas dato;
        TextView tvaño;
        TextView tvtitulo;
        CheckBox chkFav;

        View layoutInterno = convertView;

        if (layoutInterno == null)
        {
            LayoutInflater li;
            li = LayoutInflater.from(this.context);
            layoutInterno = li.inflate(resourceLayout,null);
        }
        dato = getItem(position);

        if (dato!=null)
        {
            tvaño = (TextView) layoutInterno.findViewById(R.id.tvAño);
            tvtitulo = (TextView) layoutInterno.findViewById(R.id.tvTitulo);
            chkFav = (CheckBox) layoutInterno.findViewById(R.id.cbFav);

            tvaño.setText(String.valueOf(dato.getAño()));
            tvtitulo.setText(dato.getNombre());
            chkFav.setTag(dato);
            blnPeliculaExiste = getContainerActivity().ExistePelicula(dato);
            if (blnPeliculaExiste)
            {
                chkFav.setChecked(blnPeliculaExiste);
            }

    /*    int anio = Integer.parseInt(dato.getAño());                                   No funciona :(

            if (anio  < 2000) {
                tvaño.setBackgroundColor(Color.parseColor("#ffb114"));
            } else if(anio >= 2000 && anio < 2010)
            {
                tvaño.setBackgroundColor(Color.parseColor("#26d7ff"));
            } else
            {
                tvaño.setBackgroundColor(Color.parseColor("#46db46"));
            }*/


            chkFav.setOnClickListener(chkFav_Click);


        }
        return layoutInterno;

    }

    private View.OnClickListener chkFav_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CheckBox currentCB =(CheckBox)v;
            ClasePeliculas dato = (ClasePeliculas) currentCB.getTag();
            Boolean blnExistePelicula;

            blnExistePelicula = getContainerActivity().ExistePelicula(dato);

            if (currentCB.isChecked())
            {
                getContainerActivity().AgregarPeliculaFav(dato);
                MessageHelper.Successmsg(context,"Se agregó " + dato.getNombre());
            } else
            {
                getContainerActivity().RemoverPeliculaFav(dato);
                MessageHelper.Successmsg(context,"Se removió " + dato.getNombre());
            }
        }
    };

    public MainActivity getContainerActivity()
    {
        return (MainActivity) context;
    }

}
