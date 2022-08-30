package com.jason.utils.toast_util.config;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

/**
 * DES:
 * <p>
 * Date: 2022/8/30  14:32
 *
 * @author Jason
 */
public interface IToast {

    void show();

    void cancel();

    void setText(int var1);

    void setText(CharSequence var1);

    void setView(View var1);

    View getView();

    void setDuration(int var1);

    int getDuration();

    void setGravity(int var1, int var2, int var3);

    int getGravity();

    int getXOffset();

    int getYOffset();

    void setMargin(float var1, float var2);

    float getHorizontalMargin();

    float getVerticalMargin();

    @SuppressLint("ResourceType")
    default TextView findMessageView(View view) {
        if (view instanceof TextView) {
            if (view.getId() == -1) {
                view.setId(16908299);
            } else if (view.getId() != 16908299) {
                throw new IllegalArgumentException("You must set the ID value of TextView to android.R.id.message");
            }

            return (TextView)view;
        } else if (view.findViewById(16908299) instanceof TextView) {
            return (TextView)view.findViewById(16908299);
        } else {
            throw new IllegalArgumentException("You must include a TextView with an ID value of android.R.id.message");
        }
    }
}
