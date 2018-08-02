package com.example.webmagic.pool;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class PoolFuctionTest {

    public static void main(String[] args) throws Exception {

        PooledEntityFactory pooledEntityFactory = new PooledEntityFactory();
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        ObjectPool pool = new GenericObjectPool(pooledEntityFactory, config);

        PooledEntity pooledEntity = (PooledEntity) pool.borrowObject();
        System.out.println(pooledEntity.getAttr_1() + " == " + pooledEntity.getAttr_2());

        System.out.println("active object: " + pool.getNumActive());
        System.out.println("idle object: " + pool.getNumIdle());

        pool.returnObject(pooledEntity);

        System.out.println("active object: " + pool.getNumActive());
        System.out.println("idle object: " + pool.getNumIdle());
    }
}
