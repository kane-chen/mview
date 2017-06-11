package cn.kane.mview.manager.integrate.service;

import cn.kane.mview.manager.integrate.entity.Requirement;

import java.util.List;

/**
 * Created by kane on 2017/6/11.
 */
public interface RequirementManageService {

    Long add(Requirement requirement) ;

    void edit(Requirement requirement) ;

    void remove(Long requirementId) ;

    Requirement view(Long requirementId) ;

    void compareAndSetStatus(Long requirementId,int srcStatus,int newStatus) ;

    List<Requirement> list(Long fromId, Long toId) ;

    Long getWorking() ;

}
