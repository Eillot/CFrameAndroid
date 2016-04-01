package cyl.cframe.android.data.table;

import cyl.cframe.library.database.annotation.Table;
import cyl.cframe.library.response.Entity;


@Table(name = "t_advs")
public class AdvertisementInfo extends Entity {
    /**
     *
     */
    private static final long serialVersionUID = 6579959686869408295L;
    public static final String TYPE_WEB = "WEB";
    public static final String TYPE_COMMON = "COMMON";
    public static final String TYPE_INAPP = "INAPP";
    /**
     * 广告类型
     */
    private String type;
    /**
     * 广告标题
     */
    private String title;
    /**
     * 广告描述
     */
    private String description;
    /**
     * 广告小图标
     */
    private String smallImageUrl;
    /**
     * 广告大图标
     */
    private String imageUrl;
    /**
     * 广告排序
     */
    private int sortOrder;
    /**
     * 广告跳转Url
     */
    private String url;
    private String startTime;
    private String endTime;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
