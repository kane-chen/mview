package cn.kane.mview.cvs.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by kane on 2017/6/11.
 */
public class Branch implements Serializable {
    private static final long serialVersionUID = -1352085926663882033L;
    private Long id ;
    private Long parentId ;
    private Long preId ;
    private String name ;
    private String creator ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getPreId() {
        return preId;
    }

    public void setPreId(Long preId) {
        this.preId = preId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
