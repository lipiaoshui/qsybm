package org.jeecgframework.jwt.thead;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import org.jeecgframework.jwt.def.JwtConstants;
import org.jeecgframework.jwt.service.TokenDataManager;
import org.jeecgframework.jwt.util.DateUtils;
import org.jeecgframework.web.system.pojo.base.TSUser;

/**
 * 判断Token是否失效的线程
 * 
 * @author LiPiaoShui
 *
 */
public class TokenThread extends Thread {

	private String pattern = "yyyyMMddHHmmss";
	
	@Override
	public void run() {

		while (true) {

			try {
				
				// 获取所有Token数据
				ConcurrentMap<String, TSUser> tokenMap = TokenDataManager.getAllTokens();
				Set<Entry<String, TSUser>> set = tokenMap.entrySet();
				Iterator<Entry<String, TSUser>> iterator = set.iterator();
				while (iterator.hasNext()) {
					Entry<String, TSUser> entry = iterator.next();
					String lastTime = entry.getValue().getLastTime();
					long distSecond = DateUtils.getDistSecond(lastTime, pattern);
					// 如果超出指定时间，则移除容器中的Token
					if (distSecond > JwtConstants.TOKEN_EXPIRES_SECOND) {
						iterator.remove();
					}
				}

				// 休眠1分钟
				Thread.sleep(1000 * 60);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
