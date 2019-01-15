package org.jeecgframework.jwt.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.jeecgframework.web.system.pojo.base.TSUser;

/**
 * Token数据管理器
 * @author LiPiaoShui
 *
 */
public final class TokenDataManager {

	// 存储登录用户的Token数据
	private static ConcurrentMap<String,TSUser> tokenMap = new ConcurrentHashMap<String,TSUser>();
	
	/**
	 * 获取所有Token数据
	 * @return
	 */
	public static ConcurrentMap<String,TSUser> getAllTokens() {
		return tokenMap;
	}
	
	/**
	 * 根据Token获取对应的值
	 * @param token
	 * @return
	 */
	public static TSUser getValue(String token) {
		return tokenMap.get(token);
	}
	
	/**
	 * 添加Token数据
	 * @param token
	 * @param user
	 */
	public static void addToken(String token, TSUser user) {
		tokenMap.put(token, user);
	}
	
	/**
	 * 移除Token数据
	 * @param token
	 */
	public static void removeToken(String token) {
		tokenMap.remove(token);
	}
	
}
