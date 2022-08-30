package com.jason.utils.toast_util;

import android.app.Application;
import android.content.res.Resources;

import com.jason.utils.R;
import com.jason.utils.toast_util.config.IToastInterceptor;
import com.jason.utils.toast_util.config.IToastStrategy;
import com.jason.utils.toast_util.config.IToastStyle;
import com.jason.utils.toast_util.style.BlackToastStyle;
import com.jason.utils.toast_util.style.LocationToastStyle;
import com.jason.utils.toast_util.style.ViewToastStyle;

public final class ToastUtils {
    private static Application sApplication;
    private static IToastStrategy sToastStrategy;
    private static IToastStyle<?> sToastStyle;
    private static IToastInterceptor sToastInterceptor;

    private ToastUtils() {
    }

    public static void init(Application application) {
        init(application, new ViewToastStyle(R.layout.layout_toast, sToastStyle));
    }

    public static void init(Application application, IToastStyle<?> style) {
        sApplication = application;
        if (sToastStrategy == null) {
            setStrategy(new ToastStrategy());
        }

        if (style == null) {
            style = new BlackToastStyle();
        }

        setStyle((IToastStyle)style);
    }

    public static void showToast(Object object) {
        showToast((CharSequence)(object != null ? object.toString() : "null"));
    }

    public static void showToast(int resId) {
        try {
            showToast(sApplication.getResources().getText(resId));
        } catch (Resources.NotFoundException var2) {
            showToast((CharSequence)String.valueOf(resId));
        }

    }

    public static void showToast(CharSequence text) {
        if (text != null && text.length() != 0) {
            if (sToastInterceptor == null || !sToastInterceptor.intercept(text)) {
                sToastStrategy.showToast(text);
            }
        }
    }

    public static void cancel() {
        sToastStrategy.cancelToast();
    }

    public static void setGravity(int gravity) {
        setGravity(gravity, 0, 0);
    }

    public static void setGravity(int gravity, int xOffset, int yOffset) {
        setGravity(gravity, xOffset, yOffset, 0.0F, 0.0F);
    }

    public static void setGravity(int gravity, int xOffset, int yOffset, float horizontalMargin, float verticalMargin) {
        sToastStrategy.bindStyle(new LocationToastStyle(sToastStyle, gravity, xOffset, yOffset, horizontalMargin, verticalMargin));
    }

    public static void setView(int id) {
        if (id > 0) {
            setStyle(new ViewToastStyle(id, sToastStyle));
        }
    }

    public static void setStyle(IToastStyle<?> style) {
        sToastStyle = style;
        sToastStrategy.bindStyle(style);
    }

    public static IToastStyle<?> getStyle() {
        return sToastStyle;
    }

    public static void setStrategy(IToastStrategy strategy) {
        sToastStrategy = strategy;
        sToastStrategy.registerStrategy(sApplication);
    }

    public static IToastStrategy getStrategy() {
        return sToastStrategy;
    }

    public static void setInterceptor(IToastInterceptor interceptor) {
        sToastInterceptor = interceptor;
    }

    public static IToastInterceptor getInterceptor() {
        return sToastInterceptor;
    }
}

