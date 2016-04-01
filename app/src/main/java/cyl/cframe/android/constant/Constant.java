package cyl.cframe.android.constant;

import android.text.TextUtils;

/**
 * Created by jerry on 2016/3/10.
 */
public class Constant {
    /**
     * 转换图片网址
     *
     * @param orgUrl 图片原始url
     * @return 转换后的图片url
     */
    public static String convertImageUrl(final String orgUrl) {

        if (TextUtils.isEmpty(orgUrl)) {
            return null;
        }
        String url = orgUrl;
        if (!url.toLowerCase().startsWith("http")) {
//            url = HttpURL.IMAGE_SERVER_URL + orgUrl;
        }
        return url;
    }

}
