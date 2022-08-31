package com.jason.common_resources.generator;

import com.jason.common_resources.MakeUtils;
import com.jason.common_resources.constants.DimenTypes;

import java.io.File;

public class DimenGenerator {
    /**
     * 设计稿尺寸(将自己设计师的设计稿的宽度填入)
     */
    private static final int DESIGN_WIDTH = 375;

    /**
     * 设计稿的高度  （将自己设计师的设计稿的高度填入）
     */
    private static final int DESIGN_HEIGHT = 667;

    public DimenGenerator() {
    }

    public static void main(String[] args) {
        int smallest = DESIGN_WIDTH > DESIGN_HEIGHT ? DESIGN_HEIGHT : DESIGN_WIDTH;  //     求得最小宽度;
        DimenTypes[] values = DimenTypes.values();
        DimenTypes[] var3 = values;
        int var4 = values.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            DimenTypes value = var3[var5];
            File file = new File("/Users/jasonchen/Desktop/android_utils/utils/src/main/res");
            MakeUtils.makeAll(smallest, value, file.getAbsolutePath());
        }

    }
}

