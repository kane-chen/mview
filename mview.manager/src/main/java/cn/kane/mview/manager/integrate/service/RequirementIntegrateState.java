package cn.kane.mview.manager.integrate.service;

/**
 * Created by kane on 2017/6/11.
 */
public interface RequirementIntegrateState {

    String name(Long requirementId) ;

    String forwardName(Long requirementId) ;

    String backwardName(Long requirementId) ;

    String diableName(Long requirementId) ;

    void forward(Long requirementId) ;

    void backward(Long requirementId) ;

    void disable(Long requirementId) ;
}
