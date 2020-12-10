package com.rey.OMDB_API.baseclasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.rey.OMDB_API.R;

public class BaseActivity extends AppCompatActivity {
    public void goToFragmentWithAdd(int intContainerId, Fragment fragment, Boolean addBackStack)
    {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        String strTag;

        fragmentManager = this.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        if(addBackStack)
        {
            strTag = fragment.getClass().getName();
            fragmentTransaction.add(intContainerId,fragment,strTag);
            fragmentTransaction.addToBackStack(strTag);
        } else
        {
            fragmentTransaction.replace(intContainerId,fragment);
        }
        fragmentTransaction.commit();
    }

    public void goToFragmentWithReplace(int intContainerId, Fragment fragment, Boolean addBackStack)
    {
        FragmentManager fragManager;
        FragmentTransaction fragTransaction;
        String strTag;

        fragManager = this.getSupportFragmentManager();
        fragTransaction = fragManager.beginTransaction();

        if (addBackStack)
        {
            strTag = fragment.getClass().getName();
            fragTransaction.replace(intContainerId, fragment,strTag);
            fragTransaction.addToBackStack(strTag);
        } else{
            fragTransaction.replace(intContainerId,fragment);
        }
        fragTransaction.commit();
    }

    public void FragmentAnimationDerIzq(Fragment fragment, String tag){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.transicion_der_a_izq, R.anim.t_salir_der_a_izq);
        transaction.replace(R.id.fraContenedor, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    public void FragmentAnimationIzqDer(Fragment fragment, String tag)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.transicion_izq_a_der, R.anim.t_salir_izq_a_der);
        transaction.replace(R.id.fraContenedor, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    /*public void GoToFragmentMenu()
    {
        MainActivity containerActivity;
        containerActivity = (MainActivity) getActivity();
        containerActivity.showPeliculas();
    }*/
}
