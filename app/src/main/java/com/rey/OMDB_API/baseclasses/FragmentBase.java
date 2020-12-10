package com.rey.OMDB_API.baseclasses;

import androidx.fragment.app.Fragment;

import com.rey.OMDB_API.MainActivity;

public class FragmentBase extends Fragment {

    public MainActivity getContainerActivity()
    {
        return (MainActivity)getActivity();
    }

    public void setActivityTitle(String strTitle)
    {
        getContainerActivity().setTitle(strTitle.toUpperCase());
    }



   /* public void goBack()
    {
        if (this.getSupportFragmentManager().getBackStackEntryCount() > 0)
        {
            this.getSupportFragmentManager().popBackStackImmediate();
        } else
        {
            finish();
        }
    }*/
}
