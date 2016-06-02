package io.gank.feng24k.app.ui.widget.binding;


import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import org.robobinding.annotation.ViewBinding;
import org.robobinding.customviewbinding.CustomViewBinding;

@ViewBinding(simpleOneWayProperties = {
        "refreshing","loadingMore"
})
public class SwipeToLoadLayoutBinding extends CustomViewBinding<SwipeToLoadLayout> {
}
