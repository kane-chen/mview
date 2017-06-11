package cn.kane.mview.manager.integrate.service;

import cn.kane.mview.cvs.entity.File;
import cn.kane.mview.cvs.entity.files.FileContent;
import cn.kane.mview.cvs.entity.files.FileName;

import java.util.List;

/**
 * Created by kane on 2017/6/11.
 */
public interface ChangesManageService<K extends FileName,V extends FileContent> {

    void add(Long requirementId,File<K,V> file,String operator) ;

    void edit(Long requirementId,File<K,V> file,String operator) ;

    void remove(Long requirementId,K fileName,String operator) ;

    List<K> listChanges(Long requirementId, String operator) ;
}
