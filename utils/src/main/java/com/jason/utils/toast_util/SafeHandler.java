package com.jason.utils.toast_util;


import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

final class SafeHandler extends Handler {
    private final Handler mHandler;

    SafeHandler(Handler handler) {
        this.mHandler = handler;
    }

    public void handleMessage(Message msg) {
        try {
            this.mHandler.handleMessage(msg);
        } catch (IllegalStateException | WindowManager.BadTokenException var3) {
            var3.printStackTrace();
        }

    }
}
