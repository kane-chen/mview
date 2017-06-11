package cn.kane.mview.cvs.entity;

import cn.kane.mview.cvs.entity.files.FileName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by kane on 2017/6/11.
 */
public class FileChange<K extends FileName> implements Serializable , Comparable {

    private static final long serialVersionUID = -4403780324204622728L;
    public static final int CHANGE_TYPE_ADD = 1 ;
    public static final int CHANGE_TYPE_EDIT = 2 ;
    public static final int CHANGE_TYPE_REMOVE = 3 ;
    public static final int CHANGE_TYPE_REMOVE_EXISTED = 4 ;

    private K fileName ;

    private int changeType ;

    public K getFileName() {
        return fileName;
    }

    public void setFileName(K fileName) {
        this.fileName = fileName;
    }

    public int getChangeType() {
        return changeType;
    }

    public void setChangeType(int changeType) {
        this.changeType = changeType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this,obj) ;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this) ;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
