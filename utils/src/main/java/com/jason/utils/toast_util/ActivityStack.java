package com.jason.utils.toast_util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

final class ActivityStack implements Application.ActivityLifecycleCallbacks {
    private Activity mForegroundActivity;

    ActivityStack() {
    }

    static ActivityStack register(Application application) {
        ActivityStack lifecycle = new ActivityStack();
        application.registerActivityLifecycleCallbacks(lifecycle);
        return lifecycle;
    }

    public Activity getForegroundActivity() {
        return this.mForegroundActivity;
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
        this.mForegroundActivity = activity;
    }

    public void onActivityPaused(Activity activity) {
        if (this.mForegroundActivity == activity) {
            this.mForegroundActivity = null;
        }
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    public void onActivityDestroyed(Activity activity) {
    }
}

