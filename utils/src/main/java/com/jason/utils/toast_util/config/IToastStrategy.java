package com.jason.utils.toast_util.config;

import android.app.Application;


public interface IToastStrategy {

    void registerStrategy(Application var1);

    void bindStyle(IToastStyle<?> var1);

    IToast createToast(Application var1);

    void showToast(CharSequence var1);

    void cancelToast();
}
