package cn.kane.mview.manager.integrate.dao;

import cn.kane.mview.manager.integrate.entity.RequirementMember;

/**
 * Created by kane on 2017/6/11.
 */
public interface RequirementMemberDAO {

    void add(RequirementMember requirementMember) ;

    void edit(RequirementMember requirementMember) ;

    void remove(String operator) ;

}
