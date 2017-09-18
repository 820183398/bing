package com.nleaves.study.ui.home;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.nleaves.study.R;
import com.nleaves.study.ui.base.BaseActivity;
import com.nleaves.study.views.TabControlView;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 首页相关
 */
public class HomeActivity extends BaseActivity implements TabControlView.OnTabClickListener {

    @BindView(R.id.tabs)
    TabControlView tabs;

    private HomeNoteFragment mHomeNoteFragment; //在线专辑制作
    private HomeDealFragment mHomeDealFragment;//互助交易平台
    private HomeCoachFragment mHomeCoachFragment;//在线辅导
    private HomeMeFragment mHomeMeFragment;//我的


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initTabs();

        onNavChnage(0);
        tabs.setTabSelect(0);

    }

    private void initTabs() {
        tabs.setOnTabClickListener(this);
    }

    @Override
    public void onTabClick(int index) {
        onNavChnage(index);
    }


    private void hideFragments(FragmentTransaction transaction) {
        if (mHomeNoteFragment != null) {
            transaction.hide(mHomeNoteFragment);
        }
        if (mHomeDealFragment != null) {
            transaction.hide(mHomeDealFragment);
        }

        if (mHomeCoachFragment != null) {
            transaction.hide(mHomeCoachFragment);
        }
        if (mHomeMeFragment != null) {
            transaction.hide(mHomeMeFragment);
        }

    }



    protected void onNavChnage(int tab) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragments(transaction);
        switch (tab) {
            case 0:
                if (mHomeNoteFragment == null) {
                    mHomeNoteFragment = mHomeNoteFragment.newInstance();
                    transaction.add(R.id.fragment_container, mHomeNoteFragment);
                } else {
                    transaction.show(mHomeNoteFragment);
                }


                break;
            case 1:
                if (mHomeDealFragment == null) {
                    mHomeDealFragment = mHomeDealFragment.newInstance();
                    transaction.add(R.id.fragment_container, mHomeDealFragment);
                } else {
                    transaction.show(mHomeDealFragment);
                }

                break;
            case 2:

                if (mHomeCoachFragment == null) {
                    mHomeCoachFragment = mHomeCoachFragment.newInstance();
                    transaction.add(R.id.fragment_container, mHomeCoachFragment);
                } else {
                    transaction.show(mHomeCoachFragment);
                }
                break;

            case 3:

                if (mHomeMeFragment == null) {
                    mHomeMeFragment = mHomeMeFragment.newInstance();
                    transaction.add(R.id.fragment_container, mHomeMeFragment);
                } else {
                    transaction.show(mHomeMeFragment);
                }
                break;

            default:
                break;
        }
        transaction.commit();
    }
}
