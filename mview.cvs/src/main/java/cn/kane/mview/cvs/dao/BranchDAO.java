package cn.kane.mview.cvs.dao;

import cn.kane.mview.cvs.entity.Branch;

import java.util.List;

/**
 * Created by kane on 2017/6/11.
 */
public interface BranchDAO {

    Long add(Branch branch) ;

    void edit(Branch branch) ;

    Branch view(Long branchId) ;

    List<Branch> list(Long fromId,Long toId) ;

}
