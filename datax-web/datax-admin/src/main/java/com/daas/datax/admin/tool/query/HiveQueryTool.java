package com.daas.datax.admin.tool.query;

import com.daas.datatx.core.enums.DbType;


/**
 * hive
 *
 * @author wenkaijing
 * @version 2.0
 * @since 2020/01/05
 */
public class HiveQueryTool extends BaseQueryTool implements QueryToolInterface {
    public HiveQueryTool(DbType dbType, String parameter) {
        super(dbType, parameter);
    }
}
