package cn.kane.mview.worker.product.render.data;

import java.util.List;
import java.util.Map;

import cn.kane.mview.service.definition.entity.DefinitionKey;

public interface DataAggregator {

	/**
	 * dataReader's return-data aggregate
	 * @param dataReaderKeys
	 * @param param          dataReader's input-parameter
	 * @return   context(result-data)
	 */
	Map<String, Object> aggregate(List<DefinitionKey> dataReaderKeys, Map<String, Object> param);

}
