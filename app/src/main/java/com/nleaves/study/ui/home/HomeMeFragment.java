package com.nleaves.study.ui.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nleaves.study.R;
import com.nleaves.study.ui.base.BaseFragment;
import com.nleaves.study.ui.note.NoteMakeActivity;
import com.nleaves.study.ui.user.UserSettingActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 首页notefragment
 */
public class HomeMeFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    Unbinder unbinder;

    public static HomeMeFragment newInstance() {
        HomeMeFragment fragment = new HomeMeFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_home_me, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initTopBar();
    }


    @OnClick(R.id.user_seting)
    void userSeting() {
        UserSettingActivity.start(getActivity());
    }



    private void initTopBar() {
        topbar.setTitle("我的");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
