package cn.kane.mview.adminui.web.vo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.beans.BeanCopier;

import com.alibaba.fastjson.JSON;

import cn.kane.mview.adminui.web.utils.DefinitionKeysJsonUtil;
import cn.kane.mview.service.definition.entity.DefinitionKey;
import cn.kane.mview.service.definition.entity.WidgetDefinition;

public class WidgetDefinitionVO extends WidgetDefinition {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1519598201001916853L;
	private static BeanCopier copier =	BeanCopier.create(WidgetDefinition.class, WidgetDefinitionVO.class, false);
	private String cssDefKey ;
	private String jsDefKey ;
	private String dtDefKey ;
	private String vtDefKey ;
	private String vkey ;
	
	private List<String> dsDefKeys ;
	
	public void parse(){
		if(StringUtils.isNotBlank(cssDefKey)){
			this.setCssDefinition(JSON.parseObject(cssDefKey, DefinitionKey.class));
		}
		if(StringUtils.isNotBlank(jsDefKey)){
			this.setJsDefinition(JSON.parseObject(jsDefKey, DefinitionKey.class));
		}
		if(StringUtils.isNotBlank(dtDefKey)){
			this.setDataTemplateDefinition(JSON.parseObject(dtDefKey, DefinitionKey.class));
		}
		if(StringUtils.isNotBlank(vtDefKey)){
			this.setViewTemplateDefinition(JSON.parseObject(vtDefKey, DefinitionKey.class));
		}
		if(StringUtils.isNotBlank(vkey)){
			this.setKey(JSON.parseObject(vkey, DefinitionKey.class));
		}
		if(null!=dsDefKeys){
			this.setDataReaderDefinitions(DefinitionKeysJsonUtil.parseKeys(dsDefKeys));
		}
	}
	
	public static WidgetDefinitionVO format(WidgetDefinition def){
		WidgetDefinitionVO vo = new WidgetDefinitionVO() ;
		copier.copy(def, vo, null);
		if(null!=def.getKey()){
			vo.setVkey(JSON.toJSONString(def.getKey()));
		}
		if(null!=def.getCssDefinition()){
			vo.setCssDefKey(JSON.toJSONString(def.getCssDefinition()));
		}
		if(null!=def.getJsDefinition()){
			vo.setJsDefKey(JSON.toJSONString(def.getJsDefinition()));
		}
		if(null!=def.getDataTemplateDefinition()){
			vo.setDtDefKey(JSON.toJSONString(def.getDataTemplateDefinition()));
		}
		if(null!=def.getViewTemplateDefinition()){
			vo.setVtDefKey(JSON.toJSONString(def.getViewTemplateDefinition()));
		}
		if(null!=def.getDataReaderDefinitions()){
			vo.setDsDefKeys(DefinitionKeysJsonUtil.formatKeys(def.getDataReaderDefinitions()));
		}
		return vo ;
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

	public String getDtDefKey() {
		return dtDefKey;
	}

	public void setDtDefKey(String dtDefKey) {
		this.dtDefKey = dtDefKey;
	}

	public String getVtDefKey() {
		return vtDefKey;
	}

	public void setVtDefKey(String vtDefKey) {
		this.vtDefKey = vtDefKey;
	}

	public List<String> getDsDefKeys() {
		return dsDefKeys;
	}

	public void setDsDefKeys(List<String> dsDefKeys) {
		this.dsDefKeys = dsDefKeys;
	}

	public String getVkey() {
		return vkey;
	}

	public void setVkey(String vkey) {
		this.vkey = vkey;
	}
	
}
