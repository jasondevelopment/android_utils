package com.jason.utils.toast_util;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import android.widget.Toast;

import com.jason.utils.toast_util.config.IToast;

final class ToastImpl {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    private static final int SHORT_DURATION_TIMEOUT = 2000;
    private static final int LONG_DURATION_TIMEOUT = 3500;
    private final IToast mToast;
    private final WindowLifecycle mWindowLifecycle;
    private final String mPackageName;
    private boolean mShow;
    private final Runnable mShowRunnable = new Runnable() {
        public void run() {
            WindowManager.LayoutParams params = new WindowManager.LayoutParams();
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.format = PixelFormat.TRANSLUCENT;
            params.windowAnimations = android.R.style.Animation_Toast;
            params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
            params.packageName = ToastImpl.this.mPackageName;
            params.gravity = ToastImpl.this.mToast.getGravity();
            params.x = ToastImpl.this.mToast.getXOffset();
            params.y = ToastImpl.this.mToast.getYOffset();
            params.verticalMargin = ToastImpl.this.mToast.getVerticalMargin();
            params.horizontalMargin = ToastImpl.this.mToast.getHorizontalMargin();

            try {
                Activity activity = ToastImpl.this.mWindowLifecycle.getActivity();
                if (activity == null || activity.isFinishing()) {
                    return;
                }

                WindowManager manager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
                if (manager == null) {
                    return;
                }

                manager.addView(ToastImpl.this.mToast.getView(), params);
                ToastImpl.HANDLER.postDelayed(() -> {
                    ToastImpl.this.cancel();
                }, ToastImpl.this.mToast.getDuration() == Toast.LENGTH_LONG ? LONG_DURATION_TIMEOUT : SHORT_DURATION_TIMEOUT);
                ToastImpl.this.setShow(true);
                ToastImpl.this.mWindowLifecycle.register(ToastImpl.this);
            } catch (WindowManager.BadTokenException | IllegalStateException var4) {
                var4.printStackTrace();
            }

        }
    };
    private final Runnable mCancelRunnable = new Runnable() {
        public void run() {
            try {
                Activity activity = ToastImpl.this.mWindowLifecycle.getActivity();
                if (activity == null) {
                    return;
                }

                WindowManager manager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
                if (manager == null) {
                    return;
                }

                manager.removeViewImmediate(ToastImpl.this.mToast.getView());
            } catch (IllegalArgumentException var6) {
                var6.printStackTrace();
            } finally {
                ToastImpl.this.setShow(false);
                ToastImpl.this.mWindowLifecycle.unregister();
            }

        }
    };

    ToastImpl(Activity activity, IToast toast) {
        this.mToast = toast;
        this.mPackageName = activity.getPackageName();
        this.mWindowLifecycle = new WindowLifecycle(activity);
    }

    boolean isShow() {
        return this.mShow;
    }

    void setShow(boolean show) {
        this.mShow = show;
    }

    void show() {
        if (!this.isShow()) {
            HANDLER.removeCallbacks(this.mShowRunnable);
            HANDLER.post(this.mShowRunnable);
        }
    }

    void cancel() {
        if (this.isShow()) {
            HANDLER.removeCallbacks(this.mCancelRunnable);
            HANDLER.post(this.mCancelRunnable);
        }
    }
}
