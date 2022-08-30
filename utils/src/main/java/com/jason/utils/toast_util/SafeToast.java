package com.jason.utils.toast_util;

import android.annotation.TargetApi;
import android.app.Application;
import android.os.Handler;
import android.widget.Toast;

import java.lang.reflect.Field;

@TargetApi(19)
public final class SafeToast extends SystemToast {
    public SafeToast(Application application) {
        super(application);

        try {
            Field mTNField = Toast.class.getDeclaredField("mTN");
            mTNField.setAccessible(true);
            Object mTN = mTNField.get(this);
            Field mHandlerField = mTNField.getType().getDeclaredField("mHandler");
            mHandlerField.setAccessible(true);
            Handler mHandler = (Handler)mHandlerField.get(mTN);
            mHandlerField.set(mTN, new SafeHandler(mHandler));
        } catch (NoSuchFieldException | IllegalAccessException var6) {
            var6.printStackTrace();
        }

    }
}

