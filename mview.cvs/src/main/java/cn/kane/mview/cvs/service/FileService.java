package cn.kane.mview.cvs.service;

import cn.kane.mview.cvs.entity.File;
import cn.kane.mview.cvs.entity.files.FileContent;
import cn.kane.mview.cvs.entity.files.FileName;

import java.util.List;

/**
 * Created by kane on 2017/6/11.
 */
public interface FileService<K extends FileName,V extends FileContent> {

    void addOrEditFile(File<K,V> file, Long branchId) ;

    void removeFile(K name,Long branchId) ;

    File<K,V> viewFile(K name,Long branchId) ;

    File<K,V> viewPreFile(K name,Long branchId) ;

    List<File<K,V>> getFiles(List<K> name,List<Long> branchIds) ;

    List<File<K,V>> searchFiles(K name) ;
}
