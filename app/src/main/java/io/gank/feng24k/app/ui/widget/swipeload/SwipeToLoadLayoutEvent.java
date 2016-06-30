package io.gank.feng24k.app.ui.widget.swipeload;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import org.robobinding.widget.view.AbstractViewEvent;

public class SwipeToLoadLayoutEvent extends AbstractViewEvent {
    protected SwipeToLoadLayoutEvent(SwipeToLoadLayout view) {
        super(view);
    }

    @Override
    public SwipeToLoadLayout getView() {
        return (SwipeToLoadLayout) super.getView();
    }
}
