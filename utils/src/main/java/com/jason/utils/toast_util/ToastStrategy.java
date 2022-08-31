package com.jason.utils.toast_util;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import com.jason.utils.toast_util.config.IToast;
import com.jason.utils.toast_util.config.IToastStrategy;
import com.jason.utils.toast_util.config.IToastStyle;

import java.lang.ref.WeakReference;

public class ToastStrategy extends Handler implements IToastStrategy {
    private static final int DELAY_TIMEOUT = 200;
    private static final int TYPE_SHOW = 1;
    private static final int TYPE_CANCEL = 2;
    private Application mApplication;
    private ActivityStack mActivityStack;
    private WeakReference<IToast> mToastReference;
    private IToastStyle<?> mToastStyle;

    public ToastStrategy() {
        super(Looper.getMainLooper());
    }

    public void registerStrategy(Application application) {
        this.mApplication = application;
        this.mActivityStack = ActivityStack.register(application);
    }

    public void bindStyle(IToastStyle<?> style) {
        this.mToastStyle = style;
    }

    public IToast createToast(Application application) {
        Activity resumedActivity = this.mActivityStack.getForegroundActivity();
        Object toast;
        if (resumedActivity != null) {
            toast = new ActivityToast(resumedActivity);
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1) {
            toast = new SafeToast(application);
        } else {
            toast = new SystemToast(application);
        }

        if (toast instanceof ActivityToast || Build.VERSION.SDK_INT < Build.VERSION_CODES.R || application.getApplicationInfo().targetSdkVersion < Build.VERSION_CODES.R) {
            ((IToast) toast).setView(this.mToastStyle.createView(application));
            ((IToast) toast).setGravity(this.mToastStyle.getGravity(), this.mToastStyle.getXOffset(), this.mToastStyle.getYOffset());
            ((IToast) toast).setMargin(this.mToastStyle.getHorizontalMargin(), this.mToastStyle.getVerticalMargin());
        }

        return (IToast) toast;
    }

    public void showToast(CharSequence text) {
        Message msg = Message.obtain();
        msg.what = TYPE_SHOW;
        msg.obj = text;
        this.sendMessageDelayed(msg, DELAY_TIMEOUT);
    }

    public void cancelToast() {
        this.sendEmptyMessage(TYPE_CANCEL);
    }

    public void handleMessage(Message msg) {
        IToast toast = null;
        if (this.mToastReference != null) {
            toast = (IToast) this.mToastReference.get();
        }

        switch (msg.what) {
            case TYPE_SHOW:
                if (msg.obj instanceof CharSequence) {
                    CharSequence text = (CharSequence) msg.obj;
                    if (toast != null) {
                        toast.cancel();
                    }

                    toast = this.createToast(this.mApplication);
                    this.mToastReference = new WeakReference(toast);
                    toast.setDuration(this.getDuration(text));
                    toast.setText(text);
                    toast.show();
                }
                break;
            case TYPE_CANCEL:
                if (toast != null) {
                    toast.cancel();
                }
        }

    }

    protected int getDuration(CharSequence text) {
        return text.length() > 20 ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
    }
}
