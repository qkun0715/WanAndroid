package qkun.com.wanandroid.ui.home;

import android.os.Bundle;
import android.view.View;

import qkun.com.wanandroid.R;
import qkun.com.wanandroid.base.fragment.BaseFragment;

public class HomePagerFragment extends BaseFragment {
    public static final String TAG = "HomeFragment";

    public static HomePagerFragment newInstance(String params) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, params);
        HomePagerFragment fragment = new HomePagerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
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
