package com.nleaves.study.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;


/**
 */
public abstract class BaseFragment extends Fragment {

    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mContext = getActivity();
        super.onCreate(savedInstanceState);
    }

}

