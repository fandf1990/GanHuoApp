package io.feng24k.app.gank.ui.widget.swipeload;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import org.robobinding.viewattribute.property.OneWayPropertyViewAttribute;

public class SwipeToLoadLayoutRefreshAttribute implements OneWayPropertyViewAttribute<SwipeToLoadLayout, Boolean> {

    @Override
    public void updateView(SwipeToLoadLayout swipeToLoadLayout, Boolean aBoolean) {
        swipeToLoadLayout.setRefreshing(aBoolean);
    }
}
