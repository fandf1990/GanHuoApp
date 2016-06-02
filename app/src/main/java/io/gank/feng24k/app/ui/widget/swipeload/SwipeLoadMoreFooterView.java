package io.gank.feng24k.app.ui.widget.swipeload;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreFooterLayout;
import com.wang.avi.AVLoadingIndicatorView;

import io.gank.feng24k.app.R;


/**
 * Created by Aspsine on 2015/9/2.
 */
public class SwipeLoadMoreFooterView extends SwipeLoadMoreFooterLayout {

    private TextView tvLoadMore;

    private AVLoadingIndicatorView progressBar;

    private int mFooterHeight;

    public SwipeLoadMoreFooterView(Context context) {
        this(context, null);
    }

    public SwipeLoadMoreFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeLoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mFooterHeight = getResources().getDimensionPixelOffset(R.dimen.refresh_header_height);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvLoadMore = (TextView) findViewById(R.id.tvLoad);
        progressBar = (AVLoadingIndicatorView) findViewById(R.id.progressbar);
    }

    @Override
    public void onPrepare() {

    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            progressBar.setVisibility(GONE);
            if (-y >= mFooterHeight) {
                tvLoadMore.setText(getResources().getString(R.string.load_footer_release_text));
            } else if (-y < mFooterHeight) {
                tvLoadMore.setText(getResources().getString(R.string.load_footer_swipe_text));
            }
        }
    }

    @Override
    public void onLoadMore() {
        progressBar.setVisibility(VISIBLE);
        tvLoadMore.setText(getResources().getString(R.string.refresh_header_refreshing_text));
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        progressBar.setVisibility(GONE);
        tvLoadMore.setText(getResources().getString(R.string.load_footer_complete_text));
    }

    @Override
    public void onReset() {
        progressBar.setVisibility(GONE);
    }
}
