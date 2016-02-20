package cn.kane.mview.worker.product.render.view;

import java.util.Map;

import cn.kane.mview.service.product.vo.ViewModel;
import cn.kane.mview.service.resource.entity.Resource;


public interface ViewMerger<T extends Resource> {

	ViewModel merge(T resource, Map<String, Object> context);

}
