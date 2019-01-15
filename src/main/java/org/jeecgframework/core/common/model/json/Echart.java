package org.jeecgframework.core.common.model.json;

import java.util.List;

/**
 * 统计报表模型
 * @author LiPiaoShui
 *
 */
public class Echart {

	private String name; //名称
	private String type; //类型
	private List data; //数据
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	
}
