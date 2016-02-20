package cn.kane.mview.adminui.web.utils;

import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

public class JsonDataEditor extends PropertiesEditor {

	
	@Override
	public String getAsText() {
		return JSON.toJSONString(super.getValue()) ;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isEmpty(text)) {
			setValue(null);
			return ;
		}
		setValue(JSON.parse(text));
	}
}
