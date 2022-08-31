package com.jason.utils.toast_util.style;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jason.utils.toast_util.config.IToastStyle;

public class BlackToastStyle implements IToastStyle<TextView> {
    public BlackToastStyle() {
    }

    @SuppressLint("ResourceType")
    public TextView createView(Context context) {
        TextView textView = new TextView(context);
        textView.setId(android.R.id.message);
        textView.setGravity(this.getTextGravity(context));
        textView.setTextColor(this.getTextColor(context));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.getTextSize(context));
        int horizontalPadding = this.getHorizontalPadding(context);
        int verticalPadding = this.getVerticalPadding(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            textView.setPaddingRelative(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
        } else {
            textView.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
        }

        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Drawable background = this.getBackgroundDrawable(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            textView.setBackground(background);
        } else {
            textView.setBackgroundDrawable(background);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textView.setZ(this.getTranslationZ(context));
        }

        textView.setMaxLines(this.getMaxLines(context));
        return textView;
    }

    protected int getTextGravity(Context context) {
        return Gravity.CENTER;
    }

    protected int getTextColor(Context context) {
        return 0XEEFFFFFF;
    }

    protected float getTextSize(Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16.0F, context.getResources().getDisplayMetrics());
    }

    protected int getHorizontalPadding(Context context) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24.0F, context.getResources().getDisplayMetrics());
    }

    protected int getVerticalPadding(Context context) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16.0F, context.getResources().getDisplayMetrics());
    }

    protected Drawable getBackgroundDrawable(Context context) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(0X88000000);
        drawable.setCornerRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10.0F, context.getResources().getDisplayMetrics()));
        return drawable;
    }

    protected float getTranslationZ(Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3.0F, context.getResources().getDisplayMetrics());
    }

    protected int getMaxLines(Context context) {
        return 5;
    }
}
