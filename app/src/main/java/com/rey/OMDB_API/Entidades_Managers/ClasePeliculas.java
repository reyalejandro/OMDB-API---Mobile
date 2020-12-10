package com.rey.OMDB_API.Entidades_Managers;

public class ClasePeliculas {


    private String Id;
    private String Año;
    private String Nombre;
    private String Imagen;
    private String Descripcion; private String  Duracion;
    private String  Lenguaje;
    private String  Pais;
    private String Type;

    public ClasePeliculas(String id, String año, String nombre) {
        this.Id = id;
        this.Año = año;
        this.Nombre = nombre;
    }

    public ClasePeliculas(String id, String año, String nombre, String type, String imagen) {
        this.Id = id;
        this.Año = año;
        this.Nombre = nombre;
        this.Type = type;
        this.Imagen = imagen;
    }

    public ClasePeliculas(String id, String año, String nombre, String imagen, String descripcion, String duracion, String lenguaje, String pais) {
        this.Id = id;
        this.Año = año;
        this.Nombre = nombre;
        this.Imagen = imagen;
        this.Descripcion = descripcion;
        this.Duracion = duracion;
        this.Lenguaje = lenguaje;
        this.Pais = pais;
    }

    public String getId() {
        return this.Id;
    }

    public String getNombre() {
        return this.Nombre;
    }

    public String getAño() {
        return this.Año;
    }

    public String getType() {
        return this.Type;
    }

    public String getImagen() {
        return this.Imagen;
    }

    public String getDescripcion() {
        return this.Descripcion;
    }

    public String getDuracion() {
        return this.Duracion;
    }

    public String getLenguaje() {
        return this.Lenguaje;
    }

    public String getPais() {
        return this.Pais;
    }

    @Override
    public String toString()
    {
        return this.Año + " - " + this.Nombre;
    }
}
