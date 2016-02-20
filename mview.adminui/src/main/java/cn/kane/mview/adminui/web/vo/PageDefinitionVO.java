package cn.kane.mview.adminui.web.vo;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.beans.BeanCopier;

import com.alibaba.fastjson.JSON;

import cn.kane.mview.adminui.web.utils.DefinitionKeysJsonUtil;
import cn.kane.mview.service.definition.entity.DefinitionKey;
import cn.kane.mview.service.definition.entity.PageDefinition;

public class PageDefinitionVO extends PageDefinition {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1188569625749504907L;

	private static BeanCopier copier =	BeanCopier.create(PageDefinition.class, PageDefinitionVO.class, false);
	private String layoutDefKey ;
	private String cssDefKey ;
	private String jsDefKey ;
	private List<String> dsDefKeys ;
	private List<String> widgetDefKeys ;
	
	public void parse(){
		if(StringUtils.isNotBlank(cssDefKey)){
			this.setCssDefinition(JSON.parseObject(cssDefKey, DefinitionKey.class));
		}
		if(StringUtils.isNotBlank(jsDefKey)){
			this.setJsDefinition(JSON.parseObject(jsDefKey, DefinitionKey.class));
		}
		if(StringUtils.isNotBlank(layoutDefKey)){
			this.setLayoutDefinition(JSON.parseObject(layoutDefKey, DefinitionKey.class));
		}
		if(null!=dsDefKeys){
			this.setDataReaderDefinitions(DefinitionKeysJsonUtil.parseDataReadServiceKey(dsDefKeys));
		}
		if(null!=widgetDefKeys){
			this.setWidgetDefinitions(DefinitionKeysJsonUtil.parseDataReadServiceKey(widgetDefKeys));
		}
	}
	
	public static PageDefinitionVO format(PageDefinition def) throws IOException{
		PageDefinitionVO vo = new PageDefinitionVO() ;
		copier.copy(def, vo, null);
		if(null!=def.getCssDefinition()){
			vo.setCssDefKey(JSON.toJSONString(def.getCssDefinition()));
		}
		if(null!=def.getJsDefinition()){
			vo.setJsDefKey(JSON.toJSONString(def.getJsDefinition()));
		}
		if(null!=def.getLayoutDefinition()){
			vo.setLayoutDefKey(JSON.toJSONString(def.getLayoutDefinition()));
		}
		if(null!=def.getDataReaderDefinitions()){
			vo.setDsDefKeys(DefinitionKeysJsonUtil.formatDataReadServiceKey(def.getDataReaderDefinitions()));
		}
		if(null!=def.getWidgetDefinitions()){
			vo.setWidgetDefKeys(DefinitionKeysJsonUtil.formatDataReadServiceKey(def.getWidgetDefinitions()));
		}
		return vo ;
	}
	
	public List<String> getDsDefKeys() {
		return dsDefKeys;
	}
	public void setDsDefKeys(List<String> dsDefKeys) {
		this.dsDefKeys = dsDefKeys;
	}
	public List<String> getWidgetDefKeys() {
		return widgetDefKeys;
	}
	public void setWidgetDefKeys(List<String> widgetDefKeys) {
		this.widgetDefKeys = widgetDefKeys;
	}
	public String getLayoutDefKey() {
		return layoutDefKey;
	}
	public void setLayoutDefKey(String layoutDefKey) {
		this.layoutDefKey = layoutDefKey;
	}
	public String getCssDefKey() {
		return cssDefKey;
	}
	public void setCssDefKey(String cssDefKey) {
		this.cssDefKey = cssDefKey;
	}
	public String getJsDefKey() {
		return jsDefKey;
	}
	public void setJsDefKey(String jsDefKey) {
		this.jsDefKey = jsDefKey;
	}
	
	
}
