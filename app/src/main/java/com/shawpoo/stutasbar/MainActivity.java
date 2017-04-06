package com.shawpoo.stutasbar;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

public class MainActivity extends BaseActivity {

    private Toolbar mToolbar;
    private RadioGroup mRadioGroup;
    private View mStatusBarView; //无需半透明可不写

    @Override
    public void onCreate(Bundle savedInstanceState) {
//        setLightStatusBar(true);
        super.onCreate(savedInstanceState);
        mToolbar = findViewByID(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.app_name));
        mStatusBarView = findViewByID(R.id.statusBar);

        mRadioGroup = findViewByID(R.id.radioGroup);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioBtn1:
                        mToolbar.setVisibility(View.VISIBLE);
                        mStatusBarView.setVisibility(View.GONE);
                        showStatusBar();
                        break;
                    case R.id.radioBtn2:
                        mToolbar.setVisibility(View.GONE);
                        mStatusBarView.setVisibility(View.GONE);
                        hideStatusBar();
                        break;
                    case R.id.radioBtn3: //半透明需要特殊处理一下
                        mToolbar.setVisibility(View.GONE);
                        hideStatusBar();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            FrameLayout.LayoutParams lParams = new FrameLayout.LayoutParams
                                    (FrameLayout.LayoutParams.MATCH_PARENT, ScreenUtil
                                            .getStatusBarHeight
                                            (MainActivity.this));
                            mStatusBarView.setLayoutParams(lParams);
                            mStatusBarView.setVisibility(View.VISIBLE);
                        }
                        break;
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
