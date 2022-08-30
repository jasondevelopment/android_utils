package com.jason.utils.toast_util.style;

import android.content.Context;
import android.view.View;

import com.jason.utils.toast_util.config.IToastStyle;


public class LocationToastStyle implements IToastStyle<View> {
    private final IToastStyle<?> mStyle;
    private final int mGravity;
    private final int mXOffset;
    private final int mYOffset;
    private final float mHorizontalMargin;
    private final float mVerticalMargin;

    public LocationToastStyle(IToastStyle<?> style, int gravity) {
        this(style, gravity, 0, 0, 0.0F, 0.0F);
    }

    public LocationToastStyle(IToastStyle<?> style, int gravity, int xOffset, int yOffset, float horizontalMargin, float verticalMargin) {
        this.mStyle = style;
        this.mGravity = gravity;
        this.mXOffset = xOffset;
        this.mYOffset = yOffset;
        this.mHorizontalMargin = horizontalMargin;
        this.mVerticalMargin = verticalMargin;
    }

    public View createView(Context context) {
        return this.mStyle.createView(context);
    }

    public int getGravity() {
        return this.mGravity;
    }

    public int getXOffset() {
        return this.mXOffset;
    }

    public int getYOffset() {
        return this.mYOffset;
    }

    public float getHorizontalMargin() {
        return this.mHorizontalMargin;
    }

    public float getVerticalMargin() {
        return this.mVerticalMargin;
    }
}
