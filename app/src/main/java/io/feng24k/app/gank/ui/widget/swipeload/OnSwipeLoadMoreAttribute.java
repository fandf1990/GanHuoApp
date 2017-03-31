package io.feng24k.app.gank.ui.widget.swipeload;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import org.robobinding.attribute.Command;
import org.robobinding.viewattribute.event.EventViewAttribute;

/**
 * Author: fandf
 * Create Date: 2016/6/17.
 * Email: fandf@525happy.cn
 */
public class OnSwipeLoadMoreAttribute implements
        EventViewAttribute<SwipeToLoadLayout, SwipeToLoadLayoutAddOn> {
    @Override
    public void bind(final SwipeToLoadLayoutAddOn swipeToLoadLayoutAddOn, final Command command, final SwipeToLoadLayout swipeToLoadLayout) {
        swipeToLoadLayoutAddOn.addOnLoadMoreListener(new OnSwipeLoadMoreListeners(){
            @Override
            public void onLoadMore() {
                SwipeToLoadLayoutEvent event = new SwipeToLoadLayoutEvent(swipeToLoadLayout);
                command.invoke(event);
            }
        });
    }

    @Override
    public Class<?> getEventType() {
        return SwipeToLoadLayoutEvent.class;
    }
}
