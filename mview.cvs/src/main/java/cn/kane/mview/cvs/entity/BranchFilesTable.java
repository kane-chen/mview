package cn.kane.mview.cvs.entity;

import cn.kane.mview.cvs.entity.files.FileName;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by kane on 2017/6/11.
 */
public class BranchFilesTable<K extends FileName> implements Serializable {

    private static final long serialVersionUID = 7898830396796150788L;
    private Long branchId ;

    private Long parentBranchId ;

    private Map<K,Long> filesTable = new ConcurrentHashMap<>() ;

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getParentBranchId() {
        return parentBranchId;
    }

    public void setParentBranchId(Long parentBranchId) {
        this.parentBranchId = parentBranchId;
    }

    public Map<K, Long> getFilesTable() {
        return filesTable;
    }

    public void setFilesTable(Map<K, Long> filesTable) {
        this.filesTable = filesTable;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
