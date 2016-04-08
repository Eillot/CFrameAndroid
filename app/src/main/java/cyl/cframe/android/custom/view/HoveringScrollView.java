package cyl.cframe.android.custom.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 自定义滚动悬停scrollview
 * Created by jerry on 2016/4/7.
 */
public class HoveringScrollView extends ScrollView {

    private OnScrollListener onScrollListener;

    /**
     * 用户手指离开本view，本view还在继续滑动，用来保存Y的距离
     */
    private int lastScrollY;

    public HoveringScrollView(Context context) {
        super(context);
    }

    public HoveringScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置滚动接口
     *
     * @param onScrollListener
     */
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    /**
     * 用于用户手指离开MyScrollView的时候获取MyScrollView滚动的Y距离，然后回调给onScroll方法中
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int scrollY = HoveringScrollView.this.getScrollY();
            if (lastScrollY != scrollY) {
                lastScrollY = scrollY;
                handler.sendMessageDelayed(handler.obtainMessage(), 6);
            }
            if (onScrollListener!=null){
                onScrollListener.onScroll(scrollY);
            }
        }
    };

    /**
     * 重写onTouchEvent， 当用户的手在HoveringScrollview上面的时候，
     * 直接将HoveringScrollview滑动的Y方向距离回调给onScroll方法中，当用户抬起手的时候，
     * HoveringScrollview可能还在滑动，所以当用户抬起手我们隔6毫秒给handler发送消息，在handler处理
     * HoveringScrollview滑动的距离
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (onScrollListener!=null){
            onScrollListener.onScroll(lastScrollY=this.getScrollY());
        }
        switch (ev.getAction()){
            case MotionEvent.ACTION_UP:
                handler.sendMessageDelayed(handler.obtainMessage(),20);
                break;
            default:
                break;
        }

        return super.onTouchEvent(ev);
    }

    /**
     * 滚动的回调接口
     */
    public interface OnScrollListener {
        /**
         * 方法回调，返回本View滑动的方向距离
         */
        public void onScroll(int scrollY);
    }
}
