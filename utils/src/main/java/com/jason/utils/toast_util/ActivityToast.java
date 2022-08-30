package com.jason.utils.toast_util;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.jason.utils.toast_util.config.IToast;

public final class ActivityToast implements IToast {
    private final ToastImpl mToastImpl;
    private View mView;
    private TextView mMessageView;
    private int mGravity;
    private int mDuration;
    private int mXOffset;
    private int mYOffset;
    private float mHorizontalMargin;
    private float mVerticalMargin;

    public ActivityToast(Activity activity) {
        this.mToastImpl = new ToastImpl(activity, this);
    }

    public void show() {
        this.mToastImpl.show();
    }

    public void cancel() {
        this.mToastImpl.cancel();
    }

    public void setText(int id) {
        if (this.mView != null) {
            this.setText(this.mView.getResources().getString(id));
        }
    }

    public void setText(CharSequence text) {
        if (this.mMessageView != null) {
            this.mMessageView.setText(text);
        }
    }

    public void setView(View view) {
        this.mView = view;
        if (this.mView == null) {
            this.mMessageView = null;
        } else {
            this.mMessageView = this.findMessageView(view);
        }
    }

    public View getView() {
        return this.mView;
    }

    public void setDuration(int duration) {
        this.mDuration = duration;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public void setGravity(int gravity, int xOffset, int yOffset) {
        this.mGravity = gravity;
        this.mXOffset = xOffset;
        this.mYOffset = yOffset;
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

    public void setMargin(float horizontalMargin, float verticalMargin) {
        this.mHorizontalMargin = horizontalMargin;
        this.mVerticalMargin = verticalMargin;
    }

    public float getHorizontalMargin() {
        return this.mHorizontalMargin;
    }

    public float getVerticalMargin() {
        return this.mVerticalMargin;
    }
}

