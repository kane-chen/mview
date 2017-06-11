package cn.kane.mview.cvs.dao;

import cn.kane.mview.cvs.entity.BranchFilesTable;
import cn.kane.mview.cvs.entity.files.FileName;

/**
 * Created by kane on 2017/6/11.
 */
public interface BranchFilesTableDAO<K extends FileName> {

    void add(BranchFilesTable<K> filesTable) ;

    void edit(BranchFilesTable<K> filesTable) ;

    BranchFilesTable<K> view(Long branchId) ;

}
