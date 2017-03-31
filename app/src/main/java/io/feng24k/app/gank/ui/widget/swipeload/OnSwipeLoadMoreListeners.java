package io.feng24k.app.gank.ui.widget.swipeload;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;

import org.robobinding.widgetaddon.AbstractListeners;

public class OnSwipeLoadMoreListeners extends AbstractListeners<OnLoadMoreListener>
        implements OnLoadMoreListener {

    @Override
    public void onLoadMore() {
        for ( OnLoadMoreListener onLoadMoreListener : listeners)
            onLoadMoreListener.onLoadMore();
    }
}
