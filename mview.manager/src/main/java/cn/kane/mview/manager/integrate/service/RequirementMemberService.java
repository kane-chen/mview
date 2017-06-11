package cn.kane.mview.manager.integrate.service;

/**
 * Created by kane on 2017/6/11.
 */
public interface RequirementMemberService {

    void join(String operator,Long requirementId) ;

    Long onWhich(String operator) ;

}
