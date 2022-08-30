package com.jason.utils.toast_util.style;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jason.utils.toast_util.config.IToastStyle;


public class ViewToastStyle implements IToastStyle<View> {
    private final int mLayoutId;
    private final IToastStyle<?> mStyle;

    public ViewToastStyle(int id, IToastStyle<?> style) {
        this.mLayoutId = id;
        this.mStyle = style;
    }

    public View createView(Context context) {
        return LayoutInflater.from(context).inflate(this.mLayoutId, (ViewGroup)null);
    }

    public int getGravity() {
        return this.mStyle == null ? 17 : this.mStyle.getGravity();
    }

    public int getXOffset() {
        return this.mStyle == null ? 0 : this.mStyle.getXOffset();
    }

    public int getYOffset() {
        return this.mStyle == null ? 0 : this.mStyle.getYOffset();
    }

    public float getHorizontalMargin() {
        return this.mStyle == null ? 0.0F : this.mStyle.getHorizontalMargin();
    }

    public float getVerticalMargin() {
        return this.mStyle == null ? 0.0F : this.mStyle.getVerticalMargin();
    }
}
