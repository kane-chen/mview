package cn.kane.mview.cvs.service;

import cn.kane.mview.cvs.entity.Branch;
import cn.kane.mview.cvs.entity.BranchFilesTable;
import cn.kane.mview.cvs.entity.FileChangeSet;
import cn.kane.mview.cvs.entity.files.FileName;

import java.util.List;

/**
 * Created by kane on 2017/6/11.
 */
public interface BranchService<K extends FileName> {

    Long addBranch(Branch branch) ;

    void editBranch(Branch branch) ;

    void invalidBranch(Long branchId) ;

    Branch getBranchById(Long branchId) ;

    List<Branch> getBranches(Long fromId, Long toId) ;

    void setStatus(Long branchId,int srcStatus,int newStatus) ;

    void publish(Long branchId,Long preBranchId) ;

    FileChangeSet<K> getFileChangeSetByBranchId(Long branchId) ;

    BranchFilesTable<K> getBranchFilesTableByBranchId(Long branchId) ;
}
