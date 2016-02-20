package cn.kane.mview.adminui.integrate.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cn.kane.mview.adminui.integrate.service.RequirementMemberService;

@Service("requirementMemberService")
public class RequirementMemberServiceMemImpl implements RequirementMemberService{

	private Map<String,String> requirementMembers = new ConcurrentHashMap<String, String>() ;
	
	@PostConstruct
	public void init(){
		this.join("kane", "2");
	}
	
	@Override
	public void join(String operator, String requirementId) {
		if(StringUtils.isBlank(operator) || null == requirementId){
			throw new IllegalArgumentException(String.format("illegal-argument[operatoe=%s,requirementId=%s]", operator,requirementId)) ;
		}
		requirementMembers.put(operator, requirementId) ;
	}

	@Override
	public String query(String operator) {
		return requirementMembers.get(operator);
	}

}
