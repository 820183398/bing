package com.nleaves.study.ui.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nleaves.study.R;
import com.nleaves.study.ui.base.BaseFragment;
import com.nleaves.study.ui.note.NoteMakeActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 首页notefragment
 */
public class HomeNoteFragment extends BaseFragment {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    Unbinder unbinder;

    public static HomeNoteFragment newInstance() {
        HomeNoteFragment fragment = new HomeNoteFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_home_note, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        topbar.setTitle("在线专辑制作");
    }


    @OnClick(R.id.make_new)
    void makeNew() {
        NoteMakeActivity.start(getActivity());
    }


    @OnClick(R.id.make_old)
    void makeOld() {
        NoteMakeActivity.start(getActivity());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
