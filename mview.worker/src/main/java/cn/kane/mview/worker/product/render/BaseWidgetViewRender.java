package cn.kane.mview.worker.product.render;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.kane.mview.service.definition.entity.DefinitionKey;
import cn.kane.mview.service.product.service.WidgetViewRender;
import cn.kane.mview.service.product.vo.ViewModel;
import cn.kane.mview.service.resource.entity.Widget;
import cn.kane.mview.service.resource.service.WidgetLoader;
import cn.kane.mview.worker.product.render.data.DataAggregator;
import cn.kane.mview.worker.product.render.view.ViewMerger;

public class BaseWidgetViewRender implements WidgetViewRender {
	@Autowired
	private WidgetLoader widgetLoader;
	@Autowired
	private DataAggregator dataAggregator;
	@Autowired
	private ViewMerger<Widget> widgetViewMerger;

	@Override
	public ViewModel reander(DefinitionKey defKey, Map<String, Object> params) {
		// load
		Widget widget = widgetLoader.getResourceByKey(defKey);
		// data-aggregate
		Map<String, Object> context = dataAggregator.aggregate(widget.getDataReaderKeys(), params);
		// render
		ViewModel pageView = widgetViewMerger.merge(widget, context);
		return pageView;
	}

}
