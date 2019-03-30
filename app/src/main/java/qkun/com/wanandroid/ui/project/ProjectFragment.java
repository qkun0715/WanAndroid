package qkun.com.wanandroid.ui.project;

import android.os.Bundle;
import android.view.View;

import qkun.com.wanandroid.R;
import qkun.com.wanandroid.base.fragment.BaseFragment;

public class ProjectFragment extends BaseFragment {
    public static final String TAG = "ProjectFragment";

    public static ProjectFragment newInstance(String params) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, params);
        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
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
