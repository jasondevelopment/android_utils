package com.jason.utils.toast_util;

import android.content.Context;
import android.widget.Toast;

/**
 * DES:
 * <p>
 * Date: 2022/8/30  11:29
 *
 * @author Jason
 */
public class TestToast {

    public static void showToast(Context context,String content){
        Toast.makeText(context,"测试",Toast.LENGTH_LONG).show();
    }
}
