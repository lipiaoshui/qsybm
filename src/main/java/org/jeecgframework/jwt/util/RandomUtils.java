package org.jeecgframework.jwt.util;

import java.util.UUID;

/**
 * 随机数工具类
 * @author LiPiaoShui
 *
 */
public class RandomUtils {

	/**
	 * 获取32位随机UUID
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
