package qkun.com.wanandroid.base.view;

import com.trello.rxlifecycle2.LifecycleTransformer;

public interface IView {

    void showErrorMsg(String errorMsg);

    void showLoading();

    void hideLoading();

    void showError();

    void showNoNetwork();

    void showEmpty();

    void showContent();

    /**
     * 绑定生命周期
     *
     * @param <T>
     * @return
     */
    <T> LifecycleTransformer<T> bindToLife();



}
