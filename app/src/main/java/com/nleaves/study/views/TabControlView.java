package com.nleaves.study.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.nleaves.study.R;

/**
 * Created by zaiyongyao on 2017/9/17.
 */

public class TabControlView extends LinearLayout implements View.OnClickListener {

    private TabItemView note;
    private TabItemView deal;
    private TabItemView coach;
    private TabItemView me;

    private  OnTabClickListener onTabClickListener;

    public TabControlView(Context context) {
        super(context);
    }

    public TabControlView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabControlView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public OnTabClickListener getOnTabClickListener() {
        return onTabClickListener;
    }

    public void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        this.onTabClickListener = onTabClickListener;
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();

        note = (TabItemView) findViewById(R.id.tab_note);
        deal = (TabItemView) findViewById(R.id.tab_deal);
        coach = (TabItemView) findViewById(R.id.tab_coach);
        me = (TabItemView) findViewById(R.id.tab_me);
        note.setOnClickListener(this);
        deal.setOnClickListener(this);
        coach.setOnClickListener(this);
        me.setOnClickListener(this);
    }

    private void resetAllCheck() {
        note.setChecked(false);
        deal.setChecked(false);
        coach.setChecked(false);
        me.setChecked(false);

    }



    @Override
    public void onClick(View v) {
        resetAllCheck();
        switch (v.getId()) {
            case R.id.tab_note:
                note.setChecked(true);
                if(onTabClickListener!=null){
                    onTabClickListener.onTabClick(0);
                }
                break;
            case R.id.tab_deal:
                deal.setChecked(true);
                if(onTabClickListener!=null){
                    onTabClickListener.onTabClick(1);
                }
                break;
            case R.id.tab_coach:
                coach.setChecked(true);

                if(onTabClickListener!=null){
                    onTabClickListener.onTabClick(2);
                }
                break;
            case R.id.tab_me:
                me.setChecked(true);
                if(onTabClickListener!=null){
                    onTabClickListener.onTabClick(3);
                }
                break;
        }

    }

    public void setTabSelect(int index) {
        resetAllCheck();
        switch (index) {

            case 0:
                note.setChecked(true);
                break;
            case 1:
                deal.setChecked(true);
                break;
            case 3:
                coach.setChecked(true);
                break;
            case 4:
                me.setChecked(true);
                break;
        }
    }

    public interface OnTabClickListener {
        public void onTabClick(int index);
    }
}
