package cn.kane.mview.worker.resource.loader.compare;

import cn.kane.mview.service.resource.entity.Resource;

public interface ResourceComparator<T extends Resource> {

	boolean isEquals(T res1, T res2);

}
