package cn.kane.mview.worker.product.render.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.kane.mview.service.definition.entity.DefinitionKey;
import cn.kane.mview.service.resource.entity.DataReader;
import cn.kane.mview.service.resource.service.DataReaderLoader;
import cn.kane.mview.worker.util.DefinitionKeyUtils;


public class SimpleDataAggregator implements DataAggregator {
	@Autowired
	private DataReaderLoader dataReaderLoader;

	@Override
	public Map<String, Object> aggregate(List<DefinitionKey> dataReaderKeys, Map<String, Object> param) {
		dataReaderKeys = this.sortDataReaders(dataReaderKeys);
		if(null == dataReaderKeys){
			return null ;
		}
		Map<String, Object> context = new HashMap<String, Object>();
		if (null != param) {
			context.putAll(param);
		}
		for (DefinitionKey key : dataReaderKeys) {
			if (null == key) {
				continue;
			}
			DataReader dataReader = dataReaderLoader.getResourceByKey(key);
			if (null == dataReader) {
				continue;
			}
			Object result = dataReader.getData(param);
			if (null != result) {
				context.put(DefinitionKeyUtils.format(key), result);
			}
		}
		return context;
	}

	protected List<DefinitionKey> sortDataReaders(List<DefinitionKey> dataReaderKeys) {
		// TODO dependency list
		return dataReaderKeys;
	}

}
