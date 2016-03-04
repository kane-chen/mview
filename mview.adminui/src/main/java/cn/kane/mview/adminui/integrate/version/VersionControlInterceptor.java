package cn.kane.mview.adminui.integrate.version;

import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import cn.kane.mview.adminui.integrate.service.ChangesManageService;
import cn.kane.mview.adminui.integrate.service.RequirementManageService;
import cn.kane.mview.adminui.integrate.service.RequirementMemberService;
import cn.kane.mview.service.definition.entity.AbstractDefinition;

public class VersionControlInterceptor {

	@Autowired
	private RequirementMemberService requirementMemberService ;
	@Autowired
	private RequirementManageService requirementManageService ;
	@Autowired
	private ChangesManageService changesManageService ;
	
	public void setVersion(JoinPoint jpoint){
		Object[] args = jpoint.getArgs() ;
		for(Object arg : args){
			if(null != arg && arg instanceof AbstractDefinition){
				AbstractDefinition def = (AbstractDefinition)arg ;
				//publish,update-trunk,no-reversion
				if("trunk".equalsIgnoreCase(def.getKey().getVersion())){
					return ;
				}
				String version = requirementMemberService.query(def.getOperator()) ;
				if(null == requirementManageService.get(version)){
					throw new UnsupportedOperationException(String.format("operator[%s] not in valid-requirement[%s]", def.getOperator(),version)) ;
				}
				def.getKey().setVersion(version.toString()) ;
			}
		}
	}
	
	public void addChanges(JoinPoint jpoint){
		Object[] args = jpoint.getArgs() ;
		for(Object arg : args){
			if(null != arg && arg instanceof AbstractDefinition){
				AbstractDefinition def = (AbstractDefinition)arg ;
				changesManageService.add(def.getKey().getVersion(), def.getKey()) ;
			}
		}
	}
}
