package com.jason.utils.toast_util.config;

import android.content.Context;
import android.view.View;


public interface IToastStyle<V extends View> {
    V createView(Context var1);

    default int getGravity() {
        return 17;
    }

    default int getXOffset() {
        return 0;
    }

    default int getYOffset() {
        return 0;
    }

    default float getHorizontalMargin() {
        return 0.0F;
    }

    default float getVerticalMargin() {
        return 0.0F;
    }
}
