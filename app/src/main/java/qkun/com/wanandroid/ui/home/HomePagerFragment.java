package qkun.com.wanandroid.ui.home;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import java.util.List;

import qkun.com.wanandroid.R;
import qkun.com.wanandroid.base.fragment.BaseFragment;
import qkun.com.wanandroid.bean.ArticlesBean;
import qkun.com.wanandroid.bean.HomeBannerBean;

public class HomePagerFragment extends BaseFragment<HomePagerPresenter> implements HomePagerContract.View {
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
        mFragmentComponent.inject(this);
    }

    @Override
    protected boolean isLazyLoad() {
        return false;
    }

    @Override
    protected void initView(View view) {
        mPresenter.loadHomeArticles(true);
    }

    @Override
    public void getHomeArticles(ArticlesBean articlesBean, int checker) {
        ToastUtils.showShort(articlesBean.getDatas().get(0).getAuthor());
    }

    @Override
    public void getHomeBanner(List<HomeBannerBean> beans) {

    }
}
