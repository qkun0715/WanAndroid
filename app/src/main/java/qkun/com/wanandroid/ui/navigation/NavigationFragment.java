package qkun.com.wanandroid.ui.navigation;

import android.os.Bundle;
import android.view.View;

import qkun.com.wanandroid.R;
import qkun.com.wanandroid.base.fragment.BaseFragment;

public class NavigationFragment extends BaseFragment {
    public static final String TAG = "NavigationFragment";

    public static NavigationFragment newInstance(String params) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, params);
        NavigationFragment fragment = new NavigationFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected boolean isLazyLoad() {
        return false;
    }

    @Override
    protected void initView(View view) {

    }
}
