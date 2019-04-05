package qkun.com.wanandroid.ui.home;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import qkun.com.wanandroid.R;
import qkun.com.wanandroid.base.fragment.BaseFragment;
import qkun.com.wanandroid.bean.ArticlesBean;
import qkun.com.wanandroid.bean.HomeBannerBean;
import qkun.com.wanandroid.utils.GlideImageLoader;

public class HomePagerFragment extends BaseFragment<HomePagerPresenter> implements HomePagerContract.View {
    public static final String TAG = "HomeFragment";
    @BindView(R.id.home_pager_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
//    @BindView(R.id.fake_status_bar)
//    View fakeStatusBar;

    private HomeAdapter mHomeAdapter;

    private Banner mBanner;

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

//        fakeStatusBar.setBackgroundColor(getResources().getColor(R.color.transparent));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mHomeAdapter = new HomeAdapter(new ArrayList<ArticlesBean.DatasBean>());
        mRecyclerView.setAdapter(mHomeAdapter);

        View home_recycle_header = LayoutInflater.from(getActivity()).inflate(R.layout.home_recycle_header, (ViewGroup) mRecyclerView.getParent(), false);
        mBanner = home_recycle_header.findViewById(R.id.banner);
        mHomeAdapter.addHeaderView(home_recycle_header);

        mPresenter.loadHomeData(true);
    }

    @Override
    public void getHomeArticles(ArticlesBean articlesBean, int checker) {
//        ToastUtils.showShort(articlesBean.getDatas().get(0).getAuthor());
    }

    @Override
    public void getHomeBanner(final List<HomeBannerBean> beans) {
        List<String> images = new ArrayList();
        List<String> titles = new ArrayList();
        for (HomeBannerBean bean : beans) {
            images.add(bean.getImagePath());
            titles.add(bean.getTitle());
        }
        mBanner.setImages(images)
                .setBannerTitles(titles)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setImageLoader(new GlideImageLoader())
                .start();
//        mBanner.setOnBannerListener(new OnBannerListener() {
//            @Override
//            public void OnBannerClick(int position) {
//                ToastUtils.showLong(beans.get(position).getUrl());
//            }
//        });
    }

}
