package io.feng24k.app.gank.ui.widget.swipeload;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshHeaderLayout;
import com.wang.avi.AVLoadingIndicatorView;

import io.feng24k.app.gank.R;

/**
 * Created by Aspsine on 2015/9/9.
 */
public class SwipeRefreshHeaderView extends SwipeRefreshHeaderLayout {

    private TextView tvRefresh;

    private AVLoadingIndicatorView progressBar;

    private int mHeaderHeight;

    private boolean rotated = false;

    public SwipeRefreshHeaderView(Context context) {
        this(context, null);
    }

    public SwipeRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHeaderHeight = getResources().getDimensionPixelOffset(R.dimen.refresh_header_height);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvRefresh = (TextView) findViewById(R.id.swipe_refresh_header_tv);
        progressBar = (AVLoadingIndicatorView) findViewById(R.id.progressbar);
    }

    @Override
    public void onRefresh() {
        progressBar.setVisibility(VISIBLE);
        tvRefresh.setText(getResources().getString(R.string.refresh_header_refreshing_text));
    }

    @Override
    public void onPrepare() {

    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            progressBar.setVisibility(GONE);
            if (y > mHeaderHeight) {
                tvRefresh.setText(getResources().getString(R.string.refresh_header_release_text));
                if (!rotated) {
                    rotated = true;
                }
            } else if (y < mHeaderHeight) {
                if (rotated) {
                    rotated = false;
                }
                tvRefresh.setText(getResources().getString(R.string.refresh_header_swipe_text));
            }
        }
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        rotated = false;
        progressBar.setVisibility(GONE);
        tvRefresh.setText(getResources().getString(R.string.refresh_header_complete_text));
    }

    @Override
    public void onReset() {
        rotated = false;
        progressBar.setVisibility(GONE);
    }

}
