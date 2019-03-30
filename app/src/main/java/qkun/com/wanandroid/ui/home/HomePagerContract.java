package qkun.com.wanandroid.ui.home;

import java.util.List;

import qkun.com.wanandroid.base.presenter.IPresenter;
import qkun.com.wanandroid.base.view.IView;
import qkun.com.wanandroid.bean.ArticlesBean;
import qkun.com.wanandroid.bean.HomeBannerBean;
import qkun.com.wanandroid.constant.LoadType;

public interface HomePagerContract {

    interface View extends IView {
        //对View 来说 只关心最终的数据即可
        void getHomeArticles(ArticlesBean articlesBean, @LoadType.checker int checker);

        void getHomeBanner(List<HomeBannerBean> beans);
    }

    interface Presenter extends IPresenter<View> {
        void loadHomeArticles(boolean isShowStatusView);

        void loadHomeBanner(boolean isShowStatusView);

        //banner 和 articles 一起请求
        void loadHomeData(boolean isShowStatusView);

        void refresh(boolean isShowStatusView);

        void loadMore();
    }
}
