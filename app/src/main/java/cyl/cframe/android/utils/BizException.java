package cyl.cframe.android.utils;

/**
 * 业务异常类
 * 
 * 
 */
public class BizException extends Exception {
	private static final long serialVersionUID = -7294511800719769763L;
	private String errMessage;

	public BizException() {
		super();
	}

	public BizException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
		this.errMessage = detailMessage;
	}

	public BizException(String detailMessage) {
		super(detailMessage);
	}

	public BizException(Throwable throwable) {
		super(throwable);
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
}
