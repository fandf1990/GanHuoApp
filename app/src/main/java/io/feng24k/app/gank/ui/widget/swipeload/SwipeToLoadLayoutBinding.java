package io.feng24k.app.gank.ui.widget.swipeload;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import org.robobinding.viewbinding.BindingAttributeMappings;
import org.robobinding.viewbinding.ViewBinding;

public class SwipeToLoadLayoutBinding implements ViewBinding<SwipeToLoadLayout> {
    @Override
    public void mapBindingAttributes(BindingAttributeMappings<SwipeToLoadLayout> mappings) {
        mappings.mapOneWayProperty(SwipeToLoadLayoutRefreshAttribute.class, "refreshing");
        mappings.mapOneWayProperty(SwipeToLoadLayoutLoadMoreAttribute.class, "loadingMore");
        mappings.mapEvent(OnSwipeRefreshAttribute.class, "onRefresh");
        mappings.mapEvent(OnSwipeLoadMoreAttribute.class, "onLoadMore");
    }


}
