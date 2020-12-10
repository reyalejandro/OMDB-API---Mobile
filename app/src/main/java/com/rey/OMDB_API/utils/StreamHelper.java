package com.rey.OMDB_API.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamHelper {
    public static String GetFullStringFromInputReader(InputStream streamEntrada)
    {
        BufferedReader responseReader;
        String responseLine,strResultado = "";
        StringBuilder sbResponse;

        try {
            responseReader = new BufferedReader(new InputStreamReader(streamEntrada));
            sbResponse = new StringBuilder();
            while ((responseLine = responseReader.readLine()) !=null)
            {
                sbResponse.append(responseLine);
            }
            responseReader.close();
            strResultado = sbResponse.toString();
        } catch (Exception e)
        {

        }
        return strResultado;
    }
}
