package com.daas.datatx.core.datasource;

import com.daas.datatx.core.enums.DbType;
import com.daas.datatx.core.util.Constants;


/**
 * data source of DB2 Server
 */
public class DB2ServerDataSource extends BaseDataSource {

    /**
     *  gets the JDBC url for the data source connection
     * @return jdbc url
     */
    @Override
    public String driverClassSelector() {
        return Constants.COM_DB2_JDBC_DRIVER;
    }

    /**
     * @return db type
     */
    @Override
    public DbType dbTypeSelector() {
        return DbType.DB2;
    }
}
