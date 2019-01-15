package com.jeecg.utils;

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
	
	public static void main(String[] args) {
		for(int i=0; i<20; i++) {
			System.out.println(RandomUtils.getUUID());
		}
	}
	
}
