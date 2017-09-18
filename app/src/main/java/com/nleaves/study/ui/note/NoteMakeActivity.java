package com.nleaves.study.ui.note;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.nleaves.study.R;
import com.nleaves.study.ui.base.BaseActivity;
import com.nleaves.study.ui.home.HomeCoachFragment;
import com.nleaves.study.ui.home.HomeDealFragment;
import com.nleaves.study.ui.home.HomeMeFragment;
import com.nleaves.study.ui.home.HomeNoteFragment;
import com.nleaves.study.views.TabControlView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteMakeActivity extends BaseActivity  {


    public static void start(Activity activity) {
        Intent intent = new Intent(activity, NoteMakeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_make);
        ButterKnife.bind(this);

    }

}
