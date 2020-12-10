package com.rey.OMDB_API.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rey.OMDB_API.R;

public class MessageHelper {

    public enum IconType {
        SUCCESS,
        WARNING,
        ERROR
    }

    public static void Warningmsg(Context c, String strMensaje) {
        Mensaje(c, strMensaje, IconType.WARNING);
    }

    public static void Errorgmsg(Context c, String strMensaje) {
        Mensaje(c, strMensaje, IconType.ERROR);
    }

    public static void Successmsg(Context c, String strMensaje)
    {
        Mensaje(c,strMensaje,IconType.SUCCESS);
    }

    private static void Mensaje(Context c, String strMensaje, IconType iconType){


        LayoutInflater inflater = ((Activity)c).getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_tuneado,(ViewGroup)((Activity)c).findViewById(R.id.toast_tuneado_layout));

        TextView text = layout.findViewById(R.id.tvMensaje);
        ImageView imgIcono = layout.findViewById(R.id.ivIcono);

        switch (iconType)
        {
            case SUCCESS:
                imgIcono.setImageResource(R.drawable.ic_done);
                break;
            case WARNING:
                imgIcono.setImageResource(R.drawable.ic_warning);
                break;
            case ERROR:
                imgIcono.setImageResource(R.drawable.ic_error);
                break;
        }

        text.setText(strMensaje);
        Toast toast = new Toast(c);
        toast.setGravity(Gravity.CENTER_VERTICAL,0,350);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
