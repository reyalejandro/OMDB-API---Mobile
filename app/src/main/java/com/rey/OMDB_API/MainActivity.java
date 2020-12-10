package com.rey.OMDB_API;

import android.os.Bundle;
import android.view.WindowManager;

import com.rey.OMDB_API.Entidades_Managers.ClasePeliculas;
import com.rey.OMDB_API.Fragments.FragmentAcercaDe;
import com.rey.OMDB_API.Fragments.FragmentFavoritos;
import com.rey.OMDB_API.Fragments.FragmentListado;
import com.rey.OMDB_API.baseclasses.BaseActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity{
    FragmentListado fragmentListado;
    FragmentFavoritos fragmentFavoritos;
    FragmentAcercaDe fragmentAcercaDe;
    ArrayList<ClasePeliculas> pelisfavList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createFragments();

        showInitialFragment();

        InitializeData();
    }


    private void createFragments() {
        fragmentListado = new FragmentListado();
        fragmentFavoritos = new FragmentFavoritos();
        fragmentAcercaDe = new FragmentAcercaDe();
    }
    // Codigo para mostrar en el fraContenedor el Fragment inicial
    private void showInitialFragment() {
        goToFragmentWithReplace(R.id.fraContenedor,fragmentListado,false);
    }

    private void InitializeData()
    {
        pelisfavList = new ArrayList<ClasePeliculas>();
    }

    public void showPeliculas(){
        FragmentAnimationIzqDer(fragmentListado,null);
        goToFragmentWithReplace(R.id.fraContenedor,fragmentListado,true);
    }

    public void showFavoritos(){
        FragmentAnimationDerIzq(fragmentFavoritos,null);
        goToFragmentWithReplace(R.id.fraContenedor,fragmentFavoritos,true);
    }

    public void showAcercaDe(){
        FragmentAnimationDerIzq(fragmentAcercaDe,null);
        goToFragmentWithReplace(R.id.fraContenedor,fragmentAcercaDe,true);
    }

    public void AgregarPeliculaFav(ClasePeliculas peliculas)
    {
        pelisfavList.add(peliculas);
    }

    public Boolean RemoverPeliculaFav(ClasePeliculas pelicula) {
        Boolean blnReturn = false;
        int intIndex;
        intIndex = indexOfPelicula(pelicula);
        if(intIndex >= 0)
        {
            pelisfavList.remove(pelicula);
            blnReturn = true;
        }
        return blnReturn;
    }

    public ArrayList<ClasePeliculas> ObtenerPeliculaFavorita()
    {
        return pelisfavList;
    }

    public Boolean ExistePelicula(ClasePeliculas peliABuscar)
    {
        Boolean blnReturn = false;
        int intIndex;
        intIndex = indexOfPelicula(peliABuscar);
        if (intIndex >= 0 )
        {
            blnReturn = true;
        }
        return blnReturn;
    }

    private int indexOfPelicula(ClasePeliculas pelicula)
    {
        int intReturn = -1;
        int intItemCount;
        int CurrentIndex = 0;

        if (pelicula != null)
        {
            intItemCount = pelisfavList.size();
            if (intItemCount > 0)
            {
                while (intReturn == -1 && CurrentIndex < intItemCount)
                {
                    if (pelicula.getId().equals(pelisfavList.get(CurrentIndex).getId()))
                    {
                        intReturn = CurrentIndex;
                    } else {
                        CurrentIndex++;
                    }
                }
            }
        }
        return intReturn;
    }

}