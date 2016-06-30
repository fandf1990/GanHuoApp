package io.gank.feng24k.app.ui.widget.swipeload;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import org.robobinding.widgetaddon.view.ViewAddOnForView;

public class SwipeToLoadLayoutAddOn extends ViewAddOnForView {

    private final SwipeToLoadLayout view;
    private OnSwipeRefreshListeners mOnRefreshListeners;
    private OnSwipeLoadMoreListeners mOnLoadMoreListeners;

    public SwipeToLoadLayoutAddOn(SwipeToLoadLayout view) {
        super(view);
        this.view = view;
    }

    public void addOnRefreshListener(OnRefreshListener listener) {
        ensureOnRefreshListenersInitialized();
        mOnRefreshListeners.addListener(listener);
    }


    public void addOnLoadMoreListener(OnLoadMoreListener listener) {
        ensureOnLoadMoreListenersInitialized();
        mOnLoadMoreListeners.addListener(listener);
    }

    private void ensureOnRefreshListenersInitialized() {
        if (mOnRefreshListeners == null) {
            mOnRefreshListeners = new OnSwipeRefreshListeners();
            view.setOnRefreshListener(mOnRefreshListeners);
        }
    }

    private void ensureOnLoadMoreListenersInitialized() {
        if (mOnLoadMoreListeners == null) {
            mOnLoadMoreListeners = new OnSwipeLoadMoreListeners();
            view.setOnLoadMoreListener(mOnLoadMoreListeners);
        }
    }
}
