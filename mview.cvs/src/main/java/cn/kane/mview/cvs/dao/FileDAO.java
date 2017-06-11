package cn.kane.mview.cvs.dao;

import cn.kane.mview.cvs.entity.File;
import cn.kane.mview.cvs.entity.files.FileContent;
import cn.kane.mview.cvs.entity.files.FileName;

import java.util.List;

/**
 * Created by kane on 2017/6/11.
 */
public interface FileDAO<K extends FileName,V extends FileContent> {

    void add(File<K,V> file) ;

    void edit(File<K,V> file) ;

    void remove(K fileName) ;

    File<K,V> view(K fileName) ;

    List<File<K,V>>  getFiles(List<K> fileNames) ;

    List<File<K,V>> searchFiles(K name) ;

}
