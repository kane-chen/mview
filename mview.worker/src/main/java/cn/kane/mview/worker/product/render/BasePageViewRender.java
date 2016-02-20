package cn.kane.mview.worker.product.render;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.kane.mview.service.definition.entity.DefinitionKey;
import cn.kane.mview.service.product.service.PageViewRender;
import cn.kane.mview.service.product.vo.ViewModel;
import cn.kane.mview.service.resource.entity.Page;
import cn.kane.mview.service.resource.service.PageLoader;
import cn.kane.mview.worker.product.render.data.DataAggregator;
import cn.kane.mview.worker.product.render.view.ViewMerger;


public class BasePageViewRender implements PageViewRender {
	@Autowired
	private PageLoader pageLoader;
	@Autowired
	private DataAggregator dataAggregator;
	@Autowired
	private ViewMerger<Page> pageViewMerger;

	@Override
	public ViewModel reander(DefinitionKey defKey, Map<String, Object> params) {
		// load
		Page page = pageLoader.getResourceByKey(defKey);
		// data-aggregate
		Map<String, Object> context = dataAggregator.aggregate(page.getDataReaderKeys(), params);
		// render
		ViewModel pageView = pageViewMerger.merge(page, context);
		return pageView;
	}

}
