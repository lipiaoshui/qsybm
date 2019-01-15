package org.jeecgframework.jwt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * @author LiPiaoShui
 *
 */
public class DateUtils {

	/**
	 * 获取指定格式的当前时间
	 * 
	 * @param pattern
	 * @return
	 */
	public static String getCurrentTime(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date());
    }

	/**
	 * 获取指定的时间距离当前时间多少秒
	 * 
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static long getDistSecond(String str, String pattern) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			Date d1 = sdf.parse(str);
			Date d2 = new Date();
			return (d2.getTime() - d1.getTime()) / 1000;
		} catch (ParseException e) {
			throw new RuntimeException(e.toString());
		}
	}
	
}
