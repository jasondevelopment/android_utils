package com.jason.utils.toast_util.style;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;


public class WhiteToastStyle extends BlackToastStyle {
    public WhiteToastStyle() {
    }

    protected int getTextColor(Context context) {
        return -1157627904;
    }

    @SuppressLint("WrongConstant")
    protected Drawable getBackgroundDrawable(Context context) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(-1381654);
        drawable.setCornerRadius(TypedValue.applyDimension(1, 8.0F, context.getResources().getDisplayMetrics()));
        return drawable;
    }
}
