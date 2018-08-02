package com.example.webmagic.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class PooledEntityFactory extends BasePooledObjectFactory<PooledEntity> {

    @Override
    public PooledEntity create() throws Exception {
        PooledEntity pooledEntity = new PooledEntity();
        pooledEntity.setAttr_1("Larry");
        pooledEntity.setAttr_2("Euphie");
        return pooledEntity;
    }

    @Override
    public PooledObject<PooledEntity> wrap(PooledEntity pooledEntity) {
        return new DefaultPooledObject<>(pooledEntity);
    }
}
