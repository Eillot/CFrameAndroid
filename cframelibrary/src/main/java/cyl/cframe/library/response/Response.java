
package cyl.cframe.library.response;

import java.io.Serializable;
import java.util.Date;

import cyl.cframe.library.utils.DateUtil;


/**
 * @author Dallas.Cao </br> Create at 2014年3月25日 下午4:20:38
 * @version 1.0
 */
public class Response implements Serializable {

    private static final long serialVersionUID = -4916688580556445457L;

    /** 错误代码 */
    private String code;

    /** 更新时间 */
    private long              time;

    /** 异常信息 */
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDate(String fmt) {
        return DateUtil.getFormatDateTime(new Date(time), fmt);
    }
}
