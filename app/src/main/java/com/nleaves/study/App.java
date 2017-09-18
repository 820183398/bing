package com.nleaves.study;

import android.app.Application;
import android.content.Context;

import com.nleaves.study.helper.UserHelper;


/**
 * Created by cgine on 16/3/22.
 */
public class App extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
        UserHelper.getInstance().init(this);
    }
}
