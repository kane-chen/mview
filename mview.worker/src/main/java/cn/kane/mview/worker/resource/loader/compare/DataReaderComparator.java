package cn.kane.mview.worker.resource.loader.compare;

import org.apache.commons.lang3.builder.EqualsBuilder;

import cn.kane.mview.service.resource.entity.DataReader;

public class DataReaderComparator implements ResourceComparator<DataReader> {

    @Override
    public boolean isEquals(DataReader res1, DataReader res2) {
        return EqualsBuilder.reflectionEquals(res1, res2, "dataReadService") ;
    }

}
