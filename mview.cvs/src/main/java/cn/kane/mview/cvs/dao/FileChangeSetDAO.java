package cn.kane.mview.cvs.dao;

import cn.kane.mview.cvs.entity.FileChange;
import cn.kane.mview.cvs.entity.FileChangeSet;
import cn.kane.mview.cvs.entity.files.FileName;

/**
 * Created by kane on 2017/6/11.
 */
public interface FileChangeSetDAO<K extends FileName> {

    void add(FileChangeSet<K> changeSet) ;

    FileChangeSet<K> view(Long branchId) ;

    void addOrUpdateFileChange(Long branchId,FileChange<K> fileChange) ;

    void discardFileChange(Long branchId,K fileName) ;

    void viewFileChange(Long branchId,K fileName) ;
}
