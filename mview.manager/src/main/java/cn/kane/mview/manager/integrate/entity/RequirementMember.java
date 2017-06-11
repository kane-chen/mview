package cn.kane.mview.manager.integrate.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kane on 2017/6/11.
 */
public class RequirementMember implements Serializable {

    private static final long serialVersionUID = -6818669842764883978L;
    private String member ;
    private Long requirementId ;

    public Long getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Long requirementId) {
        this.requirementId = requirementId;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this) ;
    }
}
