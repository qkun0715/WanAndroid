package qkun.com.wanandroid.ui.home;

import android.annotation.SuppressLint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import qkun.com.wanandroid.R;
import qkun.com.wanandroid.base.App;
import qkun.com.wanandroid.base.presenter.BasePresenter;
import qkun.com.wanandroid.bean.ArticlesBean;
import qkun.com.wanandroid.bean.HomeBannerBean;
import qkun.com.wanandroid.constant.Constant;
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
                        mView.getHomeArticles(articlesBean, checker);
                    }
                });

    }

    @SuppressLint("CheckResult")
    @Override
    public void loadHomeBanner(boolean isShowStatusView) {
        RetrofitManager.createApi(ApiService.class).getHomeBanner()
                .compose(RxSchedulers.<BaseResponse<List<HomeBannerBean>>>SchedulerTransformer())
                .compose(mView.<BaseResponse<List<HomeBannerBean>>>bindToLife())
                .subscribeWith(new BaseObserver<List<HomeBannerBean>>(mView,
                        App.getContext().getString(R.string.failed_to_obtain_banner_data),
                        true) {
                    @Override
                    public void onSuccess(List<HomeBannerBean> homeBannerBeans) {
                        mView.getHomeBanner(homeBannerBeans);
                    }

                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadHomeData(boolean isShowStatusView) {
        loadHomeBanner(isShowStatusView);
        loadHomeArticles(isShowStatusView);
//        Observable<BaseResponse<List<HomeBannerBean>>> homeBanner = RetrofitManager.createApi(ApiService.class).getHomeBanner();
//        Observable<BaseResponse<ArticlesBean>> homeArticles = RetrofitManager.createApi(ApiService.class).getHomeArticles(mPage);
//        Observable.zip(homeBanner, homeArticles, new BiFunction<BaseResponse<List<HomeBannerBean>>, BaseResponse<ArticlesBean>, Map<String, Object>>() {
//            @Override
//            public Map<String, Object> apply(BaseResponse<List<HomeBannerBean>> listBaseResponse, BaseResponse<ArticlesBean> articlesBeanBaseResponse) throws Exception {
//                Map<String, Object> objectMap = new HashMap<>();
//                objectMap.put(Constant.BANNER_KEY, listBaseResponse.getData());
//                objectMap.put(Constant.ARTICLE_KEY, articlesBeanBaseResponse.getData());
//                return objectMap;
//            }
//        }).compose(RxSchedulers.<Map<String, Object>>SchedulerTransformer())
//                .compose(mView.<Map<String, Object>>bindToLife())
//                .subscribe(new Consumer<Map<String, Object>>() {
//                    @Override
//                    public void accept(Map<String, Object> stringObjectMap) throws Exception {
//
//
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//
//                    }
//                });
    }

    @Override
    public void refresh(boolean isShowStatusView) {

    }

    @Override
    public void loadMore() {

    }
}
