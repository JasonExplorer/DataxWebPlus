package com.dash.datax.admin.tool.meta;

import com.dash.datax.admin.tool.enums.DbTypePlugin;
import com.pji.cloud.datatx.core.enums.DbType;

/**
 * meta信息工厂
 *
 * @author weiye
 */
public class DatabaseMetaFactory {

    /**
     * 根据数据库类型返回对应的接口
     *
     * @param dbType String
     * @return DatabaseInterface
     */
    public static DatabaseInterface getByDbType(DbType dbType) {
        return DbTypePlugin.getDbTypePlugin(dbType).getDatabaseInterface();
    }
}