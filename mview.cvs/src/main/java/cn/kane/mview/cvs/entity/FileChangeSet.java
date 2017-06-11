package cn.kane.mview.cvs.entity;

import cn.kane.mview.cvs.entity.files.FileName;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by kane on 2017/6/11.
 */
public class FileChangeSet<K extends FileName> implements Serializable {

    private static final long serialVersionUID = 3495073317644937099L;
    private Long branchId ;

    private Set<FileChange<K>> changes = new ConcurrentSkipListSet<>();

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Set<FileChange<K>> getChanges() {
        return changes;
    }

    public void setChanges(Set<FileChange<K>> changes) {
        this.changes = changes;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this) ;
    }
}
