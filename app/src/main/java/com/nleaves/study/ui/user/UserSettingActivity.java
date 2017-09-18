package com.nleaves.study.ui.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nleaves.study.R;
import com.nleaves.study.ui.base.BaseActivity;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserSettingActivity extends BaseActivity {


    @BindView(R.id.topbar)
    QMUITopBar topbar;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, UserSettingActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
        ButterKnife.bind(this);
        initTopBar();
    }

    private void initTopBar() {
        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        topbar.setTitle("设置");
    }
}
