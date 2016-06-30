package io.gank.feng24k.app.ui.widget.swipeload;

import com.aspsine.swipetoloadlayout.OnRefreshListener;

import org.robobinding.widgetaddon.AbstractListeners;

public class OnSwipeRefreshListeners extends AbstractListeners<OnRefreshListener>
        implements OnRefreshListener {
    @Override
    public void onRefresh() {
        for ( OnRefreshListener onRefreshListener : listeners)
            onRefreshListener.onRefresh();
    }
}
