package cyl.cframe.android.data;

import java.util.ArrayList;
import java.util.List;

import cyl.cframe.android.R;
import cyl.cframe.android.data.table.AdvertisementInfo;

/**
 * 测试数据
 * <p/>
 * Created by jerry on 2016/3/10.
 */
public class TestSimpleData {

    private static List<AdvertisementInfo> images = new ArrayList<>();
    private static int[] resImage = {
            R.drawable.loading_adv_detail_bg,
            R.drawable.home_adv,
            R.drawable.loading_adv_detail_bg,
            R.drawable.home_adv
    };

    private static TestSimpleData instance;

    public static synchronized TestSimpleData getInstance() {
        if (instance == null)
            instance = new TestSimpleData();
        return instance;
    }

    private TestSimpleData() {

    }

    /**
     * 获取轮播图
     *
     * @return
     */
    public List<AdvertisementInfo> getImages() {

        AdvertisementInfo advs = new AdvertisementInfo();
        for (int i : resImage) {


            images.add(advs);
        }

        return images;
    }
}
