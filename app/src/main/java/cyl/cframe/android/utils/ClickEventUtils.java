package cyl.cframe.android.utils;


public class ClickEventUtils {
	private static long lastClickTime;
	private static boolean flag = true;

//	private static boolean isFastDoubleClick() {
//		if (flag) {
//			flag = false;
//			long time = System.currentTimeMillis();
//			//Log.e("isFastClick", time - lastClickTime + "");
//			Log.e("onclick","lastClickTime1:"+lastClickTime + "");
//			Log.e("onclick", time - lastClickTime + "");
//			if (time - lastClickTime < 800) {
//				flag = true;
//				//add by QuickLi 2012-11-05 for fix SZ-CASE-FE-Promotion-025 start
//				lastClickTime = time;
//				//add by QuickLi 2012-11-05 for fix SZ-CASE-FE-Promotion-025 end
//				Log.e("onclick", "onclick: true");
//				return true;
//			}
//			lastClickTime = time;
//			flag = true;
//			Log.e("onclick","lastClickTime2:"+lastClickTime + "");
//			Log.e("onclick", "onclick: false");
//			return false;
//		} else {
//			Log.e("onclick", "onclick: true");
//			return true;
//		}
//	}
	
	private static boolean isFastDoubleClick(int space) {
		long time = System.currentTimeMillis();
		boolean isFastClick = false;
		if (time - lastClickTime < space) {
			isFastClick = true;
		}
		lastClickTime = time;
		return isFastClick;
	}
	
	public static boolean needRaiseClickEvent(int space) {
		return isFastDoubleClick(space);
	}

	public static boolean needRaiseClickEvent() {
		return isFastDoubleClick(800);
	}
}
