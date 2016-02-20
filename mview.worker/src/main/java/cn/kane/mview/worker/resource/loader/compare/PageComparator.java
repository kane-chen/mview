package cn.kane.mview.worker.resource.loader.compare;

import org.apache.commons.lang3.builder.EqualsBuilder;

import cn.kane.mview.service.resource.entity.Page;

public class PageComparator implements ResourceComparator<Page> {

    @Override
    public boolean isEquals(Page res1, Page res2) {
        return EqualsBuilder.reflectionEquals(res1, res2);
    }
    

}
