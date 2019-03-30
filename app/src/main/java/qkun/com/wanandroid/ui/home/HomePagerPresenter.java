package qkun.com.wanandroid.ui.home;

import android.annotation.SuppressLint;

import javax.inject.Inject;

import qkun.com.wanandroid.R;
import qkun.com.wanandroid.base.App;
import qkun.com.wanandroid.base.presenter.BasePresenter;
import qkun.com.wanandroid.bean.ArticlesBean;
import qkun.com.wanandroid.constant.LoadType;
import qkun.com.wanandroid.http.ApiService;
import qkun.com.wanandroid.http.BaseObserver;
import qkun.com.wanandroid.http.BaseResponse;
import qkun.com.wanandroid.http.RetrofitManager;
import qkun.com.wanandroid.http.RxSchedulers;

public class HomePagerPresenter extends BasePresenter<HomePagerContract.View> implements HomePagerContract.Presenter {
    private int mPage = 0;
    private boolean mIsRefresh;

    @Inject
    public HomePagerPresenter() {
        this.mIsRefresh = true;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadHomeArticles(boolean isShowStatusView) {
        RetrofitManager.createApi(ApiService.class)
                .getHomeArticles(mPage)
                .compose(RxSchedulers.<BaseResponse<ArticlesBean>>SchedulerTransformer())
                .compose(mView.<BaseResponse<ArticlesBean>>bindToLife())
                .subscribeWith(new BaseObserver<ArticlesBean>(mView,
                        App.getContext().getString(R.string.failed_to_obtain_banner_data),
                        true) {
                    @Override
                    public void onSuccess(ArticlesBean articlesBean) {
                        int checker = mIsRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                        mView.getHomeArticles(articlesBean,checker);
                    }
                });

    }

    @Override
    public void loadHomeBanner(boolean isShowStatusView) {

    }

    @Override
    public void loadHomeData(boolean isShowStatusView) {

    }

    @Override
    public void refresh(boolean isShowStatusView) {

    }

    @Override
    public void loadMore() {

    }
}
