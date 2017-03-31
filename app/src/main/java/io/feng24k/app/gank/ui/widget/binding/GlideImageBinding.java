package io.feng24k.app.gank.ui.widget.binding;


import org.robobinding.annotation.ViewBinding;
import org.robobinding.customviewbinding.CustomViewBinding;

import io.feng24k.app.gank.ui.widget.GlideImageView;

@ViewBinding(simpleOneWayProperties = {
        "imageBitmap",/*when it is bitmap*/
        "imageUrl"/*when it is remote url,file,resouceID*/
})
public class GlideImageBinding extends CustomViewBinding<GlideImageView> {
}