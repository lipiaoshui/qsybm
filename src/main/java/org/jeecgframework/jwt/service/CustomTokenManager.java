package org.jeecgframework.jwt.service;

import org.jeecgframework.jwt.model.TokenModel;
import org.jeecgframework.jwt.util.DateUtils;
import org.jeecgframework.jwt.util.RandomUtils;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.stereotype.Component;

/**
 * 自定义Token管理器
 * @author LiPiaoShui
 *
 */
@Component
public class CustomTokenManager implements TokenManager {

	@Override
	public String createToken(TSUser user) {
		// 使用UUID作为Token
		String token = RandomUtils.getUUID();
		// 将新建Token添加到Token数据管理器
		user.setLastTime(DateUtils.getCurrentTime("yyyyMMddHHmmss"));
		TokenDataManager.addToken(token, user);
		return token;
	}

	@Override
	public boolean checkToken(TokenModel model) {
		if (model == null) {
            return false;
        }
		// 获取指定Token对应的值
		TSUser user = TokenDataManager.getValue(model.getToken());
		if(user == null) {
			return false;
		}
		// 更新最后一次访问的时间
		user.setLastTime(DateUtils.getCurrentTime("yyyyMMddHHmmss"));
		return true;
	}

	@Override
	public TokenModel getToken(String token,String userid) {
        return new TokenModel(userid, token);
    }

	@Override
	public void deleteToken(String username) {
		
	}

}
