package com.jason.utils.toast_util;

import android.app.Application;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jason.utils.toast_util.config.IToast;

public class SystemToast extends Toast implements IToast {
    private TextView mMessageView;

    public SystemToast(Application application) {
        super(application);
    }

    public void setView(View view) {
        super.setView(view);
        if (view == null) {
            this.mMessageView = null;
        } else {
            this.mMessageView = this.findMessageView(view);
        }
    }

    public void setText(CharSequence text) {
        super.setText(text);
        if (this.mMessageView != null) {
            this.mMessageView.setText(text);
        }
    }
}

