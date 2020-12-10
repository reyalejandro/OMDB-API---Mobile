package com.rey.OMDB_API.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class VirtualKeyboardHelper {
    public static void hideKeyboard(Context c, View v)
    {
        InputMethodManager imm = (InputMethodManager) c.getSystemService(c.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(),0);
    }
}
