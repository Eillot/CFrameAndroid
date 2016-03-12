package cyl.cframe.android.data.model;

import cyl.cframe.library.response.Entity;

/**
 * Created by jerry on 2016/3/10.
 */
public class VersionUpdateInfo extends Entity {

    private static final long serialVersionUID = 6735854369026844827L;

    private String id;

    // 服务器的APP版本: such as 1.0
    private String version;

    // 是否必须更新（不更新程序无法运行）：true or false
    private String forcedUpdate;

    // 下载链接: such as http://...
    private String updateUrl;

    private String updateInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getForcedUpdate() {
        return forcedUpdate;
    }

    public void setForcedUpdate(String forcedUpdate) {
        this.forcedUpdate = forcedUpdate;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    public String getUpdateInfo() {
        return updateInfo;
    }

    public void setUpdateInfo(String updateInfo) {
        this.updateInfo = updateInfo;
    }

}
