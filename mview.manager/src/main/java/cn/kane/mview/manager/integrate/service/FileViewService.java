package cn.kane.mview.manager.integrate.service;

import cn.kane.mview.cvs.entity.File;
import cn.kane.mview.cvs.entity.files.FileContent;
import cn.kane.mview.cvs.entity.files.FileName;

import java.util.List;

/**
 * Created by kane on 2017/6/11.
 */
public interface FileViewService<K extends FileName,V extends FileContent> {

    File<K,V> view(K fileName, String operator) ;

    File<K,V> viewPreVersion(K fileName,String operator) ;

    List<File<K,V>> getFiles(List<K> fileNames, String operator) ;

    List<K> listFileNamesInWorkspace(String operator,String type) ;

    List<K> listFileNamesInWorkspace(Long branchId) ;
}
