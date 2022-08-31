package com.jason.common_resources;

import com.jason.common_resources.constants.DimenTypes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

public class MakeUtils {
    private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n";
    private static final String XML_RESOURCE_START = "<resources>\r\n";
    private static final String XML_RESOURCE_END = "</resources>\r\n";
    private static final String XML_DIMEN_TEMPLETE = "<dimen name=\"wsresx%2$d\">%3$.2fdp</dimen>\r\n";
    private static final String XML_DIMEN_SIZE_TEMPLETE = "<dimen name=\"wsrestext_size_%2$d\">%3$.2fdp</dimen>\r\n";
    private static final String XML_BASE_DPI = "<dimen name=\"wsresx\">%ddp</dimen>\r\n";
    private static final int MAX_SIZE = 720;
    private static final String XML_NAME = "dimens.xml";

    public MakeUtils() {
    }

    public static float px2dip(float pxValue, int sw, int designWidth) {
        float dpValue = pxValue / (float)designWidth * (float)sw;
        BigDecimal bigDecimal = new BigDecimal((double)dpValue);
        float finDp = bigDecimal.setScale(2, 4).floatValue();
        return finDp;
    }

    private static String makeAllDimens(DimenTypes type, int designWidth) {
        StringBuilder sb = new StringBuilder();

        try {
            sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n");
            sb.append("<resources>\r\n");
            String temp = String.format("<dimen name=\"wsresx\">%ddp</dimen>\r\n", type.getSwWidthDp());
            sb.append(temp);

            float dpValue;
            int i;
            for(i = 0; i <= 720; ++i) {
                dpValue = px2dip((float)i, type.getSwWidthDp(), designWidth);
                temp = String.format("<dimen name=\"wsresx%2$d\">%3$.2fdp</dimen>\r\n", "", i, dpValue);
                sb.append(temp);
            }

            for(i = 0; i <= 720; ++i) {
                dpValue = px2dip((float)i, type.getSwWidthDp(), designWidth);
                temp = String.format("<dimen name=\"text_size_%2$d\">%3$.2fdp</dimen>\r\n", "", i, dpValue);
                sb.append(temp);
            }

            sb.append("</resources>\r\n");
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return sb.toString();
    }

    public static void makeAll(int designWidth, DimenTypes type, String buildDir) {
        try {
            if (type.getSwWidthDp() <= 0) {
                return;
            }

            String folderName = "values-sw" + type.getSwWidthDp() + "dp";
            File file = new File(buildDir + File.separator + folderName);
            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fos = new FileOutputStream(file.getAbsolutePath() + File.separator + "dimens.xml");
            fos.write(makeAllDimens(type, designWidth).getBytes());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException var6) {
            var6.printStackTrace();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

    }
}
