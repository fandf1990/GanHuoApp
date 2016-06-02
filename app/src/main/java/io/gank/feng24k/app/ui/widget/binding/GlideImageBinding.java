package io.gank.feng24k.app.ui.widget.binding;


import org.robobinding.annotation.ViewBinding;
import org.robobinding.customviewbinding.CustomViewBinding;

import io.gank.feng24k.app.ui.widget.GlideImageView;

@ViewBinding(simpleOneWayProperties = {
        "imageBitmap",/*when it is bitmap*/
        "imageUrl"/*when it is remote url,file,resouceID*/
})
public class GlideImageBinding extends CustomViewBinding<GlideImageView> {
}