/**
 * 
 */
package cyl.cframe.library.exception;

import cyl.cframe.library.exception.ServiceException;

/**
 * SD card 文件操作异常
 * 
 * @author Richard.Ma
 * 
 */
public class FileException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1442439299450146708L;


	/**
	 * Creates a new SelectorException object.
	 * 
	 * @param s
	 */
	public FileException(String s) {
		super(s);
	}

	/**
	 * Creates a new SelectorException object.
	 * 
	 * @param s
	 * @param throwable
	 */
	public FileException(String s, Throwable throwable) {
		super(s, throwable);
	}

	/**
	 * Creates a new SelectorException object.
	 * 
	 * @param throwable
	 */
	public FileException(Throwable throwable) {
		super(throwable);
	}
}
