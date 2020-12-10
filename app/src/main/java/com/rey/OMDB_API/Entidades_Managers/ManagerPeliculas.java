package com.rey.OMDB_API.Entidades_Managers;

public class ManagerPeliculas {
    private static ClasePeliculas pelicula = null;

    public static ClasePeliculas obtenerPelicula(){
        ClasePeliculas peli = null;

        if (pelicula != null){
            peli = pelicula;
        }

        return peli;
    }

    public static void AsignarDatosPelicula(ClasePeliculas peli){
        pelicula = peli;
    }
}

