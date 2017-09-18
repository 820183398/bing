package com.nleaves.study.views;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nleaves.study.R;


public class TabItemView extends RelativeLayout {


    private ImageView imageview;
    private TextView textView;
    private Context mContext;
    private String tabText;
    private Drawable msrc;

    public TabItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public TabItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.TabItemView);
        tabText = a.getString(R.styleable.TabItemView_tabItemText);
        msrc = a.getDrawable(R.styleable.TabItemView_tabItemImage);
        mContext = context;
        a.recycle();
        initView(context);

    }

    public TabItemView(Context context) {
        this(context,null);
    }

    private void initView(Context context) {
        mContext = context;
        LayoutInflater.from(getContext()).inflate(R.layout.tab_item_view, this);
//        View view = View.inflate(context, R.layout.tab_item_view, this);
        imageview = (ImageView) findViewById(R.id.imageview);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(tabText);
        imageview.setImageDrawable(msrc);
    }




    public boolean isChecked() {
        return imageview.isSelected();
    }

    public void setChecked(boolean checked) {
        imageview.setSelected(checked);
        if (checked) {
            textView.setTextColor(mContext.getResources().getColor(R.color.qmui_config_color_blue));
        } else {
            textView.setTextColor(mContext.getResources().getColor(R.color.qmui_config_color_gray_6));
        }

    }

}
