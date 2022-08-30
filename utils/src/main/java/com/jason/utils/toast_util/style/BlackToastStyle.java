package com.jason.utils.toast_util.style;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jason.utils.toast_util.config.IToastStyle;

/**
 * DES:
 * <p>
 * Date: 2022/8/30  14:37
 *
 * @author Jason
 */
public class BlackToastStyle implements IToastStyle<TextView> {
    public BlackToastStyle() {
    }

    @SuppressLint("ResourceType")
    public TextView createView(Context context) {
        TextView textView = new TextView(context);
        textView.setId(16908299);
        textView.setGravity(this.getTextGravity(context));
        textView.setTextColor(this.getTextColor(context));
        textView.setTextSize(0, this.getTextSize(context));
        int horizontalPadding = this.getHorizontalPadding(context);
        int verticalPadding = this.getVerticalPadding(context);
        if (Build.VERSION.SDK_INT >= 16) {
            textView.setPaddingRelative(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
        } else {
            textView.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
        }

        textView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        Drawable background = this.getBackgroundDrawable(context);
        if (Build.VERSION.SDK_INT >= 16) {
            textView.setBackground(background);
        } else {
            textView.setBackgroundDrawable(background);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            textView.setZ(this.getTranslationZ(context));
        }

        textView.setMaxLines(this.getMaxLines(context));
        return textView;
    }

    protected int getTextGravity(Context context) {
        return 17;
    }

    protected int getTextColor(Context context) {
        return -285212673;
    }

    protected float getTextSize(Context context) {
        return TypedValue.applyDimension(2, 16.0F, context.getResources().getDisplayMetrics());
    }

    protected int getHorizontalPadding(Context context) {
        return (int)TypedValue.applyDimension(1, 24.0F, context.getResources().getDisplayMetrics());
    }

    protected int getVerticalPadding(Context context) {
        return (int)TypedValue.applyDimension(1, 16.0F, context.getResources().getDisplayMetrics());
    }

    protected Drawable getBackgroundDrawable(Context context) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(-2013265920);
        drawable.setCornerRadius(TypedValue.applyDimension(1, 10.0F, context.getResources().getDisplayMetrics()));
        return drawable;
    }

    protected float getTranslationZ(Context context) {
        return TypedValue.applyDimension(1, 3.0F, context.getResources().getDisplayMetrics());
    }

    protected int getMaxLines(Context context) {
        return 5;
    }
}
