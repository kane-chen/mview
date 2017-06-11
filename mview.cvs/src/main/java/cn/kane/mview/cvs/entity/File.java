package cn.kane.mview.cvs.entity;

import cn.kane.mview.cvs.entity.files.FileContent;
import cn.kane.mview.cvs.entity.files.FileName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by kane on 2017/6/11.
 */
public class File<K extends FileName,V extends FileContent> implements Serializable {

    private static final long serialVersionUID = -2693878711324725748L;
    private K fileName ;

    private V content ;

    public K getFileName() {
        return fileName;
    }

    public void setFileName(K fileName) {
        this.fileName = fileName;
    }

    public V getContent() {
        return content;
    }

    public void setContent(V content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this) ;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this,obj) ;
    }
}
