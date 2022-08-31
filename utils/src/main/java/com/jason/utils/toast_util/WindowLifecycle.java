package com.jason.utils.toast_util;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;

final class WindowLifecycle implements Application.ActivityLifecycleCallbacks {
    private Activity mActivity;
    private ToastImpl mToastImpl;

    WindowLifecycle(Activity activity) {
        this.mActivity = activity;
    }

    Activity getActivity() {
        return this.mActivity;
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
        if (this.mActivity == activity) {
            if (this.mToastImpl != null) {
                this.mToastImpl.cancel();
            }
        }
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    public void onActivityDestroyed(Activity activity) {
        if (this.mActivity == activity) {
            if (this.mToastImpl != null) {
                this.mToastImpl.cancel();
            }

            this.unregister();
            this.mActivity = null;
        }
    }

    void register(ToastImpl impl) {
        this.mToastImpl = impl;
        if (this.mActivity != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                this.mActivity.registerActivityLifecycleCallbacks(this);
            } else {
                this.mActivity.getApplication().registerActivityLifecycleCallbacks(this);
            }

        }
    }

    void unregister() {
        this.mToastImpl = null;
        if (this.mActivity != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                this.mActivity.unregisterActivityLifecycleCallbacks(this);
            } else {
                this.mActivity.getApplication().unregisterActivityLifecycleCallbacks(this);
            }

        }
    }
}
