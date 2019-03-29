package qkun.com.wanandroid.base.presenter;

import qkun.com.wanandroid.base.view.IView;

public interface IPresenter<T extends IView> {
    /**
     * attachView
     *
     * @param view view
     */
    void attachView(T view);

    /**
     * detachView
     */
    void detachView();

    void reload();

    void registerEventBus();

    void unregisterEventBus();

}
