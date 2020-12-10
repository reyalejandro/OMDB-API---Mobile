package com.rey.OMDB_API.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rey.OMDB_API.Entidades_Managers.ClasePeliculas;
import com.rey.OMDB_API.R;
import com.rey.OMDB_API.adapter.PeliculasArrayAdapter;
import com.rey.OMDB_API.baseclasses.FragmentBase;
import com.rey.OMDB_API.utils.LogHelper;
import com.rey.OMDB_API.utils.MessageHelper;
import com.rey.OMDB_API.utils.StreamHelper;
import com.rey.OMDB_API.utils.VirtualKeyboardHelper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class FragmentListado extends FragmentBase {
    private static final long DURATION_TRANSITION = 1000;
    View layoutRoot = null;
     EditText ettitulo;
     Button btnbuscar;
     ListView lvpelis;
    public FragmentListado() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (layoutRoot == null)
        {
            layoutRoot = inflater.inflate(R.layout.fragment_listado, container, false);
            ObtenerReferencias();
            SetearListeners();
        }
        setActivityTitle("Listado");
        LogHelper.d("FragmentList -> onCreateView");
        return layoutRoot;
    }

    private void SetearListeners() {
        btnbuscar.setOnClickListener(btnBuscar_Click);
    }

    private void ObtenerReferencias() {
        btnbuscar = (Button) layoutRoot.findViewById(R.id.btnBuscar);
        ettitulo = (EditText) layoutRoot.findViewById(R.id.etNombreingresa);
        lvpelis = (ListView) layoutRoot.findViewById(R.id.lvPeliculas);
    }

    View.OnClickListener btnBuscar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Context miContext;
            TodasPelisTask miTarea;
            String strCriteriosBusqueda;

            strCriteriosBusqueda = ettitulo.getText().toString();
            if (!strCriteriosBusqueda.isEmpty())
            {
                miContext = getContainerActivity();
                miTarea = new TodasPelisTask(miContext);
                miTarea.doSearchMovies(strCriteriosBusqueda);
            }
            else
            {
                MessageHelper.Warningmsg(getContainerActivity(),"Por favor Ingresar una película");
            }

            VirtualKeyboardHelper.hideKeyboard(getContainerActivity(),btnbuscar);
        }
    };



    class TodasPelisTask extends AsyncTask<Void, String, String> {
        private ProgressDialog dialog;
        private Context context;
        private String strURL;

        public TodasPelisTask(Context context)
        {
            this.context = context;
            dialog = new ProgressDialog(context);
        }

        public void setURL(String strURL) {
            this.strURL = strURL;
        }

        public void doSearchMovies(String strSearchCriterio)
        {
            String strFinalURL;
            strFinalURL = "http://www.omdbapi.com/?apikey=44163530&s=" + strSearchCriterio;
            setURL(strFinalURL);
            execute();
        }

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Espere unos segundos");
            this.dialog.show();
        }

        @Override
        protected String doInBackground(Void... parametros) {
            HttpURLConnection miConexion = null;
            URL strAPIUrl;
            String strResultado= "";

            try {
                Thread.sleep(250);
                publishProgress("Conectando");

                strAPIUrl = new URL(this.strURL);
                miConexion = (HttpURLConnection) strAPIUrl.openConnection();
                miConexion.setRequestMethod("GET");
                if (miConexion.getResponseCode() == 200) {
                    Thread.sleep(250);
                    publishProgress("Leyendo la respuesta");
                    strResultado = StreamHelper.GetFullStringFromInputReader(miConexion.getInputStream());
                } else {
                }
                Thread.sleep(450);
                publishProgress("Procesando...");
            } catch (Exception e) {
                LogHelper.d("Al conectar/procesar ocurre el error " + e.getMessage());
            } finally {
                if (miConexion != null) {
                    miConexion.disconnect();
                }
            }

            return strResultado;
        }

        @Override
        protected void onPostExecute(String resultado) {
            ArrayList<ClasePeliculas> datosArrayList;
            super.onPostExecute(resultado);

            if(dialog.isShowing())
            {
                dialog.dismiss();
            }
            datosArrayList = ParsearResultado(resultado);

            PeliculasArrayAdapter adapter;
            adapter = new PeliculasArrayAdapter(getContainerActivity(), R.layout.listview_activity,datosArrayList);
            lvpelis.setAdapter(adapter);
            setActivityTitle("Listado ("+ datosArrayList.size() +")");

            if (datosArrayList.size()==0)
            {
                MessageHelper.Warningmsg(getContainerActivity(),"No se encontraron películas");
            } else
            {
                MessageHelper.Successmsg(getContainerActivity(),"Se han encontrado " + datosArrayList.size() + " peliculas!");
            }

        }

        protected void OnProgressUpdate(String... progress)
        {
            this.dialog.setMessage(progress[0].toString());
        }

        private ArrayList<ClasePeliculas> ParsearResultado (String resultado) {
            ArrayList<ClasePeliculas> returnList;
            ClasePeliculas currentPelicula;

            JsonObject objJSONRespuesta, objJSONPelicula;
            JsonArray searchJSONArray;

            int  intTotalResults;
            String strNombrePeli,Id,tipo,imagen,AñoPeli;
            ClasePeliculas pelicula;
            boolean blnResponse;

            returnList = new ArrayList<ClasePeliculas>();

            objJSONRespuesta = JsonParser.parseString(resultado).getAsJsonObject();

            searchJSONArray = objJSONRespuesta.getAsJsonArray("Search");
            blnResponse = objJSONRespuesta.get("Response").getAsBoolean();

            if (blnResponse) {
                intTotalResults = objJSONRespuesta.get("totalResults").getAsInt();
                for (int i = 0; i < searchJSONArray.size(); i++) {
                    objJSONPelicula = searchJSONArray.get(i).getAsJsonObject();
                    strNombrePeli = objJSONPelicula.get("Title").getAsString();
                    AñoPeli = objJSONPelicula.get("Year").getAsString();
                    Id = objJSONPelicula.get("imdbID").getAsString();
                    /*tipo = objJSONPelicula.get("Type").getAsString();
                    imagen = objJSONPelicula.get("Poster").getAsString();*/
                    pelicula = new ClasePeliculas(Id,AñoPeli, strNombrePeli);
                    returnList.add(pelicula);
                }
            }
            return returnList;
        }
    }
}




