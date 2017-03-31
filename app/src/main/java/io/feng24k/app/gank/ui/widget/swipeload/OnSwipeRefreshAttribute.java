package io.feng24k.app.gank.ui.widget.swipeload;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import org.robobinding.attribute.Command;
import org.robobinding.viewattribute.event.EventViewAttribute;

public class OnSwipeRefreshAttribute implements
        EventViewAttribute<SwipeToLoadLayout, SwipeToLoadLayoutAddOn> {
    @Override
    public void bind(final SwipeToLoadLayoutAddOn swipeToLoadLayoutAddOn, final Command command, final SwipeToLoadLayout swipeToLoadLayout) {
        swipeToLoadLayoutAddOn.addOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
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
