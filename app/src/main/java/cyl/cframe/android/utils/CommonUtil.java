package cyl.cframe.android.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().equals("");
	}

	public static boolean isServiceReturnNull(String str) {
		return str == null || str.trim().equals("") || str.trim().equals("{}")
				|| str.trim().equals("[{}]") || str.trim().equals("[]");
	}
	
	
	/**
	 * 半角转换为全角,解决排版混乱问题。 
	 * 
	 * @param input
	 * @return
	 */
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	
	/**
	 * 去除特殊字符或将所有英文标号替换为中文标号,解决排版混乱问题。 
	 * 
	 * @param str
	 * @return
	 */
	public static String stringFilter(String str) {
		str = str.replaceAll("[", "【").replaceAll("]", "】")
				.replaceAll("!", "！").replaceAll(":", "：")
				.replaceAll(".", "。").replaceAll("?", "？")
				.replaceAll(",", "，");// 替换中文标号
		str = replaceQuotation(str);
		String regEx = "[『』]"; // 清除掉特殊字符
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	private static String replaceQuotation(String str) {
		Log.e("log", "replaceQuotation");
		if (!str.contains("\""))
			return str;

		StringBuffer sb = new StringBuffer(str);
		int position = sb.indexOf("\"");
		sb.setCharAt(position, '“');
		position = sb.indexOf("\"", position + 1);
		if (position >= 0)
			sb.setCharAt(position, '”');

		return replaceQuotation(sb.toString());
	}
	
	public static String replaceUrlcode(String str) {
		Log.e("log", "replaceUrlcode");
		if (!str.contains(" "))
			return str;

		//StringBuffer sb = new StringBuffer(str);
		//int position = str.indexOf("\\+");
		//str.split("%20", position);
		
		str = str.replaceAll(" ", "%20");
		Log.e("log", "replaceUrlcode:"+str);
		return replaceUrlcode(str);
	}
	
	
	/**
	  * 返回美国时间格式 26 Apr 2006
	  * 
	  * @param str
	  * @return
	  */
	public static String getEDate(String str) {
	   try {
		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		   ParsePosition pos = new ParsePosition(0);
		   Date strtodate = formatter.parse(str, pos);
		   String j = strtodate.toString();
		   String[] k = j.split(" ");
		   return k[2] +" "+ k[1].toUpperCase() +" "+ k[5].substring(0, 4);
		} catch (Exception e) {
			e.printStackTrace();
		}
	   return str;
	}
	

	/**
	 * 将px值转换为dip或dp值，保证尺寸大小不变
	 * 
	 * @param pxValue
	 * @param scale
	 *            （DisplayMetrics类中属性density）
	 * @return
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将dip或dp值转换为px值，保证尺寸大小不变
	 * 
	 * @param dipValue
	 * @param scale
	 *            （DisplayMetrics类中属性density）
	 * @return
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param pxValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}
	
	/** 
	 * 将dip或dp值转换为px值，保证尺寸大小不变 
	 *  
	 * @param dipValue 
	 * @param scale 
	 *            （DisplayMetrics类中属性density） 
	 * @return 
	 */  
	public static int dp2px(Context context, float dipValue) {  
		final float scale = context.getResources().getDisplayMetrics().density;  
		return (int) (dipValue * scale + 0.5f);  
	}  
	
	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 * @param spValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}
	
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final Paint paint = new Paint();
		//保证是方形，并且从中心画
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		int w;
		int deltaX = 0;
		int deltaY = 0;
		if (width <= height) {
			w = width;
			deltaY = height - w;
		} else {
			w = height;
			deltaX = width - w;
		}
		final Rect rect = new Rect(deltaX, deltaY, w, w);
		final RectF rectF = new RectF(rect);

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		//圆形，所有只用一个

		int radius = (int) (Math.sqrt(w * w * 2.0d) / 2);
		canvas.drawRoundRect(rectF, radius, radius, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}
	
}
