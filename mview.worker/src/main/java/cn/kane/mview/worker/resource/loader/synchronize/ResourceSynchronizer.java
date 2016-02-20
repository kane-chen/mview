package cn.kane.mview.worker.resource.loader.synchronize;

import cn.kane.mview.service.definition.entity.DefinitionKey;
import cn.kane.mview.service.resource.entity.Resource;

public interface ResourceSynchronizer<T extends Resource> {

	T sync(DefinitionKey key);

	T get(DefinitionKey key);
}
